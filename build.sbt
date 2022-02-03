ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

lazy val `custom-updates` = (project in file("."))
  .settings(
    name := "sbt-custom-updates"
  ).enablePlugins(SbtPlugin)


addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.1")

libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "4.6.0.201612231935-r"