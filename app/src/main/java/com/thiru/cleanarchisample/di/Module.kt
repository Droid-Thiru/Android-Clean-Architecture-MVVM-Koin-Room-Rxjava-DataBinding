package com.thiru.cleanarchisample.di

import androidx.room.Room
import com.thiru.cleanarchisample.data.database.MY_DATA_BASE
import com.thiru.cleanarchisample.data.database.MyDatabase
import com.thiru.cleanarchisample.data.repository.UserRepositoryImpl
import com.thiru.cleanarchisample.domain.repository.UserRepository
import com.thiru.cleanarchisample.domain.usecase.LoginUseCase
import com.thiru.cleanarchisample.domain.usecase.RegisterUseCase
import com.thiru.cleanarchisample.presenters.login.LoginViewModel
import com.thiru.cleanarchisample.presenters.registration.RegistrationViewModel
import com.thiru.cleanarchisample.utils.FormValidation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            databaseModule,
            repositoryModule,
            useCaseModule,
            viewModelModule,
            validationModule)
    )
}
val databaseModule = module {
    single { Room.databaseBuilder(get(),MyDatabase::class.java, MY_DATA_BASE).build() }
    single { get<MyDatabase>().getUserDao() }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(),get()) }
    viewModel { RegistrationViewModel(get(),get()) }
}

val validationModule = module {
    factory { FormValidation() }
}
