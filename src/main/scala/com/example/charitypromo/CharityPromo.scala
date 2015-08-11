package com.example.charitypromo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import dispatch.Defaults._
import dispatch._

import scala.util.{Failure, Success}

object CharityPromo {

  val MDS_HTTP_CALL = "http://marketing-dealservice-vip.snc1/deals.json?country=%s&fields=category,sub_category,margin,ts_created,options,channel"

  def main(args: Array[String]): Unit = {

    val ieDeals = MDS_HTTP_CALL.format("IE").concat("&size=20")

    val svc = url(ieDeals)
    val country = Http(svc OK as.String)

    val mapper = new ObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    mapper.registerModule(DefaultScalaModule)

    country.onComplete(result => result match {
      case Success(deals) => {
        println(s"Deals:\n$deals")
        val mdsDeals = mapper.readValue(deals, classOf[Array[MdsDeal]])
        mdsDeals.foreach(println)
      }
      case Failure(t) => println(s"Error: ${t.getMessage}")
    })


  }

//  # if 'margin' in deal.keys() and 'options' in deal.keys():
//  #     marginCheapestOption = float(deal['margin'])
//  #     unitBuyPrice = getCheapestOptionPrice(deal)
//  #     if marginCheapestOption <= 0.0:
//  #         return LIST_5_CATEGORY
//  #     elif unitBuyPrice > marginCheapestOption > 0 and marginCheapestOption / unitBuyPrice < FIVE_PERCENT:
//  #         return LIST_5_CATEGORY

//  @JsonCreator
  case class MdsDeal(@JsonProperty("dl_permalink") permalink: String, margin: Double)

}
