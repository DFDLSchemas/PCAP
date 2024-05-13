name := "dfdl-pcap"

organization := "com.tresys"

retrieveManaged := true

useCoursier := false // because of bug, retrieveManaged won't work without this

version := "1.4.0"

scalaVersion := "2.12.18"

daffodilVersion := "3.8.0"

libraryDependencies ++= Seq(
  //
  // We want to get rid of this, because it requires a publishLocal or binary server
  // to have this dependency schema present.
  // instead we want to just pull the dependent schema from github
  //
  "com.owlcyberdefense" %% "dfdl-ethernetip" % "1.4.0"
)

//
// To express the dependency explicitly we have to create a project and use dependsOn
//

// lazy val main = (project in file(".")).dependsOn(ethernetIP)

//
// This is a dependency on github with a specific tag, or can be a branch
//
// lazy val ethernetIP = RootProject(uri("git://github.com/DFDLSchemas/ethernetIP.git#1.2.0"))

enablePlugins(DaffodilPlugin)
