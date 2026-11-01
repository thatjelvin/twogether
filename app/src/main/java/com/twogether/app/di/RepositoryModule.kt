package com.twogether.app.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.twogether.app.data.datasource.PairingDataSource
import com.twogether.app.data.datasource.RitualDataSource
import com.twogether.app.data.repository.AuthRepositoryImpl
import com.twogether.app.data.repository.PairingRepositoryImpl
import com.twogether.app.data.repository.RitualRepositoryImpl
import com.twogether.app.domain.repository.AuthRepository
import com.twogether.app.domain.repository.PairingRepository
import com.twogether.app.domain.repository.RitualRepository
import com.twogether.app.domain.usecase.CreateCouple
import com.twogether.app.domain.usecase.JoinCouple
import com.twogether.app.domain.usecase.SignInWithGoogle
import com.twogether.app.feature.auth.AuthViewModel
import com.twogether.app.feature.auth.PairingViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
    ): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    @Singleton
    fun providePairingDataSource(firestore: FirebaseFirestore): PairingDataSource =
        PairingDataSource(firestore)

    @Provides
    @Singleton
    fun providePairingRepository(
        dataSource: PairingDataSource,
        auth: FirebaseAuth,
    ): PairingRepository = PairingRepositoryImpl(dataSource, auth)

    @Provides
    @Singleton
    fun provideSignInWithGoogle(authRepository: AuthRepository): SignInWithGoogle =
        SignInWithGoogle(authRepository)

    @Provides
    @Singleton
    fun provideCreateCouple(pairingRepository: PairingRepository): CreateCouple =
        CreateCouple(pairingRepository)

    @Provides
    @Singleton
    fun provideJoinCouple(pairingRepository: PairingRepository): JoinCouple =
        JoinCouple(pairingRepository)

    @Provides
    @Singleton
    fun provideRitualDataSource(firestore: FirebaseFirestore): RitualDataSource =
        RitualDataSource(firestore)

    @Provides
    @Singleton
    fun provideRitualRepository(
        dataSource: RitualDataSource,
        auth: FirebaseAuth,
    ): RitualRepository = RitualRepositoryImpl(dataSource, auth)

    @Provides
    @Singleton
    fun provideAuthViewModel(signInWithGoogle: SignInWithGoogle): AuthViewModel =
        AuthViewModel(signInWithGoogle)

    @Provides
    @Singleton
    fun providePairingViewModel(
        createCouple: CreateCouple,
        joinCouple: JoinCouple,
    ): PairingViewModel = PairingViewModel(createCouple, joinCouple)
}
