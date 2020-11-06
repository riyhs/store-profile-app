package com.riyaldi.e_toko.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransaksiModel (
    val nama: String? = null,
    val harga: Int = 0,
) : Parcelable