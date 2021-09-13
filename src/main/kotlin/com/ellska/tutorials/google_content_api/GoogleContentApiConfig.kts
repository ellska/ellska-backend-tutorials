package com.ellska.tutorials.google_content_api

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.content.ShoppingContent
import com.google.api.services.content.ShoppingContentScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GoogleContentApiConfig {
  val apiCredentials: GoogleCredentials by lazy {
    GoogleCredentials
      .fromStream("YOUR_API_KEY".byteInputStream())
  }

  @Bean
  fun shoppingContent(): ShoppingContent {
    return ShoppingContent(
      GoogleNetHttpTransport.newTrustedTransport(),
      JacksonFactory.getDefaultInstance(),
      HttpCredentialsAdapter(
        apiCredentials.createScoped(
          googleOAuth2Scopes()
        )
      )
    )
  }

  private fun googleOAuth2Scopes() = setOf(
    ShoppingContentScopes.CONTENT
  )
}  