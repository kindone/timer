lazy val projectName = "timer"

lazy val projectOrganization = "com.kindone"

lazy val projectVersion = "0.3-SNAPSHOT"

name := projectName

organization := projectOrganization

version := projectVersion

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
    name := projectName,
    organization := projectOrganization,
    version := projectVersion
  ).
  jvmSettings(
    // Add JVM-specific settings here
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.4" % "test",
      "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % "test"
    )
  ).
  jsSettings(
    // Add JS-specific settings here
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % "3.0.4" % "test",
      "org.scalamock" %%% "scalamock-scalatest-support" % "3.6.0" % "test"
    )
  )

lazy val crossJVM = crosslib.jvm
lazy val crossJS = crosslib.js
