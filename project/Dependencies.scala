import sbt._

object Dependencies {
  lazy val akkaVersion =  "2.4.0-RC1"
  lazy val akkaStreamVersion = "1.0"

  // Akka
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion % Compile
  val akkaCluster = "com.typesafe.akka" %% "akka-cluster" % akkaVersion % Compile
  val akkaClusterMetrics = "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion % Compile
  val akkaClusterTools = "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion % Compile
  val akkaPersistence = "com.typesafe.akka" %% "akka-persistence-experimental" % akkaVersion % Compile
  val akkaContrib = "com.typesafe.akka" %% "akka-contrib" % akkaVersion % Compile
  val akkaCamel = "com.typesafe.akka" %% "akka-camel" % akkaVersion % Compile
  val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  val akkaMultiNodeTestKit = "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion % Test

  // Akka streams:
  val akkaStream = "com.typesafe.akka" %% "akka-stream-experimental" % akkaStreamVersion % Compile
  val akkaHttp = "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamVersion % Compile
  val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamVersion % Compile
  val akkaStreamTestKit = "com.typesafe.akka" %% "akka-stream-testkit-experimental" % akkaStreamVersion % Test

  // Others
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.4" % Test
  val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % Test

  val coreDeps = Seq(scalaTest, scalaMock)
  val etlDeps = Seq(akkaCluster, akkaClusterTools)
  val backendDeps = Seq(akkaCluster, akkaClusterTools)
  val clusterTestsDeps = Seq(akkaCluster, akkaClusterTools, akkaTestKit, akkaMultiNodeTestKit) ++ coreDeps
}

   


