package com.example.caseclass

sealed abstract class FamilyMember

case class Mother(name: String) extends FamilyMember

case class Father(name: String) extends FamilyMember

case class Sibling(name: String) extends FamilyMember

object FamilyMemberExample {

  def main(args: Array[String]): Unit = {

    val familyMember: FamilyMember = Father("Michele")

    familyMember match {
      case Mother(name) => println(s"Mother $name")
      case Father(name) => println(s"Father $name")
      case Sibling(name) => println(s"Sibling $name")
    }

  }

}
