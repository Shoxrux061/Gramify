package com.shoxrux.domain.repository

import com.shoxrux.core.handler.NetworkResult
import com.shoxrux.domain.model.post.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun addPost(postModel: PostModel): Flow<NetworkResult<Boolean>>

}