package com.example.moviesdb.client.model

data class ReviewResponse(
    val id: Int? = null,
    val page: Int? = null,
    val results: List<Review>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null,
)

data class Review(
    val author: String? = null,
    val content: String? = null,
    val created_at: String? = null,
    val id: String? = null,
    val updated_at: String? = null,
    val url: String? = null,
)
