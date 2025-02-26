package com.shoxrux.data.di.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
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
    fun providePostRepository(
        auth: FirebaseAuth,
        storage: FirebaseStorage,
        firebaseFirestore: FirebaseFirestore
    ): PostRepository {
        return PostRepositoryImpl(firestore = firebaseFirestore, firebaseStorage = storage, auth)
    }

    @[Provides Singleton]
    fun providePostUseCase(authRepository: PostRepository) = PostUseCase(authRepository)


}