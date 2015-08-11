package com.example.adwidget

import spray.json.{DefaultJsonProtocol, JsonParser}

sealed abstract class BaseJson

case class Person(name: String, age: Int) extends BaseJson


object SparyJsonTest extends DefaultJsonProtocol {

  implicit val colorFormat = DefaultJsonProtocol.jsonFormat2(Person)

  val personJson = """
                     | {
                     |   "name": "Giovanni",
                     |   "age": 32
                     | }
                   """.stripMargin

  def main(args: Array[String]): Unit = {

    val personJs = JsonParser(personJson).convertTo[Person]

    println(personJs)


  }

}
