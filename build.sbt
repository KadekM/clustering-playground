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

// multi-jvm

import com.typesafe.sbt.SbtMultiJvm
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

lazy val clusterTests = (project in file("cluster-tests")).
settings(commonSettings: _*).
settings(Project.defaultSettings ++ SbtMultiJvm.multiJvmSettings).
settings(
  libraryDependencies ++= clusterTestsDeps,
    compile in MultiJvm <<= (compile in MultiJvm) triggeredBy (compile in Test),
    // disable parallel tests
    parallelExecution in Test := false,
    // make sure that MultiJvm tests are executed by the default test target,
    // and combine the results from ordinary test and multi-jvm tests
    executeTests in Test <<= (executeTests in Test, executeTests in MultiJvm) map {
      case (testResults, multiNodeResults) =>
        val overall =
          if (testResults.overall.id < multiNodeResults.overall.id)
            multiNodeResults.overall
          else
            testResults.overall
        Tests.Output(overall,
          testResults.events ++ multiNodeResults.events,
          testResults.summaries ++ multiNodeResults.summaries)
    }
).
dependsOn(core) configs MultiJvm

