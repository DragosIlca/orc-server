package com.uni.orc.types

object Action {
  val CLI_COMMAND: ActionType = "command"
  val DOCKER_COMMAND: ActionType = "docker-command"
  val HTTP_REQUEST: ActionType = "request"

  type ActionType = String
}
