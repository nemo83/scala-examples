package com.example.adwidget.two

import spray.json._

case class AdWidgetRequest(assemblyParameters: AssemblyParameters,
                           relevanceParameters: RelevanceParameters)

case class AssemblyParameters(numDeals: Int, ipAddress: String)

case class RelevanceParameters(affiliateId: Long,
                               signal: String,
                               categories: Map[String, List[String]],
                               fallback: String,
                               safe: Boolean)

object MyJsonProtocol extends DefaultJsonProtocol {

  implicit val assemblyParametersFormat = jsonFormat(AssemblyParameters, "num_deals", "ip_address")

  implicit val relevanceParametersFormat = jsonFormat(RelevanceParameters, "affiliateId", "signal", "categories", "fallback", "safe")

  implicit val adWidgetRequestFormat = jsonFormat(AdWidgetRequest, "assembly_parameters", "relevance_parameters")

}

object AdWidget {

  val request =
    """
      |{
      |  "assembly_parameters":{
      |    "num_deals": 3,
      |    "ip_address": "72.229.28.185"
      |  },
      |  "relevance_parameters":{
      |    "affiliateId": 200001,
      |    "signal": "relevance",
      |    "categories": {
      |      "c09790ba-a6b9-40fc-ad81-4cdf25260b5e": ["f052f491-36c2-406f-a196-be2c59d281f4"]
      |    },
      |    "fallback": "c09790ba-a6b9-40fc-ad81-4cdf25260b5e",
      |    "safe": false
      |  }
      |}
    """.stripMargin

  import MyJsonProtocol._

  def main(args: Array[String]): Unit = {

    val adWidgetRequest = JsonParser(request).convertTo[AdWidgetRequest]

    println(adWidgetRequest)

  }


  def theMappingService(request: AdWidgetRequest): List[(String, String)] = {
    Nil
  }

  trait RequestParameterMapping[T <: AdWidgetRequest] {
    def toRequestParameters(request: T): List[(String, String)]
  }

  class GeoLocationRequestParameterMapping extends RequestParameterMapping[AdWidgetRequest] {
    override def toRequestParameters(request: AdWidgetRequest): List[(String, String)] = {
      Nil
    }
  }


}
