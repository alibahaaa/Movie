package ir.baha.moviedomain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val title: String,
    val image: String
) : Parcelable