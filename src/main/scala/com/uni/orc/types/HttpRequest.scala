package com.uni.orc.types

object HttpRequest {
  val GET: HttpRequestType = "GET"
  val PUT: HttpRequestType = "PUT"
  val POST: HttpRequestType = "POST"
  val DELETE: HttpRequestType = "DELETE"

  type HttpRequestType = String
}
