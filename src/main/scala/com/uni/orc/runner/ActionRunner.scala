package com.uni.orc.runner

import com.uni.orc.models.raw.RawAction

trait ActionRunner[T <: RawAction] {
  def run(action: T)
}
