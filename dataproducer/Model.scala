package com.calori.dataproducer

case class RawData(SurveyFoods: List[Data])

case class ProcessedData(SurveyFoods: List[DataWithId])

case class Data(description: String, foodNutrients: List[FoodNutrients] /*,foodPortions:List[FoodPortion],inputFoods:List[InputFood]*/)

case class DataWithId(description: String, foodNutrients: List[FoodNutrients], id: Option[Long] /*,foodPortions:List[FoodPortion],inputFoods:List[InputFood]*/)

case class FoodNutrients(nutrient: Option[Nutrient], amount: Double)

case class Nutrient(id: Option[Long], name: Option[String], unitName: Option[String])
//case class FoodPortion(gramWeight:Double, portionDescription:String)
//case class InputFood(foodDescription:String)
