package org.s3.training.lesson1

import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.{Calendar => Cal}

import scala.annotation.tailrec

/*
  There are no statics in Scala
  object = singleton instance of itself
 */
object Basics {

  //nested class in object
  class MyClass {
  }

  /*
    Methods are defined as follows:

    def methodName(parameter1: Parameter1Type, parameterN: ParameterNType) : ReturnType = {
      methodBody
      returnsLastValue
    }

    def -> definition
   */
  def main(args: Array[String]): Unit = {









    def variablesAndValues = {}
    var variable1 = 0//int variable1 = 0;
    var variable2: Int = 0
    val value1 = 0 //final int value1 = 0;

    variable1 = 2
    //value1 = 3 //compilation error reassignment to value
    //val value2: String = 0 //Non compatible types








    //https://docs.scala-lang.org/tour/unified-types.html
    def types = {}
    val a1: Byte = 0
    val a2: Short = 0
    val a3: Int = 0
    val a4: Char = 0
    val a5: Long = 0
    val a6: Float = 0
    val a7: Double = 0
    val a8: String = null //Don't use nulls!
    val a9: Array[Int] = null //int[] a9 = null
















    def methodsAndFunctions() = {}
    println("methodsAndFunctions")

    println("METHOD:")
    def myMethod(param1: Int, param2: Double, param3: String = "defaultValue") = {
      println(param1 + " " + param2 + " " + param3)
    }
    myMethod(1, 2)
    myMethod(1, 2, "nonDefaultValue")
    myMethod(param2 = 2, param1 = 1)
    myMethod(param3 =  "nonDefaultValue", param2 = 2, param1 = 1)










    def methodWithMultipleParamLists(param1: Int)(param2: Int)(param3: String) = {
      param1 + " " + param2 + " " + param3
    }
    println("methodWithMultipleParamLists= " + methodWithMultipleParamLists(1)(2){"SOME"})












    println("FUNCTION:")
    val sumFunction = (x: Int, y: Int) => x + y
    println("sumFunction= " + sumFunction)
    println("sumFunction(1, 2) = " + sumFunction(1, 2))
    println("sumFunction.apply(1, 2) = " + sumFunction.apply(1, 2))







    val add3: Int => Int = sumFunction(3, _)
    println("add3 = " + add3)
    println("add3(2) = " + add3(2))















    def ifConstruct = {}
    println("ifConstruct")
    val someCondition = false

    //Imperative way
    var someVariable = 1
    if (someCondition) {
      someVariable = 2
    } else if (someVariable > 3) {
      someVariable = 3
    } else {
      someVariable = 4
    }

    //Declarative way
    val someExpressionResult = if(someCondition) 2 else if (someVariable > 3) 3 else 4










    println("Expressions and laziness:")
    val blockResult = {
      println("I am invoking code block")
      2
    }
    println("After code block result value declaration")
    println("blockResult= " + blockResult)

    lazy val lazyBlockResult = {
      println("I am invoking lazy code block")
      2
    }
    println("After lazy code block result value declaration")
    println("lazyBlockResult= " + lazyBlockResult)













    def loopConstructs = {}

    while (false) {
      //it works as usual
    }

    do {
      //it works as usual
    } while (false)




    for(i <- 0 to 10) {
      println("For loop with to " + i)
    }

    for(i <- 0 until 10) {
      println("For loop with until " + i)
    }

    for(i <- 0 until 10 by 2) {
      println("For loop with by " + i)
    }

    val range: Seq[Int] = 0 until 10 by 2
    for(i <- range if i < 3) {
      println(s"For loop with guard $i")
    }

    for(
      x <- 0 until 4;
      y <- 0 until 3
    ){
      println(s"Double for loop x=$x, y=$y")
    }

    /*
      Something like:
        Java:
        IntStream.range(0, 4).forEach(
          x -> IntStream.range(0, 3).forEach(
            y -> System.out.println("Double for loop " + x + " " + y)
          )
        );

        Scala:
        (0 until 4).foreach(
          x => (0 until 3).foreach(
            y => println(s"Double for loop x=$x, y=$y")
          )
        )
     */




    for(
      maybeValue <- Option(1)
    ){
      println(s"Option loop $maybeValue")
    }
    /*
    Same as:
      Scala:
      Option(1).foreach(maybeValue => println(s"Option loop $maybeValue"))
    */


    for(
      maybeValue1 <- Option(1);
      maybeValue2 <- Option(2)
    ){
      println(s"2 Options loop1 $maybeValue1 $maybeValue2")
    }
    /*
       Same as:
       Scala:
          Option(1).foreach(
            maybeValue1 => Option(2).foreach(
              maybeValue2 => println(s"2 Options loop1 $maybeValue1 $maybeValue2")
            )
          )
     */




    for(
      maybeValue1 <- Some(1); //same as Option(1)
      maybeValue2 <- None //same as Option.empty or Option(null)
    ){
      //nope :)
      println(s"2 Options loop2 $maybeValue1 $maybeValue2")
    }










    for(
      future1 <- Future("Hi from 1");
      future2 <- Future("Hi from 2")
    ) {
      println(s"2 Future loop1 $future1 and $future2")
    }










    val multiFuture: Future[String] = for (
      future1Result <- Future("Hi from 1");
      future2Result <- Future("Hi from 2 and " + future1Result);
      future3Result <-
        if(future1Result == "Hi from 1")
          Future("Hi from 3")
        else
          Future.failed(new IllegalStateException("Something went wrong"))
    ) yield s"$future1Result and $future2Result and $future3Result"

    println("multiFuture=" + multiFuture)

    multiFuture.onComplete{
      //Pattern matching
      case Success(value) => println("multiFuture.onComplete= " + value)
      case Failure(exception) => println("multiFuture.onComplete= " + exception)
    }

    /*
    //Same as:
    //Scala:
    val future: Future[String] = Future("Hi from 1").flatMap(
      future1Result =>
        Future("Hi from 2 and " + future1Result).flatMap(
          future2Result =>
            if (future1Result == "Hi from 1")
              Future("Hi from 2").map(
                future3Result => s"$future1Result and $future2Result and $future3Result"
              )
            else
              Future.failed(new IllegalStateException("Something went wrong"))
        )
    )
    */




    def patternMatching() = {}

    case class Person(name: String, surname: String)
    println("PATTERN MATCHING")
    println(
      Seq(
        Person("Adolf", "Muddler"),
        Person("Mr", "Bean"),
        Person("John", "Blaskovitz"),
        Person("John", "Rambo")
      ).map(person =>
        person match {
          case Person("John", "Blaskovitz") => "Here we go!"
          case Person("John", surname) => "It's John but not this one I am looking for..."
          case Person("Adolf", _) => "It's Adolf... not John -.-"
          case Person(name, surname) => s"Well... It's $name $surname"
          case _ => "I don't know what it is"
        }
      )
    )













    println("CLASSES:")
    class CasualClass
    class ClassWithConstructors(x: Int, y: String, z: Int){
      def this() = this(0, "0", 0)

      override def toString: String = "ClassWithConstructors " + x + " " + y + " " + z
    }
    println(new ClassWithConstructors())
    println(new ClassWithConstructors(1, "1", 2))

    abstract class AbstractClass

    object SingletonObject{
      def staticMethod = 1
      val staticConstant = 1
      var staticVariable = 1
    }


    trait InterfaceOnSteroids {
      var nonStaticVariable = 1
      def hi: String
      def setValue(arg: Int) = {
        nonStaticVariable = arg
      }
    }

    class SomeClass(arg: Int) extends InterfaceOnSteroids{
      override def hi: String = "Some hi!"
      setValue(arg)
    }
    println("new SomeClass(1).nonStaticVariable= " + new SomeClass(1).nonStaticVariable)
    println("new SomeClass(2).nonStaticVariable= " + new SomeClass(2).nonStaticVariable)


    object EnumObjectA {
      sealed trait EnumTrait
      object ENUM_VAL_1A extends EnumTrait
      object ENUM_VAL_2A extends EnumTrait
      object ENUM_VAL_3A extends EnumTrait
      object ENUM_VAL_4A extends EnumTrait
    }

    object EnumObjectB extends Enumeration {
      type MyEnum = Value
      val ENUM_VAL_1B, ENUM_VAL_2B, ENUM_VAL_3B, ENUM_VAL_4B = Value
    }

    EnumObjectB.ENUM_VAL_1B
    EnumObjectB.ENUM_VAL_2B
    EnumObjectB.ENUM_VAL_3B
    EnumObjectB.ENUM_VAL_4B
    EnumObjectB.values



    //mixin based inheritance
    abstract class SomeAbstractClass
    trait SomeTrait1 extends SomeAbstractClass
    trait SomeTrait2 extends SomeAbstractClass
    class SomeSpecificClass extends SomeTrait1 with SomeTrait2




    println("Case classes:")
    case class Game(name: String, genre: String)

    //equals and hashcode based on constructor args
    println(Game("FarCry", "FPS") == Game("FarCry", "FPS") )
    println(Game("FarCry", "FPS").==(Game("WorldOfWarcraft", "MMORPG")))


    //unapply -> used for decomposition
    val Game(someName, genre) = Game("LeagueOfLegends", "MOBA")
    println(s"Name and genre: ${someName + " " + genre}")

    //apply
    Game("LeagueOfLegends", "MOBA") //instead of new Game("LeagueOfLegends", "MOBA")

    //copy
    println(Game("LeagueOfLegends", "MOBA").copy(name = "DoTA"))

    //toString, getters/setters and many more things...






















    def tuples() = {}
    println("TUPLES")

    val someTuple = (0, 1.0, "Something")
    println(s"${someTuple._1}, ${someTuple._2}, ${someTuple._3}")
    println(s"$someTuple")




    println("TUPLE DECONSTRUCTION")
    val (someInt, someDouble, someString) = someTuple
    println(s"$someInt $someDouble $someString ")









    def operatorsOverloading={}
    println("operatorsOverloading")

    case class SomeA(value: Int){
      def +(someA: SomeA): SomeA = SomeA(this.value + someA.value)
    }

    println(SomeA(1) + SomeA(2))
    println("FPS" == "MMORPG")
    println("FPS" == "FPS")
    println("Integer comparision: " + (Integer.valueOf(10000) == Integer.valueOf(10000)))
    println(1 +: 2 +: List(3, 4, 5) :+ 6 :+ 7)








    def factorial(n: Int): Int = {
      @tailrec
      def factorial(x: Int, result: Int): Int =
        if (x <= 0) result
        else factorial(x - 1, result * x)
      factorial(n, 1)
    }

    println("TAILREC FACTORIAL:")
    println(factorial(1))
    println(factorial(2))
    println(factorial(3))
    println(factorial(4))
    println(factorial(5))
    println(factorial(6))
    println(factorial(7))
    println(factorial(8))
    println(factorial(9))
    println(factorial(10))
    println(factorial(100))
    println(factorial(1000))
    println(factorial(10000))
    println(factorial(100000))
    println(factorial(1000000))








    def complexStreamsInScala() = {}
    println("Complex streams in Scala:")


    type MyMap = Map[String, Int]
    val sortMapping: MyMap = Map(
      "Banana" -> 1,
      "Onion" -> 2,
      "Orange" -> 3,
      "Potato" -> 4,
      "Mango" -> 5,
    )

    abstract class Eatable(name: String){
      def eatableType: String
      def myName: String = name
    }
    case class Vegetable(name: String) extends Eatable(name) {
      override def eatableType: String = "VEGETABLE"
    }
    case class Fruit(name: String) extends Eatable(name) {
      override def eatableType: String = "FRUIT"
    }
    case class Bucket(groupName:String, eatables: Seq[Eatable], charactersSum: Int, indicesSum: Int)

    val resultOfComplexStream = List("Banana", "Onion", "Potato", "Orange", "Mango")
      .map(thing => (thing, sortMapping(thing)))
      .filter(!_._1.toLowerCase.contains("m"))
      .sortBy(_._2)
      .collect {
        case (name@"Banana", _) => Fruit(name)
        case (name@"Orange", _) => Fruit(name)
        case (name@"Onion", _) => Vegetable(name)
        case (name@"Potato", _) => Vegetable(name)
        case (name@"Mango", _) => Fruit(name)
      }
      .zipWithIndex
      .groupBy(_._1.eatableType + "S")
      .map {
        case (eatableType, eatablesWithIndices) =>
          (
            eatableType,
            (
              eatablesWithIndices.map(_._1),
              eatablesWithIndices.foldLeft((0, 0)) {
                case ((charactersSum, indicesSum), (eatable, eatableIndex)) =>
                  (charactersSum + eatable.myName.length, indicesSum + eatableIndex)
              }
            )
          )
      }
      .map {
        case (groupName, (eatables, (charactersSum, indicesSum))) =>
          Bucket(groupName, eatables, charactersSum, indicesSum)
      }
      .map(bucket =>
        bucket.copy(
          eatables = bucket.eatables.map {
            case Vegetable("Potato") => Vegetable("Potejto")
            case other => other
          }
        )
      )



    println("resultOfComplexStream= " + resultOfComplexStream)

  }
}
