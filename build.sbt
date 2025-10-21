name := "dfdl-pcap"

organization := "com.tresys"

retrieveManaged := true

useCoursier := false // because of bug, retrieveManaged won't work without this

version := "1.4.1"

daffodilVersion := "4.0.0"

libraryDependencies ++= Seq(
  "com.owlcyberdefense" %% "dfdl-ethernetip" % "1.5.0"
)

enablePlugins(DaffodilPlugin)
