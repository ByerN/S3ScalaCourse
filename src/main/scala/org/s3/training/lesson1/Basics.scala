package org.s3.training.lesson1

import scala.concurrent.Future
import scala.util.{Failure, Success}


/*
  There are no statics in Scala
  static void main -> def main in object (to explain later)
 */
object Basics {

  class MyClass {
  }

  /*
    Methods are defined as follows:

    def methodName(parameter1: Parameter1Type, parameter2: Parameter2Type) : ReturnType = {
      methodbody
      returnsLastValue
    }

    def -> definition
   */
  def main(args: Array[String]): Unit = {









    def variablesAndValues = {}
    var variable1 = 0
    var variable2: Int = 0
    val value1 = 0

    variable1 = 2
    //value1 = 3 //value1 = 2; //compilation error reassignment to value
    //val value2: String = 0 //Non compatible types









    def types = {}
    val a1: Byte = 0
    val a2: Short = 0
    val a3: Int = 0
    val a4: Char = 0
    val a5: Long = 0
    val a6: Float = 0
    val a7: Double = 0
    val a8: String = null
    val a9: Array[Int] = null















    def ifConstruct = {}

    val someCondition = false

    var someVariable = 1
    if (someCondition) {
      someVariable = 2
    } else if (someVariable > 3) {
      someVariable = 3
    } else {
      someVariable = 4
    }

    val someExpressionResult = if(someCondition) 2 else if (someVariable > 3) 3 else 4
















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

    for(i <- 0 until 10 by 2 if i < 3) {
      println(s"For loop with guard $i")
    }

    for(
      x <- 0 until 4;
      y <- 0 until 3
    ){
      println(s"Double for loop x=$x, y=$y")
    }

    for(
      maybeValue <- Option(1)
    ){
      println(s"Option loop $maybeValue")
    }


    for(
      maybeValue1 <- Option(1);
      maybeValue2 <- Option(2)
    ){
      println(s"2 Options loop1 $maybeValue1 $maybeValue2")
    }



    for(
      maybeValue1 <- Some(1); //same as Option(1)
      maybeValue2 <- None //same as Option.empty or Option(null)
    ){
      println(s"2 Options loop1 $maybeValue1 $maybeValue2")
    }










    import scala.concurrent.ExecutionContext.Implicits.global
    for(
      future1 <- Future("Hi from 1");
      future2 <- Future("Hi from 2")
    ) {
      println(s"2 Future loop1 $future1 and $future2")
    }










    val multiFuture: Future[String] = for (
      future1 <- Future("Hi from 1");
      future2 <- if(future1.nonEmpty) Future("Hi from 2") else Future.failed(new IllegalStateException("Something went wrong"))
    ) yield s"$future1 and $future2"

    println(multiFuture)

    multiFuture.onComplete{
      //Pattern matching
      case Success(value) => println(value)
      case Failure(exception) => println(exception)
    }



    val ints = for(number <- 0 until 10 by 3) yield number

    println(s"YIELD: $ints")
    println(s"SUM: ${(for(number <- 0 until 10 by 3) yield number).sum}")









  }

}
