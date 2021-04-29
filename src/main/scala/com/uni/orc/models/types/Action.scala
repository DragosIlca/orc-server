package com.uni.orc.models.types

object Action {
  val CLI_COMMAND: ActionType = "command"
  val DOCKER_COMMAND: ActionType = "docker-command"
  val HTTP_REQUEST: ActionType = "request"

  type ActionType = String
}
