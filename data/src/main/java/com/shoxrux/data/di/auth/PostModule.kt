package com.shoxrux.data.di.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shoxrux.data.repository.PostRepositoryImpl
import com.shoxrux.domain.repository.PostRepository
import com.shoxrux.domain.usecase.PostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @[Provides Singleton]
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore
    ): PostRepository {
        return PostRepositoryImpl(firestore = firebaseFirestore)
    }

    @[Provides Singleton]
    fun provideAuthUseCase(authRepository: PostRepository) = PostUseCase(authRepository)


}