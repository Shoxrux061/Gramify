package com.shoxrux.domain.usecase

import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.domain.repository.PostRepository
import javax.inject.Inject

class PostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend fun addPost(postModel: PostModel) = repository.addPost(postModel)


}