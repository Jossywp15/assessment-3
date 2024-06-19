package org.d3if3169.doghotel.model

import com.squareup.moshi.Json

data class Anjing(
    @Json(name = "id")
    val id: Int,
    @Json(name = "user_email")
    val userEmail: String,
    @Json(name = "nama_anjing")
    val nama: String,
    @Json(name = "tipe_anjing")
    val tipe: String,
    @Json(name = "umur")
    val umur: String,
    @Json(name = "image_id")
    val imageId: String,
    @Json(name = "created_at")
    val createdAt: String
)