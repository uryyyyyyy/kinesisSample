package com.github.uryyyyyyy.kinesis.helloWorld

import java.nio.charset.Charset

import com.amazonaws.auth.{EnvironmentVariableCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.{Regions, Region}
import com.amazonaws.services.kinesis.AmazonKinesisClient
import com.amazonaws.services.kinesis.model.DescribeStreamRequest

import scala.collection.JavaConversions._


object Hello {

	val streamName = "sampleStream"

	def main(args: Array[String]): Unit = {

		val credential = new EnvironmentVariableCredentialsProvider()
		val kinesis = new AmazonKinesisClient(credential)
		kinesis.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1))
		//デコーダー
		val decoder = Charset.forName("UTF-8").newDecoder()
		//シャード情報を取得
		val describeStreamRequest = new DescribeStreamRequest()
		describeStreamRequest.setStreamName(streamName)
		val describeStreamResult = kinesis.describeStream(describeStreamRequest)
		val shards =  describeStreamResult.getStreamDescription().getShards()
		shards.foreach(println)

	}
}
