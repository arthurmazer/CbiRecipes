package com.mazer.cbirecipes.presentation.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.mazer.cbirecipes.BuildConfig
import com.mazer.cbirecipes.data.local.CacheDataSource
import com.mazer.cbirecipes.data.local.CacheDataSourceImpl
import com.mazer.cbirecipes.data.remote.RemoteDataSource
import com.mazer.cbirecipes.data.remote.RemoteDataSourceImpl
import com.mazer.cbirecipes.data.remote.api.ApiService
import com.mazer.cbirecipes.data.repos.RecipeRepository
import com.mazer.cbirecipes.data.repos.RecipeRepositoryImpl
import com.mazer.cbirecipes.domain.use_cases.*
import com.mazer.cbirecipes.presentation.ui.modules.home.MainViewModel
import com.mazer.cbirecipes.presentation.ui.modules.onboarding.OnboardingViewModel
import com.mazer.cbirecipes.presentation.ui.modules.recipe.RecipeViewModel
import com.mazer.cbirecipes.presentation.ui.modules.search.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {


    single<SharedPreferences> {
        androidContext().getSharedPreferences("cbi_recips_prefs", Context.MODE_PRIVATE)
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor{ message -> Log.d("HttpLoggingInterceptor", message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addNetworkInterceptor (loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(request)
            }
            .build()
    }


    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    factory<CacheDataSource> { CacheDataSourceImpl(get()) }
    factory<RecipeRepository> { RecipeRepositoryImpl(get()) }
    factory { GetRecipeUseCases(get()) }
    factory { PlayVideoUseCase(get()) }
    factory { CheckFirstTimeUseCase(get()) }
    factory { GetCarouselOnboardingUseCase() }
    factory { SearchUseCase(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { RecipeViewModel(get(), get()) }
    viewModel { OnboardingViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
}
