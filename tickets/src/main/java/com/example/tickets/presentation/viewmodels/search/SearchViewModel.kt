package com.example.tickets.presentation.viewmodels.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickets.domain.api.interacrors.RecommendTicketsInteractor
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.presentation.models.search.SearchScreenState
import com.example.tickets.presentation.models.search.SearchScreenStateResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SearchViewModel(
    private val interactor: RecommendTicketsInteractor,
    private val departureName: String,
    private val arrivalName: String
) : ViewModel() {
    private var thereDate = Calendar.getInstance()
    private var backDate = Calendar.getInstance()
    private var departureTown: String = departureName
    private var arrivalTown: String = arrivalName
    private var _searchScreenState: MutableStateFlow<SearchScreenState> =
        MutableStateFlow(SearchScreenState())
    val searchScreenState: StateFlow<SearchScreenState> = _searchScreenState.asStateFlow()

    fun getSearchData() {
        _searchScreenState.update {
            it.copy(
                departureTown = departureTown,
                arrivalTown = arrivalTown,
                thereDate = getCurrentDate(thereDate),
                thereDayName = getCurrentDayName(thereDate)
            )
        }
        viewModelScope.launch {
            interactor.getRecommendTickets().collect { result ->
                processingResult(result = result)
            }
        }
    }

    private fun processingResult(result: SearchResultData<List<RecommendTicket>>) {
        when (result) {
            is SearchResultData.Data -> {
                if (result.value != null) {
                    _searchScreenState.update {
                        it.copy(data = SearchScreenStateResponse.Content(tickets = result.value))
                    }
                }
            }

            is SearchResultData.Empty -> {
                _searchScreenState.update {
                    it.copy(data = SearchScreenStateResponse.Error(message = result.message))
                }
            }

            is SearchResultData.ErrorServer -> {
                _searchScreenState.update {
                    it.copy(data = SearchScreenStateResponse.Error(message = result.message))
                }

            }

            is SearchResultData.NoInternet -> {
                _searchScreenState.update {
                    it.copy(data = SearchScreenStateResponse.Error(message = result.message))
                }
            }
        }
    }

    fun setThereDate(date: Calendar) {
        thereDate = date
        _searchScreenState.update {
            it.copy(
                thereDate = getCurrentDate(date),
                thereDayName = getCurrentDayName(date)
            )
        }
    }

    fun setBackDate(date: Calendar) {
        backDate = date
        _searchScreenState.update {
            it.copy(
                backDate = getFullDate(date)
            )
        }
    }

    fun destinationChanged() {
        val bufer = departureTown
        departureTown = arrivalTown
        arrivalTown = bufer
        _searchScreenState.update {
            it.copy(
                arrivalTown = arrivalTown,
                departureTown = departureTown
            )
        }
    }

    private fun getFullDate(date: Calendar): String {
        return "${getCurrentDate(date)}, ${getCurrentDayName(date)}"
    }

    private fun getCurrentDayName(date: Calendar): String {
        val formaterDayInWeek = SimpleDateFormat("EEE", Locale.getDefault())
        val dayName = formaterDayInWeek.format(date.time)
        return ", $dayName"
    }

    private fun getCurrentDate(date: Calendar): String {
        val formaterDayMon = SimpleDateFormat("dd MMM", Locale.getDefault())
        val dayAndMon = formaterDayMon.format(date.time)
        return dayAndMon
    }
}