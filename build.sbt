name := "timer"

organization := "com.kindone"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  //"com.kindone" %% "crosslib" % "0.1-SNAPSHOT"
)

lazy val root = project.in(file(".")).
  aggregate(crossJS, crossJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val crosslib = crossProject.in(file(".")).
  settings(
    name := "timer",
    organization := "com.kindone",
    version := "0.1-SNAPSHOT"
  ).
  jvmSettings(
    // Add JVM-specific settings here
  ).
  jsSettings(
    // Add JS-specific settings here
  )

lazy val crossJVM = crosslib.jvm
lazy val crossJS = crosslib.js
