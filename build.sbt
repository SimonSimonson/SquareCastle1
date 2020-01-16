name := "SquareCastle1"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "junit" % "junit" % "4.8" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
