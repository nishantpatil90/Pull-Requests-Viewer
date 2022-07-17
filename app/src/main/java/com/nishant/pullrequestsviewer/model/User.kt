package com.nishant.pullrequestsviewer.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val userName: String?,
    val avatar_url: String?
)
