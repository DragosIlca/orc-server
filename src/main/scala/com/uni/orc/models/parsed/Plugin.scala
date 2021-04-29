package com.uni.orc.models.parsed

case class Plugin(id: String, name: String, description: String, version: String, lifecycle: List[Task])
