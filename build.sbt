import Dependencies._

lazy val sharedSettings = Seq(
  scalacOptions ++= Seq(
    "-Xfatal-warnings",
    "-Xlint:unused"
  ),
  scalaVersion := "2.12.8",
  version := "1.0-SNAPSHOT"
)

lazy val root = (project in file("."))
  .settings(
    libraryDependencies ++= Seq(scalatest),
    name := "tenniskata",
    organization := "fr.xebia",
    sharedSettings,
  )
