package com.github.uryyyyyyy.kinesis.helloWorld

import java.nio.charset.Charset

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.kinesis.AmazonKinesisClient
import com.amazonaws.services.kinesis.model.{DescribeStreamRequest, GetRecordsRequest, GetShardIteratorRequest}

import scala.collection.JavaConversions._


object KinesisUtil {

	val streamName = "sampleStream"

	def init(): AmazonKinesisClient = {

		val credential = new EnvironmentVariableCredentialsProvider()
		val kinesis = new AmazonKinesisClient(credential)
		kinesis.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1))
		kinesis
	}
}
