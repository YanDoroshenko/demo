package com.github.yandoroshenko.demo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 14.06.2018.
  */
object Main extends App {
  val conf = new SparkConf().setAppName("demo").setMaster("local")

  val textFile = new SparkContext(conf).textFile("LICENSE")

  println(
    textFile.flatMap(_.split("[^a-zA-Z]").map(_.toLowerCase())).filter(_.nonEmpty)
      .groupBy((s: String) => s).map(p => p._1 -> p._2.size)
      .sortBy(_._2, ascending = false)
      .collect().mkString("\n")
  )
}
