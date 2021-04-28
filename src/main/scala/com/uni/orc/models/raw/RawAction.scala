package com.uni.orc.models.raw

import com.uni.orc.models.parsed.Config._
import com.uni.orc.types.Action.ActionType

case class RawAction(actionType: ActionType, instruction: String, config: Config)
