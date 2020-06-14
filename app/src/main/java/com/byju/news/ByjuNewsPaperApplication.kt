package com.byju.news

import android.app.Application
import com.byju.news.data.db.AppDatabase
import com.byju.news.model.NewsPaperDataSource
import com.byju.news.model.NewsPaperRepository
import com.byju.news.viewmodel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ByjuNewsPaperApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ByjuNewsPaperApplication))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { NewsPaperRepository(instance()) }
        bind() from singleton { ViewModelFactory(instance()) }
    }

}