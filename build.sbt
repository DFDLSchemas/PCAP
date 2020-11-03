name := "dfdl-pcap"

organization := "com.tresys"

version := "0.0.2"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "org.apache.daffodil" %% "daffodil-tdml-processor" % "3.1.0-SNAPSHOT" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "junit" % "junit" % "4.12" % "test",
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

crossPaths := false
