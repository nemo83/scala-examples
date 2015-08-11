package com.example.maps

object MapExamples {

  def main(args: Array[String]): Unit = {

    val countries = Map("France" -> "Paris", "Ireland" -> "Dublin", "Italy" -> "Rome")

    val moreCountries = countries + ("Spain" -> "Madrid")

    println(countries)
    println(moreCountries)

    for {
      (country, capital) <- moreCountries
    } println(s"Country: $country, capital: $capital")

    println()
    println("** **")
    println()

    for {
      (country, capital) <- moreCountries if country.startsWith("I")
    } println(s"Country: $country, capital: $capital")

    println()
    println("** **")
    println()

    val toUpperCase = moreCountries.map(foo => (foo._1.toUpperCase, foo._2.toUpperCase))

    println(toUpperCase)

  }

}
