package com.uni.orc.models

case class ConfigurationField(
	id         : String,
	name       : String,
	valueType  : String,
	description: String,
	required   : Boolean
)
