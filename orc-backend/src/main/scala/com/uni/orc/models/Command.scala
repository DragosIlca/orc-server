package com.uni.orc.models

case class Command(
	id                 : String,
	name               : String,
	description        : String,
	instruction        : String,
	keyWords           : Option[List[String]],
	configurationFields: Option[List[ConfigurationField]]
)
