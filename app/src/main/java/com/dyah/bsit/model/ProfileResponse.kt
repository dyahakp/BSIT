package com.dyah.bsit.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileResponse(
    @SerializedName("name")
    var name: String?,
    @SerializedName("joined_date")
    var joinedDate: String?,
    @SerializedName("image_url")
    var imageUrl: String? ,
    @SerializedName("no_telp")
    var noTelp: String? ,
    @SerializedName("status")
    var status: Int? ,
    @SerializedName("lat")
    var lat: Double? ,
    @SerializedName("lng")
    var lng: Double?
) : Parcelable