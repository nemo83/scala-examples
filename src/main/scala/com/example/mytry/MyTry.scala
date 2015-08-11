package com.example.mytry

import scala.util.Try

object MyTry {

  def main(args: Array[String]): Unit = {
    val foo = Try{5}
    val bar = foo.flatMap(result => Try{result + 1})
    println(bar.get)
  }

}
