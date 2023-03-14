package com.example.calculator.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperheroResponseDetail(
    @SerializedName("name") val name: String,
    @SerializedName("powerStats") val powerStats: PowerStatsResponse,
    @SerializedName("image") val image: SuperheroImageDetailResponse
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class SuperheroImageDetailResponse(
    @SerializedName("url") val url:String
)