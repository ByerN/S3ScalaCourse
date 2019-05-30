package org.s3.training.lesson1

import scala.collection.immutable.TreeSet
import scala.collection.mutable
import scala.util.Try

object Collections {

  def main(args: Array[String]): Unit = {
    //https://docs.scala-lang.org/overviews/collections/overview.html

    //common immutable collections
    var array: Array[String] = Array("A", "B", "C") //Array

    var sequence: Seq[String] = Seq("A", "B", "C") // java: List interface (immutable) - scala: default implementation is List
    var list: List[String] = List("A", "B", "C") // java: LinkedList (immutable)
    var vector: Vector[String] = Vector("A", "B", "C") //java: ArrayList (immutable)

    var map: Map[String, Int] = Map("A" -> 1, "B" -> 2, "C" -> 3) //java: Map interface - scala: default implementation is HashMap
    //var hashMap: HashMap[String, Int] = HashMap("A" -> 1, "B" -> 2, "C" -> 3)

    var set: Set[String] = Set("Cat", "Dog", "Turtle") //java: Set interface - scala: default implementation is HashSet
    //var hashSet: HashSet[String] = HashSet("Cat", "Dog", "Turtle")

    //mutable collections
    var mutableSequence: mutable.Seq[String] = mutable.Seq("A", "B", "C") // java: List interface - scala: default implementation is ArrayBuffer
    var mutableList: mutable.MutableList[String] = mutable.MutableList("A", "B", "C") // java: LinkedList
    var mutableVector: mutable.ArrayBuffer[String] = mutable.ArrayBuffer("A", "B", "C") //java: ArrayList

    var mutableMap: mutable.Map[String, Int] = mutable.Map("A" -> 1, "B" -> 2, "C" -> 3) //java: Map interface - scala: default implementation is mutable.HashMap
    //var mutableHashMap: mutable.HashMap[String, Int] = mutable.HashMap("A"           ->           1, "B"           ->           2, "C"           ->           3)

    var mutableSet: mutable.Set[String] = mutable.Set("Cat", "Dog", "Turtle") //java: Set interface - scala: default implementation is mutable.HashSet
    //var mutableHashSet: mutable.HashSet[String] = mutable.HashSet("Cat", "Dog", "Turtle")


    println()
    println("SEQUENCE OPERATIONS:")
    println("IMMUTABLE:")

    var animals = Seq("Cat", "Dog", "Turtle")
    println("Seq(\"Cat\", \"Dog\", \"Turtle\")               ->           " + animals)

    animals :+= "Mouse" // append
    println("animals :+= \"Mouse\"                       ->           " + animals)


    animals +:= "Parrot" // prepend
    println("animals +:= \"Parrot\"                      ->           " + animals)


    animals ++= Seq("Rat", "Duck") // collection append
    println("animals ++= Seq(\"Rat\", \"Duck\")            ->           " + animals)


    animals ++:= Seq("Eagle", "Owl") // collection prepend
    println("animals ++:= Seq(\"Eagle\", \"Owl\")          ->           " + animals)


    animals = animals.filterNot(_ == "Dog") //remove elements
    println("animals = animals.filterNot(_ == \"Dog\")   ->           " + animals)


    val firstAnimal = animals.head
    val restOfAnimals = animals.tail
    val thirdAnimal = animals(2)
    val maybeAnimalStartingWithD = animals.find(_.startsWith("D"))
    val allAnimalsStartingWithC = animals.filter(_.startsWith("C"))

    println("animals.head   ->           " + animals.head)
    println("animals.tail   ->           " + animals.tail)
    println("animals(2)     ->           " + animals(2))
    println("animals.find(_.startsWith(\"D\"))     ->           " + animals.find(_.startsWith("D")))
    println("animals.map(_ + \"!\")                ->           " + animals.map(_ + "!"))
    println("animals.filter(_.startsWith(\"C\"))   ->           " + animals.filter(_.startsWith("C")))


    println("MUTABLE:")
    val mutableAnimals = mutable.ListBuffer("Cat", "Dog", "Turtle")
    mutableAnimals += "Mouse" // append
    println("mutableAnimals += \"Mouse\" -> " + mutableAnimals)
    mutableAnimals -= "Mouse" // append
    println("mutableAnimals -= \"Mouse\" -> " + mutableAnimals)


    println("MAP:")
    var animalMapping = Map(
      "Cat" -> 1,
      "Dog" -> 2,
      "Mouse" -> 3
    )

    println("animalMapping : " + animalMapping)
    println("animalMapping(\"Cat\") : " + animalMapping("Cat"))
    println("animalMapping.get(\"Cat\") : " + animalMapping.get("Cat"))
    println("animalMapping.get(\"SomethingThatDoesntExist\") : " + animalMapping.get("SomethingThatDoesntExist"))

    animalMapping += "Parrot" -> 4
    println("animalMapping += \"Parrot\" -> 4: " + animalMapping)


    println()
    println("FUNCTIONAL")
    println()


    val some1 = (0 until 10).map(_ * 2)
    println("val some1 = (0 to 10).map(_ * 2)")
    println("some1 = " + some1)
    println("some1.reduce(_ + _) = " + some1.reduce(_ + _))
    println("some1.reduce(_ * _) = " + some1.reduce(_ * _))


    val some2 = (0 until 10).flatMap(0 to _)
    println("val some2 = (0 to 10).flatMap(0 to _)")
    println("some2 = " + some2)
    println("some2.toSet = " + some2.toSet)
    println("some2.foldLeft(TreeSet[Int]())((result, next) => result + next)) = " +
      some2
        .foldLeft(TreeSet[Int]())((result, next) => result + next)
    )
    println("some2.filter(_ < 5) = " + some2.filter(_ < 5))


    val some3 = (0 until 10).filter(_ % 2 == 0)
    println("val some3 = (0 to 10).filter(_ % 2 == 0)")
    println("some3.drop(1) = " + some3.drop(1))
    println("some3.dropRight(1) = " + some3.dropRight(1))
    println("some3.dropWhile(_ > 3) = " + some3.dropWhile(_ > 3))
    println("some3.take(1) = " + some3.take(1))
    println("some3.takeRight(1) = " + some3.takeRight(1))
    println("some3.takeWhile(_ > 3) = " + some3.takeWhile(_ > 3))

    val some4 = (0 until 10).toList
    println("(0 until 10) = " + (0 until 10))
    println("val some4 = (0 until 10).toList")
    println("some4 = " + some4)
    println("some4.sliding(2, 2).toList = " + some4.sliding(2, 2).toList)
    println("some4.sliding(2, 2).toList.flatten = " + some4.sliding(2, 2).toList.flatten)


    val some5 = Option(1)
    println("val some5 = Option(1)")
    println("some5 = " + some5)
    println("some5.map(_ * 2) = " + some5.map(_ * 2))
    println("some5.filterNot(_ == 1) = " + some5.filterNot(_ == 1))
    println("some5some5.toSeq = " + some5.toSeq)
    println("some5.getOrElse(0) = " + some5.getOrElse(0))


    val some6 = Seq(Option(0), Option(1), Option.empty, Option(2), Option.empty, Option(3))
    println("val some6 = Seq(Option(1), Option.empty, Option(2), Option.empty, Option(3))")
    println("some6 = " + some6)
    println("some6.flatten = " + some6.flatten)
    println("some6.map(_.map(_ * 2)) = " + some6.map(_.map(_ * 2)))
    println("some6.flatMap(_.map(_ * 3)) = " + some6.flatMap(_.map(_ * 3)))
    println("some6.flatMap(_.map(number => Try(100 / number))) = " + some6.flatMap(_.map(number => Try(100 / number))))
    println("some6.flatMap(_.flatMap(number => Try(100 / number).toOption)) = " + some6.flatMap(_.flatMap(number => Try(100 / number).toOption)))


    val some7 = Seq(Try(0 / 1), Try(1 / 0), Try(2))
    println("val some7 = Seq(Try(0 / 1), Try(1 / 0), Try(2))")
    println("some7 = " + some7)
    println("some7.map(_.map(_ + 10)) = " + some7.map(_.map(_ + 10)))
    println("some7.flatMap(_.map(_ + 10).toOption = " + some7.flatMap(_.map(_ + 10).toOption))






    val some5b = Seq(Left(1), Right(1))
    println("val some5b = Seq(Left(1), Right(1))")
    println("some5b.map(_.map(_ + 5)) = " + some5b.map(_.map(_ + 5)))
    println("some5b.map(_.swap.map(_ + 5)) = " + some5b.map(_.swap.map(_ + 5)))

    type NameWithSurname = (String, String)
    def parse(someString: String): Either[String, NameWithSurname] = {
      (someString
        .split(" ")
        .toList
        .filterNot(_.isEmpty)
      match {
        case (name: String) :: (surname: String) :: Nil => Right((name, surname))
        case (name: String) :: Nil => Left("There is only name!")
        case Nil => Left("There is nothing!")
        case other => Left("Something went wrong! " + other)
      })
        .map {
          case (name, surname) => (name.toUpperCase, surname.toUpperCase)
        }
    }

    println("parse(\"Jan Kowalski\")= " + parse("Jan Kowalski"))
    println("parse(\"Jan Kowalski \")= " + parse("Jan Kowalski "))
    println("parse(\"Jan Kowalski XXX\")= " + parse("Jan Kowalski XXX"))
    println("parse(\"Jan\")= " + parse("Jan"))
    println("parse(\"\")= " + parse(""))



















    val sortedAnimals: Seq[String] = Seq("CAt", "MousE", "DoG", "rAt")
      .map(_.toUpperCase)
      .flatMap(str => if (str == "CAT") Seq(str, "LION", "TIGER") else Seq(str))
      .filterNot(_.startsWith("R"))
      .filter(_ => true)
      .zipWithIndex
      .sortBy(_._1)
      .foldLeft((Seq[Int](), Seq[(String, Int)]())) {
        case (result, next) =>
          (result._1 :+ next._2, result._2 :+ next)
      } match {
      case (indices, namesWithIndices) =>
        println("Indices after sort: " + indices.mkString(" "))
        namesWithIndices.unzip._1
    }

    println("sortedAnimals: " + sortedAnimals)

  }
}
