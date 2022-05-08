package hr.ikvakan.betshop.repository


import hr.ikvakan.betshop.mapper.ModelMapperImpl
import hr.ikvakan.betshop.model.BetshopLocationModel
import hr.ikvakan.betshop.retrofit.BetshopAPI
import hr.ikvakan.betshop.retrofit.COORDINATES
import hr.ikvakan.betshop.retrofit.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

import javax.inject.Inject

class Repository
@Inject constructor(
    private val betshopAPI: BetshopAPI,
    private val modelMapperImpl: ModelMapperImpl
) {
    suspend fun getBetshopLocationItem(): Flow<DataState<BetshopLocationModel>> = flow {
        try {
            val response = betshopAPI.getBetshopLocationItem(COORDINATES)
            val betshopLocations = modelMapperImpl.mapFromEntityItemToDomainModel(response)
            emit(DataState.Success(betshopLocations))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}