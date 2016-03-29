package com.github.uryyyyyyy.kinesis.helloWorld

import java.nio.ByteBuffer

import com.amazonaws.services.kinesis.model.PutRecordRequest


object Producer {

	val streamName = "sampleStream"

	def main(args: Array[String]): Unit = {

		val kinesis = KinesisUtil.init()

		val putRecordRequest = new PutRecordRequest()
		putRecordRequest.setStreamName(KinesisUtil.streamName)
		putRecordRequest.setData(ByteBuffer.wrap("hello".getBytes))
		putRecordRequest.setPartitionKey("1")
		val putRecordResult = kinesis.putRecord(putRecordRequest)
		println(putRecordResult)

	}
}
