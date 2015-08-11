package com.example

import java.io.File

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.io.Source

object AdWidgetRequestScript {

  //  val MDS_HTTP_CALL = "http://marketing-dealservice-vip.snc1/deals.json?country=%s&fields=category,sub_category,margin,ts_created,options,channel"

  def main(args: Array[String]): Unit = {

    val requests = Source.fromFile("src/main/scala/com/example/requests.csv").getLines()

    val adWidgetRequests = requests.filter(string => string.trim.length > 0 && string.trim != ",,,,,,,").map(request => {

      val paramRequest = request.split(",")
      val ipAddress = paramRequest(0).replaceAll("\"", "")
      val numDeals = paramRequest(1).toInt
      val assemblyParameters = AssemblyParameters(numDeals, ipAddress)
      val affiliateId = paramRequest(7).toLong
      val countryId = paramRequest(2).replaceAll("\"", "")
      val divisionId = paramRequest(3).replaceAll("\"", "")
      val fallback = paramRequest(5).replaceAll("\"", "")
      val categories = categoriesToMap(paramRequest(4).replaceAll("\"", ""))
      val relevanceParameters = RelevanceParameters(affiliateId, "relevance", countryId, divisionId, categories, fallback, true)
      Request(assemblyParameters, relevanceParameters)
    })

    val mapper = new ObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    mapper.registerModule(DefaultScalaModule)

    for {
      (request, index) <- adWidgetRequests.zipWithIndex
      if index <= 5
    } println(mapper.writeValueAsString(request))

  }


  def main2(args: Array[String]): Unit = {
    val foo = "{c09790ba-a6b9-40fc-ad81-4cdf25260b5e=[f052f491-36c2-406f-a196-be2c59d281f4]}"
    println(categoriesToMap(foo))
  }

  def categoriesToMap(categories: String): Map[String, List[String]] = {
    categories match {
      case cat: String if cat.length <= 2 => Map()
      case cat: String if cat.indexOf("[") - cat.indexOf("]") + 1 == 0 => {
        Map(cat.substring(1, cat.indexOf("=")) -> Nil)
      }
      case cat: String => Map(cat.substring(1, cat.indexOf("=")) -> List(cat.substring(cat.
        indexOf("[") + 1, cat.indexOf("]"))))
    }
  }

  case class Request(@JsonProperty("assembly_parameters") assemblyParameters: AssemblyParameters,
                     @JsonProperty("relevance_parameters") relevanceParameters: RelevanceParameters)

  case class AssemblyParameters(@JsonProperty("num_deals") numDeals: Int, @JsonProperty("ip_address") ipAddress: String)

  case class RelevanceParameters(affiliateId: Long,
                                 signal: String,
                                 countryId: String,
                                 divisionId: String,
                                 categories: Map[String, List[String]],
                                 fallback: String,
                                 safe: Boolean)

}
