package com.example.adwidget.one

import spray.json._

case class AdWidgetRequest(assemblyParameters: AssemblyParameters,
                           relevanceParameters: RelevanceParameters)

case class AssemblyParameters(numDeals: Int, ipAddress: String)

case class RelevanceParameters(affiliateId: Long,
                               signal: String,
                               fallback: String,
                               safe: Boolean)

object MyJsonProtocol extends DefaultJsonProtocol {

  implicit object AssemblyParametersFormat extends JsonFormat[AssemblyParameters] {
    override def write(obj: AssemblyParameters): JsValue = JsObject(
      "num_deals" -> JsNumber(obj.numDeals),
      "ip_address" -> JsString(obj.ipAddress)
    )

    override def read(json: JsValue): AssemblyParameters = {
      json.asJsObject.getFields("num_deals", "ip_address") match {
        case Seq(JsNumber(numDeals), JsString(ipAddress)) => AssemblyParameters(numDeals.toInt, ipAddress)
        case _ => throw new DeserializationException("AssemblyParameters expected")
      }
    }
  }

  implicit object RelevanceParametersFormat extends JsonFormat[RelevanceParameters] {
    override def write(obj: RelevanceParameters): JsValue = JsObject(
      "affiliateId" -> JsNumber(obj.affiliateId),
      "signal" -> JsString(obj.signal),
      "fallback" -> JsString(obj.fallback),
      "safe" -> JsBoolean(obj.safe)
    )

    override def read(json: JsValue): RelevanceParameters = {
      json.asJsObject.getFields("affiliateId", "signal", "fallback", "safe") match {
        case Seq(JsNumber(affiliateId), JsString(signal), JsString(fallback), JsBoolean(safe)) => RelevanceParameters(affiliateId.toLong, signal, fallback, safe)
        case _ => throw new DeserializationException("RelevanceParameters expected")
      }
    }
  }

  implicit val adWidgetRequestFormat = jsonFormat(AdWidgetRequest, "assembly_parameters", "relevance_parameters")

}

object AdWidget {

  val request =
  //    "categories": {
  //      "c09790ba-a6b9-40fc-ad81-4cdf25260b5e": ["f052f491-36c2-406f-a196-be2c59d281f4"]
  //    },
    """
      |{
      |  "assembly_parameters":{
      |    "num_deals": 3,
      |    "ip_address": "72.229.28.185"
      |  },
      |  "relevance_parameters":{
      |    "affiliateId": 200001,
      |    "signal": "relevance",
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

}
