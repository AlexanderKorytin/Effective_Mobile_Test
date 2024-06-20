package com.example.tickets.data.repository.impl

import com.example.data.client.api.NetworkClient
import com.example.tickets.R
import com.example.tickets.data.convrtors.map
import com.example.tickets.domain.api.repositories.AllTicketsRepository
import com.example.tickets.domain.models.SearchResultData
import com.example.tickets.domain.models.TicketItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class AllTicketsRepositoryImpl(private val client: NetworkClient) : AllTicketsRepository {
    override suspend fun getAllTickets(): Flow<SearchResultData<List<TicketItem>>> = flow {
        val searchResult = client.getAllTickets()
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()
        when {
            data != null -> {
                emit(SearchResultData.Data(data.tickets.map { map(it) }))
            }

            error is ConnectException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet_error_text))
            }

            error is SocketTimeoutException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet_error_text))
            }

            error is HttpException -> {
                emit(SearchResultData.ErrorServer(R.string.server_error))
            }
        }
    }
}