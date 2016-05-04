import org.sbtidea.SbtIdeaPlugin._

name := "akkaPersistence"

version := "1.0"

mainClass in (Compile, run) := Some("com.deere.Main")

val akkaVersion = "2.3.10"

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.typesafe.akka" %% "akka-persistence-experimental" % akkaVersion,
    "com.github.ironfish" %% "akka-persistence-mongo-casbah"  % "0.7.6",
    "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit"  % akkaVersion% "test",
    "org.scalatest" % "scalatest_2.11" % "2.2.1"% "test"
)

