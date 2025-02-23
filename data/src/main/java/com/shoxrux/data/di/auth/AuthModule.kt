package com.shoxrux.data.di.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shoxrux.data.repository.AuthRepositoryImpl
import com.shoxrux.domain.repository.AuthRepository
import com.shoxrux.domain.usecase.AuthUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @[Provides Singleton]
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @[Provides Singleton]
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @[Provides Singleton]
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(auth = auth, firestore = firebaseFirestore)
    }

    @[Provides Singleton]
    fun provideAuthUseCase(authRepository: AuthRepository) = AuthUseCase(authRepository)

}