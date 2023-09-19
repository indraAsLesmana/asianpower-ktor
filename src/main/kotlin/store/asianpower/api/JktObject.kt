package store.asianpower.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val img: String,
    val name: String,
    val price: String,
    @SerialName("price-coret")
    val priceCoret: String,
    val url: String
)