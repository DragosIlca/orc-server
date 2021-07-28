package com.uni.orc.models

case class MarketPlugin(
	id          : String,
	name        : String,
	description : String,
	version     : String,
	installation: List[String],
	lifecycle   : List[Task],
	execution   : List[Command]
)
