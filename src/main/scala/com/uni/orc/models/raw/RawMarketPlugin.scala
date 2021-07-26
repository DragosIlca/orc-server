package com.uni.orc.models.raw

import com.uni.orc.models.common.Command

case class RawMarketPlugin(
	id          : String,
	name        : String,
	description : String,
	version     : String,
	installation: List[String],
	lifecycle   : List[RawTask],
	commands    : List[Command]
)
