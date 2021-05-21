package com.uni.orc.models.types

object Action {
  val CLI_COMMAND: ActionType = "cli"
  val DOCKER_COMMAND: ActionType = "docker"
  val HTTP_REQUEST: ActionType = "request"

  type ActionType = String
}
