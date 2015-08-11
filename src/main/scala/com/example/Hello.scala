package com.example

object Hello {

  case class Foobar(id: Long)

  def main(args: Array[String]): Unit = {

    println("Hello, world!")


    val foo = Foobar(5)

    println(s"ciao-${foo.id}")
  }

}
