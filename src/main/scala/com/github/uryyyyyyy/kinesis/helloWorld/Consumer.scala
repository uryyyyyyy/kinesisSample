package com.github.uryyyyyyy.kinesis.helloWorld

import java.nio.charset.Charset

import com.amazonaws.services.kinesis.model.{DescribeStreamRequest, GetRecordsRequest, GetShardIteratorRequest}

import scala.collection.JavaConversions._


object Consumer {

	def main(args: Array[String]): Unit = {

		val kinesis = KinesisUtil.init()

		//デコーダー
		val decoder = Charset.forName("UTF-8").newDecoder()
		//シャード情報を取得
		val describeStreamRequest = new DescribeStreamRequest()
		describeStreamRequest.setStreamName(KinesisUtil.streamName)
		val describeStreamResult = kinesis.describeStream(describeStreamRequest)
		val shards =  describeStreamResult.getStreamDescription().getShards()
		shards.foreach(println)


		val shard = shards.get(0)
		val iterReq = new GetShardIteratorRequest()
		iterReq.setStreamName(KinesisUtil.streamName)
		iterReq.setShardId(shard.getShardId())
		// シャードの最初からデータを取得
		iterReq.setShardIteratorType("TRIM_HORIZON")
		//シャードイテレータ取得
		val iteratorRes = kinesis.getShardIterator(iterReq)
		var shardIterator = iteratorRes.getShardIterator()

		while (true) {
			//シャードイテレータを使ってレコード取得
			val getRecordsRequest = new GetRecordsRequest()
			getRecordsRequest.setShardIterator(shardIterator)
			val result = kinesis.getRecords(getRecordsRequest)
			//レコードのリストを取得
			val records = result.getRecords()
			for (r <- records) {
				//取得したデータをデコードして表示
				System.out.println(decoder.decode(r.getData()).toString())
			}
			//レコードを取得間隔をあけるためsleep
			Thread.sleep(1000)

			//次のシャードイテレータ取得（データ終端でも取得できるので注意)
			shardIterator = result.getNextShardIterator()
		}

	}
}
