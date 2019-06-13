package org.s3.training.lesson1

object PatternMatching {

  def main(args: Array[String]): Unit = {

    trait Thing
    trait LivingThing extends Thing
    trait Named{
      def name: String
    }

    case class Animal(name: String) extends LivingThing with Named
    case class Person(name: String, surname: String) extends LivingThing with Named
    case class Chair(color: String) extends Thing


    println("1.")
    Animal("cat") match {
      case _ => println("Anything not extracted")
    }

    println("2.")
    Animal("cat") match {
      case something => println(s"Anything extracted to $something")
    }

    println("3.")
    Animal("cat") match {
      case Animal(name) => println(s"Animal with extracted name: $name")
    }

    println("4.")
    Animal("cat") match {
      case Animal(_) => println(s"Animal with not extracted name")
    }

    println("5.")
    Animal("cat") match {
      case animal: Animal => println(s"Animal matched by type $animal")
    }

    println("6.")
    Animal("cat") match {
      case animal@ Animal(name) => println(s"Animal $animal with name $name")
    }


    println("7.")
    Seq(
      Animal("cat"),
      Animal("dog"),
    ).foreach{
      case cat @ Animal("cat") => println(s"cat matched: $cat")
      case dog @ Animal("dog") => println(s"dog matched: $dog")
      case anyOtherAnimal @ Animal("SOMETHING") => println(s"other animal matched $anyOtherAnimal")
    }



    println("8.")
    Seq(
      Animal("cat"),
      Animal("dog"),
    ).foreach{
      case cat @ Animal("cat") => println(s"cat matched: $cat")
      case dog @ Animal("dog") => println(s"dog matched: $dog")
      case anyOtherAnimal @ Animal(_) => println(s"other animal matched $anyOtherAnimal")
      case unknown => println(s"I don't know what it is $unknown")
    }





    println("9.")
    Seq(
      Animal("Puszek"),
      Animal("Cat"),
      Person("Jan", "Kowalski"),
      Person("Adolf", "Kowalski"),
      Chair("red"),
      new Chair("blue") with Named {
        override def name: String = "Stefan"
      }
    ).foreach {
      case Person("Jan", _) => println("Jan matched")
      case Person(name, surname) => println(s"person name: $name, surname: $surname matched")
      case Animal("Cat") => println(s"cat matched")
      case animal: Animal => println(s"animal $animal matched")
      case namedThing: Thing with Named if namedThing.name.contains("a") => println(s"thing with name containing a, name: ${namedThing.name}")
      case namedThing: Thing with Named => println(s"thing with name ${namedThing.name}")
      case namedSomething: Named => println(s"something with name ${namedSomething.name}")
      case other => println(s"Some other $other")
    }




    println("10.")

    Seq(
      (
        "Jan", "Kowalski",
        ("Wroclaw", "Powstancow Slaskich", 56, 10),
        (
          ("Maria", "Szanta"),
          ("Józef", "Kowalski")
        )
      ),
      (
        "Adolf", "Martin",
        ("Wroclaw", "Powstancow Slaskich", 39, 10),
        (
          ("Józefa", "Szanta"),
          ("Marian", "Martin")
        )
      ),
    ).foreach{
      case (name, surname, ("Wroclaw", _, _, _), ((_, _), ("Marian", _))) => println(s"Matched person from Wroclaw which father's name is Marian: $name $surname")
      case _ => println("Not matched")
    }





    println("11.")

    val maybeFilePath= Option("some/file/path")
    val maybeDataToWrite= Option("someDataToWrite")

    (maybeFilePath, maybeDataToWrite) match {
      case (Some(filePath), Some(data)) => println(s"I can write data:$data to file:$filePath!")
      case (a, b) => println(s"I can write if both are some but got ($a, $b)")
    }








    println("12.")
    object ProperNameForADog {
      def unapply(obj: Named): Option[String] = if(obj.name == "Puszek" || obj.name == "Adolf") Some(obj.name) else None
    }


    Seq(
      Animal("Puszek"),
      Animal("Cat"),
      Person("Jan", "Kowalski"),
      Person("Adolf", "Kowalski"),
      Chair("red")
    ).foreach {
      case ProperNameForADog(name) => println(s"Thing with proper name for a dog: $name")
      case other => println(s"Some other $other")
    }
  }
}
