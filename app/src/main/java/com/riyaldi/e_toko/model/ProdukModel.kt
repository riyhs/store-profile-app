package com.riyaldi.e_toko.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProdukModel (
    val nama : String? = null,
    val harga : Int = 0,
    val deskripsi : String? = null,
    val jenis : String? = null,
    val ukuran : String? = null,
    val foto: String? = null
) : Parcelable