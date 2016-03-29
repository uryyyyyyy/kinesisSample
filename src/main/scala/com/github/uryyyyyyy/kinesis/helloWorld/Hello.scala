package com.github.uryyyyyyy.kinesis.helloWorld

import java.nio.charset.Charset

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.kinesis.AmazonKinesisClient
import com.amazonaws.services.kinesis.model.DescribeStreamRequest

import scala.collection.JavaConversions._


object Hello {
	def main(args: Array[String]): Unit = {

		val accessKey = ""
		val secretKey = ""
		val kinesis = new AmazonKinesisClient(new BasicAWSCredentials(accessKey, secretKey))
		//デコーダー
		val decoder = Charset.forName("UTF-8").newDecoder()
		//シャード情報を取得
		val describeStreamRequest = new DescribeStreamRequest()
		describeStreamRequest.setStreamName("kinesis.name")
		val describeStreamResult = kinesis.describeStream(describeStreamRequest)
		val shards =  describeStreamResult.getStreamDescription().getShards()
		shards.foreach(println)

	}
}
