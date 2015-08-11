package com.example.loops

object SomeLoops {


  def main(args: Array[String]): Unit = {

    for {
      i <- 0 until 5
    } println(s"index: $i")

    println()
    println(" ** ** ")
    println()

    for {
      i <- 0 to 5
    } println(s"index: $i")

    println()
    println(" ** ** ")
    println()

    val fruits = List("pears", "bananas", "apples")

    for {
      (fruit, index) <- fruits.zipWithIndex
    } println(s"fruit: $fruit and index: $index")

  }

}
