import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.marekkadek",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= coreDeps
    )

lazy val etl = (project in file("etl")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= etlDeps
    ).
  dependsOn(core)

lazy val backend = (project in file("backend")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= backendDeps
    ).
  dependsOn(core)
