package com.ellska.tutorials.google_content_api

import com.google.api.services.content.ShoppingContent
import com.google.api.services.content.model.Price
import com.google.api.services.content.model.Product
import com.google.api.services.content.model.ProductShipping
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class GoogleContentApiService(
  private val shoppingContent: ShoppingContent
) {
  fun createOrUpdateProduct() {
    val contentProduct = Product()
      .setOfferId("Product offer id")
      .setTitle("Product title")
      .setDescription("Product description")
      .setLink("https://url-to-your-product")
      .setImageLink("https://image-url")
      .setChannel("online")
      .setContentLanguage("sv")
      .setTargetCountry("SE")
      .setAvailability("in stock")
      .setCondition("new")
      .setPrice(
        Price()
          .setValue("1337")
          .setCurrency("SEK")
      )
      .setShipping(
        listOf(
          ProductShipping()
            .setPrice(
              Price()
                .setValue("20")
                .setCurrency("SEK")
            )
            .setCountry("SE")
            .setService("Brev")
        )
      )
    shoppingContent
      .products()
      .insert(BigInteger("YOUR_MERCHANT_ID"), contentProduct)
      .execute()
  }
}