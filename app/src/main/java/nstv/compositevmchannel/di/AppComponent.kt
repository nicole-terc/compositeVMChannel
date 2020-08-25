package nstv.compositevmchannel.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import nstv.compositevmchannel.BuildConfig
import nstv.compositevmchannel.compositeVM.delegate.LoadViewModelDelegate
import nstv.compositevmchannel.compositeVM.delegate.UpdateFavoriteViewModelDelegate
import nstv.compositevmchannel.data.DataApi
import nstv.compositevmchannel.data.favoriteDB.DB_NAME
import nstv.compositevmchannel.data.favoriteDB.FavoriteDataBase
import nstv.compositevmchannel.list.ListCompositeViewModel
import nstv.compositevmchannel.useCase.GetFavoritesUseCase
import nstv.compositevmchannel.useCase.LoadItemsUseCase
import nstv.compositevmchannel.useCase.UpdateFavoriteStatusUseCase
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class AppComponent : KoinComponent {
    fun initModules(application: Application) {
        startKoin {
            androidLogger()
            androidContext(application)
            modules(
                dataModule,
                useCaseModule,
                delegateModule,
                listModule
            )
        }
    }
}

private val dataModule = module {

    single {
        val cacheSize = 10 * 1024 * 1024
        Cache(this.androidApplication().cacheDir, cacheSize.toLong())
    }

    single {
        OkHttpClient.Builder()
            .cache(get())
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .build()
    }

    single<DataApi> {
        get<Retrofit>().create(DataApi::class.java)
    }

    single {
        Room.databaseBuilder(get(), FavoriteDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<FavoriteDataBase>().favoriteDao()
    }
}

private val useCaseModule = module {
    factory { LoadItemsUseCase(get()) }
    factory { GetFavoritesUseCase(get()) }
    factory { UpdateFavoriteStatusUseCase(get()) }
}

private val delegateModule = module {
    factory { LoadViewModelDelegate(get()) }
    factory { UpdateFavoriteViewModelDelegate(get()) }
}

private val listModule = module {
    viewModel {
        ListCompositeViewModel(
            listOf(
                get<LoadViewModelDelegate>(),
                get<UpdateFavoriteViewModelDelegate>()
            )
        )
    }
}


