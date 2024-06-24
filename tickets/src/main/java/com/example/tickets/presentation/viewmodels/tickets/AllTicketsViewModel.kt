package com.example.tickets.presentation.viewmodels.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.api.interacrors.AllTicketsInteractor
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.TicketItem
import com.example.tickets.presentation.models.tickets.AllTicketsScreenState
import com.example.tickets.presentation.models.tickets.AllTicketsScreenStateResponse
import com.example.tickets.presentation.models.tickets.TicketInfo
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllTicketsViewModel(
    private val interactor: AllTicketsInteractor,
    json: Gson,
    ticketInfo: String
) : ViewModel() {
    private val ticketParams = json.fromJson(ticketInfo, TicketInfo::class.java)
    private var _allTicketsScreenState: MutableStateFlow<AllTicketsScreenState> =
        MutableStateFlow(AllTicketsScreenState())
    val allTicketsScreenState: StateFlow<AllTicketsScreenState> =
        _allTicketsScreenState.asStateFlow()

    fun getAllTicketsData() {
        _allTicketsScreenState.update {
            it.copy(data = AllTicketsScreenStateResponse.IsLoading, ticketInfo = ticketParams)
        }
        viewModelScope.launch {
            interactor.getAllTickets().collect { result ->
                processingResult(result = result)
            }
        }
    }

    private fun processingResult(result: SearchResultData<List<TicketItem>>) {
        when (result) {
            is SearchResultData.Data -> {
                if (result.value != null) {
                    _allTicketsScreenState.update {
                        it.copy(data = AllTicketsScreenStateResponse.Content(tickets = result.value))
                    }
                }
            }

            is SearchResultData.Empty -> {
                _allTicketsScreenState.update {
                    it.copy(data = AllTicketsScreenStateResponse.Error(message = result.message))
                }
            }

            is SearchResultData.ErrorServer -> {
                _allTicketsScreenState.update {
                    it.copy(data = AllTicketsScreenStateResponse.Error(message = result.message))
                }
            }

            is SearchResultData.NoInternet -> {
                _allTicketsScreenState.update {
                    it.copy(data = AllTicketsScreenStateResponse.Error(message = result.message))
                }
            }
        }
    }
}