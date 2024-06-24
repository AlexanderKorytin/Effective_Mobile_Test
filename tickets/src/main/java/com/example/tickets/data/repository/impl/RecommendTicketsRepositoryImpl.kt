package com.example.tickets.data.repository.impl

import com.example.data.client.api.NetworkClient
import com.example.tickets.R
import com.example.tickets.data.convrtors.map
import com.example.tickets.domain.api.repositories.RecommendTicketsRepository
import com.example.tickets.domain.models.RecommendTicket
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class RecommendTicketsRepositoryImpl(private val client: NetworkClient) :
    RecommendTicketsRepository {
    override suspend fun getRecommendTickets(): Flow<SearchResultData<List<RecommendTicket>>> =
        flow {
            val searchResult = client.getRecommendTickets()
            val data = searchResult.getOrNull()
            val error = searchResult.exceptionOrNull()
            when {
                data != null -> {
                    emit(SearchResultData.Data(data.tickets.map { map(it) }
                        .mapIndexed { index, recommendTicket ->
                            setIconId(recommendTicket, index)
                        }))
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

    private fun setIconId(ticket: RecommendTicket, index: Int): RecommendTicket {
        return when (index) {
            0 -> {
                ticket.copy(iconId = 1)
            }

            1 -> {
                ticket.copy(iconId = 2)
            }

            2 -> {
                ticket.copy(iconId = 3)
            }

            else -> {
                ticket.copy(iconId = 4)
            }
        }
    }
}