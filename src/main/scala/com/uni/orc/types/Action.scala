package com.uni.orc.types

object Action {
  val COMMAND: ActionType = "command"
  val DOCKER_COMMAND: ActionType = "docker-command"

  type ActionType = String
}
