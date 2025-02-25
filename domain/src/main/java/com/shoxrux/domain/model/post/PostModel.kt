package com.shoxrux.domain.model.post

import com.google.firebase.Timestamp
import java.util.UUID

data class PostModel(
    val authorId: String = "",
    val id: String = UUID.randomUUID().toString(),
    val postTime: Timestamp = Timestamp.now(),
    val content: String = "",
    val imageUrl: String = "",
    val likeCount: Int = 0,
    val commentCount: Int = 0

)