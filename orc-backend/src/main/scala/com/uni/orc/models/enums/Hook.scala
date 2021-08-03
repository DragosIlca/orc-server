package com.uni.orc.models.enums

object Hook {
  val INSTALL: HookType = "start"
  val REMOVE: HookType = "stop"

  type HookType = String
}
