package hr.ikvakan.betshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.ikvakan.betshop.mapper.ModelMapperImpl
import hr.ikvakan.betshop.repository.Repository
import hr.ikvakan.betshop.retrofit.BASE_URL
import hr.ikvakan.betshop.retrofit.BetshopAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance() : BetshopAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BetshopAPI::class.java)

    @Singleton
    @Provides
    fun provideRepository(betshopAPI: BetshopAPI,modelMapperImpl: ModelMapperImpl) : Repository {
        return Repository(betshopAPI,modelMapperImpl)
    }

}