val root = (project in file("."))
  .settings(
    name := "dfdl-pcap",

    organization := "com.tresys",

    version := "1.4.1",

    libraryDependencies ++= Seq(
      ("com.owlcyberdefense" % "dfdl-ethernetip" % "1.5.0").daffodilPlugin(daffodilVersion.value)
    )
  )
  .daffodilProject(crossDaffodilVersions = Seq("4.0.0"))
