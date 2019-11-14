name := "lightgbm_spark_1"

version := "0.1"

scalaVersion := "2.11.12"


// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3" % "provided"

// com.microsoft.ml.spark:mmlspark:0.18.1
libraryDependencies += "com.microsoft.ml.spark" %% "mmlspark" % "0.18.1"

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case n if n.contains("services") => MergeStrategy.concat
  case n if n.startsWith("reference.conf") => MergeStrategy.concat
  case n if n.endsWith(".conf") => MergeStrategy.concat
  case x => MergeStrategy.first
}