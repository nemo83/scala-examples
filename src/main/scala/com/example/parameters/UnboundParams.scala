package com.example.parameters

object UnboundParams {


  def main(args: Array[String]): Unit = {

    println(foo("ciao", "mamma", "guarda", "come", "mi", "diverto").mkString("Jovanotti: ", " ", " '95"))

    println(foo())

  }


  def foo(strings: String*) = {
    strings.toList
  }

}
