package com.calori.dataproducer

object Constant {
  val nutrientsNamingMap = Map(
    1004L -> "Fat",
    1005L -> "Carbohydrate",
    1109L -> "Vitamin E",
    1165L -> "Vitamin B1(Thiamin)",
    1166L -> "Vitamin B2(Riboflavin)",
    1167L -> "Vitamin B3(Niacin)",
    1177L -> "Vitamin B9(Folate)",
    1185L -> "Vitamin K",
    1258L -> "Saturated Fatty Acids",
    1292L -> "Monounsaturated fatty acids",
    1293L -> "Polyunsaturated fatty acids",
    2000L -> "Sugars",
    1079L -> "Fiber",
    1162L -> "Vitamin C",
    1178L -> "Vitamin B12",
    1175L -> "Vitamin B6"
  )
  val excludedNutrientIds: Seq[Long] = Seq(1120, 1122, 1123, 1180, 1187, 1090, 1259, 1260, 1261, 1262, 1263, 1264, 1265, 1266, 1268, 1269, 1270, 1271, 1272, 1275, 1276, 1277, 1278, 1279, 1280)


}
