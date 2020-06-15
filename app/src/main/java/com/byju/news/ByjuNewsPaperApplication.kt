package com.byju.news

import android.app.Application
import com.byju.news.data.db.AppDatabase
import com.byju.news.data.network.MyApi
import com.byju.news.data.network.NetworkConnectionInterceptor
import com.byju.news.data.repositories.NewsRepository
import com.byju.news.viewmodel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ByjuNewsPaperApplication  : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ByjuNewsPaperApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { NewsRepository(instance(), instance()) }
        bind() from provider{ ViewModelFactory(instance()) }


    }

}