name := "orc-server"
organization in ThisBuild := "com.uni.orc"
scalaVersion in ThisBuild := "2.13.1"

lazy val global = project
  .in(file("."))
  .settings(commonSettings)
  .enablePlugins(PlayScala)
  .aggregate(
    `orc-backend`,
    `orc-rest`
    )

lazy val `orc-backend` = project
  .enablePlugins(PlayScala)
  .settings(
    name := "orc-backend",
    commonSettings,
    libraryDependencies ++= commonDependencies ++ Seq(
      "org.typelevel" %% "cats-core" % "2.6.0",
      "org.typelevel" %% "cats-effect" % "2.1.3",
      "ai.x" %% "play-json-extensions" % "0.40.2"
      )
  )

lazy val `orc-rest` = project
  .settings(
    name := "orc-rest",
    commonSettings
  )

lazy val compilerOptions = Seq(
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-deprecation",
  "-encoding",
  "utf8"
  )

val commonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.9" % "test",
  "log4j" % "log4j" % "1.2.7"
  )

lazy val commonSettings = Seq(
  scalacOptions ++= compilerOptions,
  resolvers ++= Seq(
    "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
    )
  )
