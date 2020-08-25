package nstv.compositevmchannel.data.model

import com.google.gson.annotations.SerializedName

data class Elephant(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("affiliation") val affiliation: String,
    @SerializedName("species") val species: String,
    @SerializedName("sex") val sex: String,
    @SerializedName("fictional") val fictional: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("note") val summary: String,
    @SerializedName("wikilink") val wikiUrl: String,
    val isFavorite: Boolean
)
