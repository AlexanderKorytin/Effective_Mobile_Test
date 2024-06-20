package com.example.tickets.presentation.viewmodels.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.api.interacrors.MainInteractor
import com.example.tickets.domain.models.OfferData
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.Town
import com.example.tickets.presentation.models.main.MainScreenState
import com.example.util.debounce
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: MainInteractor) : ViewModel() {
    private var _mainScreenState: MutableLiveData<MainScreenState> = MutableLiveData()
    val mainScreenState: LiveData<MainScreenState> = _mainScreenState
    private val savedTownName = debounce<String>(DELAY, viewModelScope, true) { name ->
        setTownToStorage(name)
    }

    fun getMainData() {
        val town = interactor.getCurrentTown()
        _mainScreenState.postValue(MainScreenState.IsLoading(town = town))
        viewModelScope.launch {
            interactor.getMainData().collect { result ->
                processingResult(result = result, town = town)
            }
        }
    }

    fun debounceSave(townName: String) {
        savedTownName(townName)
    }

    private fun setTownToStorage(townName: String) {
        interactor.setDepartureTown(Town(name = townName))
    }

    private fun processingResult(result: SearchResultData<List<OfferData>>, town: Town) {
        when (result) {
            is SearchResultData.Data -> {
                if (result.value != null) {
                    _mainScreenState.postValue(
                        MainScreenState.Content(
                            offers = result.value,
                            town = town
                        )
                    )
                }
            }

            is SearchResultData.Empty -> {
                _mainScreenState.postValue(
                    MainScreenState.Error(
                        message = result.message,
                        town = town
                    )
                )
            }

            is SearchResultData.ErrorServer -> {
                _mainScreenState.postValue(
                    MainScreenState.Error(
                        message = result.message,
                        town = town
                    )
                )
            }

            is SearchResultData.NoInternet -> {
                _mainScreenState.postValue(
                    MainScreenState.Error(
                        message = result.message,
                        town = town
                    )
                )
            }
        }
    }
}

private const val DELAY = 300L