package com.uni.orc.models.raw

case class RawPlugin(id: String, name: String, description: String, version: String, lifecycle: List[RawTask])
