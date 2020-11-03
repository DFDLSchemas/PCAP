name := "dfdl-pcap"

organization := "com.tresys"

version := "1.0.0"

scalaVersion := "2.12.14"

libraryDependencies ++= Seq(
  "com.owlcyberdefense" % "dfdl-ethernetip" % "1.0.0",
  "org.apache.daffodil" %% "daffodil-tdml-processor" % "3.1.0" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "junit" % "junit" % "4.12" % "test",
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

crossPaths := false
