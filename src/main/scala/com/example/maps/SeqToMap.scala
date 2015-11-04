package com.example.maps

object SeqToMap {

  def main(args: Array[String]) {
    val foo: Seq[String] = List("ciao", "mamma", "guarda", "come")

    // Going for a Seq of items to a Map requires the toMap in the end, or it will
    // just return a Seq of Tuples.
    val bar = foo.map(item => item -> item.size).toMap
    println(bar)
  }

}
