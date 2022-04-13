package com.fork.spoonfeed.domain.model

import com.fork.spoonfeed.data.remote.model.community.ResponsePostData
import java.io.Serializable


data class CommentData(
    val data: ResponsePostData.Data.Comment, val commentUpdateListener: (ResponsePostData.Data.Comment) -> (Unit),
    val commentDeleteListener: (ResponsePostData.Data.Comment) -> (Unit)
) : Serializable