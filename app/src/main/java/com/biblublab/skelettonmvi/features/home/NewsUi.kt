package com.biblublab.skelettonmvi.features.home

import android.os.Parcelable
import com.biblublab.data.common.EMPTY_STRING
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsUi(
    val title: String,
    val description: String,
    val url: String,
    val urlImage: String,
    val formattedDate: String = EMPTY_STRING
) : Parcelable {
}