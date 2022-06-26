package br.com.lucolimac.soccernews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey
    val id: Int,
    val description: String,
    val image: String,
    val link: String,
    val title: String,
    var favorite: Boolean
)