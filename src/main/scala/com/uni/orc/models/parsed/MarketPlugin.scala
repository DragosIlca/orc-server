package com.uni.orc.models.parsed

import com.uni.orc.models.common.Command

case class MarketPlugin(
	id          : String,
	name        : String,
	description : String,
	version     : String,
	installation: List[String],
	lifecycle   : List[Task],
	execution   : List[Command]
)
