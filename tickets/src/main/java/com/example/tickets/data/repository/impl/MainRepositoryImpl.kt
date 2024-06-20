package com.example.tickets.data.repository.impl

import com.example.data.client.api.NetworkClient
import com.example.tickets.R
import com.example.tickets.data.convrtors.map
import com.example.tickets.domain.api.repositories.MainRepository
import com.example.tickets.domain.models.MainData
import com.example.tickets.domain.models.SearchResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

class MainRepositoryImpl(private val client: NetworkClient) : MainRepository {
    override suspend fun getMainData(): Flow<SearchResultData<List<MainData>>> =
        flow {
            val searchResult = client.getMainData()
            val data = searchResult.getOrNull()
            val error = searchResult.exceptionOrNull()
            when {
                data != null -> {
                    emit(SearchResultData.Data(data.offers.map { map(it) }))
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