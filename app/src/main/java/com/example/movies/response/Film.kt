package com.example.movies.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val description: String,
    val genres: List<String>,
    val id: Int,
    val image_url: String,
    val localized_name: String,
    val name: String,
    val rating: Double,
    val year: Int

) : Parcelable {
    var onClick : () -> Unit = {}
}