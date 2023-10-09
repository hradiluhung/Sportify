package com.adiluhung.sportify.core.di

import androidx.room.Room
import com.adiluhung.sportify.core.data.PlayerRepository
import com.adiluhung.sportify.core.data.TeamRepository
import com.adiluhung.sportify.core.data.source.local.LocalDataSource
import com.adiluhung.sportify.core.data.source.local.room.TeamDatabase
import com.adiluhung.sportify.core.data.source.remote.RemoteDataSource
import com.adiluhung.sportify.core.data.source.remote.network.ApiService
import com.adiluhung.sportify.core.domain.repository.IPlayerRepository
import com.adiluhung.sportify.core.domain.repository.ITeamRepository
import com.adiluhung.sportify.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<TeamDatabase>().teamDao() }
    factory { get<TeamDatabase>().playerDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("adiluhung".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TeamDatabase::class.java, "Teams.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/smdLYb0tBmIrtC1iYizy1svf7FACM13T2oOwdEjnagw=")
            .add(hostname, "sha256/i7LHCNm4KCjPzj1tqkk+ay+iOmDaEPHvFlQiOwufi8M=")
            .add(hostname, "sha256/ljTDgm/k397r3IdZEKRul2NCPhqITZKGW8ue2nIVaGc=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITeamRepository> {
        TeamRepository(
            get(),
            get(),
            get()
        )
    }
    single<IPlayerRepository> {
        PlayerRepository(
            get(),
            get()
        )
    }
}