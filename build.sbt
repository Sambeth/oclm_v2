ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val sparkVersion = "3.4.0"
lazy val log4jVersion = "2.11.2"

lazy val root = (project in file("."))
  .settings(
    name := "oclm-v2",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.9.0",
      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql" % sparkVersion,
      "com.typesafe" % "config" % "1.4.2"
    )
  )
