package com.example.curry

import org.scalatest.{FlatSpec, Matchers}

class CurryingTest extends FlatSpec with Matchers {

  "Currying" should "multiply values in list" in {
    Currying.mult(List(2, 2, 3)) should be === 12
  }

  it should "return zero if a zero in the lists" in {
    Currying.mult(List(1, 0, 3)) should be === 0
  }

  it should "add values in list " in {
    Currying.sum(List(2, 2, 3)) should be === 7
  }

  it should "add values in list with negative numbers" in {
    Currying.sum(List(-2, 2, 3)) should be === 3
  }

}
