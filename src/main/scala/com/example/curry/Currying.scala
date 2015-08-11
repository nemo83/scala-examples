package com.example.curry

object Currying {

  def main(args: Array[String]): Unit = {

    unitToUnit(println("during"))

    println("")
    println("** **")
    println("")

    stringToUnit(println _)

    println("")
    println("** **")
    println("")

    val myList = List(1, 2, 3)

    println("")
    println("** **")
    println("")

    println(sum(myList))

    println("")
    println("** **")
    println("")

    println(mult(myList))

  }

  def stringToUnit(unit: String => Unit): Unit = {
    println("before")
    unit("bla")
    println("after")
  }


  def unitToUnit(unit: => Unit): Unit = {
    println("before")
    unit
    println("after")
  }

  def curryOp(op: (Int, Int) => Int, initial: Int)(numbers: List[Int]): Int = {
    numbers.foldLeft(initial)(op)
  }

  // With explicity signature and forward of params
  def sum(numbers: List[Int]) = curryOp(_ + _, 0)(numbers)

  // With _ shortcut
  def mult = curryOp(_ * _, 1)(_)
}
