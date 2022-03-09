package com.fork.spoonfeed.data.remote.model.user

data class RequestQuestionData(
    val userId: String,
    val title: String,
    val content: String,
    val email: String
)