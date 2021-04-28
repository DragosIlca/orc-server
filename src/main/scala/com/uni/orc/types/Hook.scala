package com.uni.orc.types

object Hook {
  val INSTALL: HookType = "install"
  val REMOVE: HookType = "remove"

  type HookType = String
}
