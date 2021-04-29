package com.uni.orc.models.types

object Hook {
  val INSTALL: HookType = "install"
  val REMOVE: HookType = "remove"

  type HookType = String
}
