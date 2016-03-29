name := """kinesisClient"""

version := "1.0"

organization := "com.github.uryyyyyyy"
scalaVersion := "2.11.7"
libraryDependencies ++= Seq(
	"com.amazonaws" % "aws-java-sdk-kinesis" % "1.10.64",
	"org.scalatest" %% "scalatest" % "3.0.0-M15" % "test"
)