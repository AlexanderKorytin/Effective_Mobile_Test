package com.example.tickets.presentation.viewmodels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.api.interacrors.MainInteractor
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.Town
import com.example.tickets.presentation.models.main.MainScreeState
import com.example.tickets.presentation.models.main.MainScreenStateResponse
import com.example.util.debounce
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: MainInteractor) : ViewModel() {
    private var _mainScreenState: MutableStateFlow<MainScreeState> =
        MutableStateFlow(MainScreeState())
    val mainScreenState: StateFlow<MainScreeState> = _mainScreenState.asStateFlow()
    private val savedTownName = debounce<String>(DELAY, viewModelScope, true) { name ->
        setTownToStorage(name)
    }

    fun getMainData() {
        val town = interactor.getCurrentTown()
        _mainScreenState.update {
            it.copy(departureTown = town)
        }
        viewModelScope.launch {
            interactor.getMainData().collect { result ->
                processingResult(result = result)
            }
        }
    }

    fun debounceSave(townName: String) {
        savedTownName(townName)
    }

    private fun setTownToStorage(townName: String) {
        interactor.setDepartureTown(Town(name = townName))
    }

    private fun processingResult(result: SearchResultData<List<OfferData>>) {
        when (result) {
            is SearchResultData.Data -> {
                if (result.value != null) {
                    _mainScreenState.update {
                        it.copy(data = MainScreenStateResponse.Content(offers = result.value))
                    }
                }
            }

            is SearchResultData.Empty -> {
                _mainScreenState.update {
                    MainScreeState().copy(data = MainScreenStateResponse.Error(message = result.message))
                }
            }

            is SearchResultData.ErrorServer -> {
                _mainScreenState.update {
                    it.copy(data = MainScreenStateResponse.Error(message = result.message))
                }

            }

            is SearchResultData.NoInternet -> {
                _mainScreenState.update {
                    it.copy(data = MainScreenStateResponse.Error(message = result.message))
                }
            }
        }
    }
}

private const val DELAY = 300L