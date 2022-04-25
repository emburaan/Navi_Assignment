package com.sumit.navi.models

import com.google.gson.annotations.SerializedName

data class Pull(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val name: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("closed_at")
    val closedAt: String?
)

data class User(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)