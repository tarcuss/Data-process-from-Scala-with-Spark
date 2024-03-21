package com.calori.dataproducer

import com.calori.dataproducer.Constant.{excludedNutrientIds, nutrientsNamingMap}
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{lit, row_number}

object ProducerJob  extends App{

    val sparkSession = SparkSession.builder()
      .master("local")
      .appName("Hello Boy")
      .getOrCreate()

    import sparkSession.implicits._

    def removeExcludedNutrientIds (rawData: Dataset[RawData] ): Dataset[Data] =
      rawData
        .flatMap(_.SurveyFoods)
        .map(r => r.copy(foodNutrients = r.foodNutrients.filter(fn => !excludedNutrientIds.contains(fn.nutrient.get.id.get) && fn.amount >= 0.01)))

    def addId (withoutIDData: Dataset[Data]): Dataset[DataWithId] = {
      val window = Window.partitionBy("tmp").orderBy("description")

      withoutIDData
        .withColumn("tmp", lit(1))
        .withColumn("id", row_number().over(window))
        .drop("tmp")
        .as[DataWithId]
    }

    def renameNutrientNames(unreformedData: Dataset[DataWithId]): Dataset[DataWithId] =
      unreformedData
          .map {
            unreformedData =>
              unreformedData.copy(foodNutrients = unreformedData.foodNutrients.map { fn =>
                fn.copy(nutrient = fn.nutrient.map(r => r.copy(name = Some(nutrientsNamingMap.getOrElse(r.id.get, r.name.get)))))
              })
          }

    val calorie = sparkSession.read.option("multiline", true).json("data/fs.json").as[RawData]
      .transform(removeExcludedNutrientIds)
      .transform(addId)
      .transform(renameNutrientNames)
      .cache

    Seq(ProcessedData(calorie.collect().toList)).toDS().map(r => r).write.json("data/resultData-17")
    Seq(ProcessedData(calorie.filter(_.description.contains(" NFS")).collect().toList)).toDS().map(r => r).write.json("data/resultData-18")


}
