
name := "dfdl-pcap"

organization := "com.tresys"

version := "1.2.0"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  //
  // We want to get rid of this, because it requires a publishLocal or binary server
  // to have this dependency schema present. 
  // instead we want to just pull the dependent schema from github
  //
  // "com.owlcyberdefense" % "dfdl-ethernetip" % "1.2.0",
  "org.apache.daffodil" %% "daffodil-tdml-processor" % "3.2.1" % "test",
  "junit" % "junit" % "4.13.2" % "test",
  "com.github.sbt" % "junit-interface" % "0.13.2" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

crossPaths := false

//
// To express the dependency explicitly we have to create a project and use dependsOn
//

lazy val main = (project in file(".")).dependsOn(ethernetIP)

//
// This is a dependency on github with a specific tag, or can be a branch 
//
lazy val ethernetIP = RootProject(uri("git://github.com/DFDLSchemas/ethernetIP.git#1.2.0"))





