package com.uni.orc.runner

import com.uni.orc.models.parsed.Action.Action

trait ActionRunner[T <: Action] {
  def run(action: T): Either[String, Unit]
}
