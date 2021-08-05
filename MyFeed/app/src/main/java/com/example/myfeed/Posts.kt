package com.example.myfeed

data class Posts(
    val userId: Int,
    val id: Int,
    val title: String? = null,
    val body: String? = null
)
