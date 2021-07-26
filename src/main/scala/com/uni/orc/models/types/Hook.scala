package com.uni.orc.models.types

object Hook {
  val INSTALL: HookType = "start"
  val REMOVE: HookType = "stop"

  type HookType = String
}
