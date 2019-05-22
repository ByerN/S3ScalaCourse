package org.s3.training.lesson1

object ClassesLesson {

  def main(args: Array[String]): Unit = {






    object SingletonObject {
      val singletonValue: String = "HI"
      var singletonVariable: String = "Hello"

      def staticMethod: String = "Nice to meet you"
    }

    val singletonObject = SingletonObject
    println(s"singletonObject=$singletonObject")
    println(s"singletonObject.singletonValue=${singletonObject.singletonValue}")
    println(s"singletonObject.singletonVariable=${singletonObject.singletonVariable}")
    println(s"singletonObject.staticMethod=${singletonObject.staticMethod}")
    println("singletonObject==SingletonObject=" + (singletonObject == SingletonObject))
















    class SomeClass {
      val someValue = 1
      var someVariable = 2

      def someMethod = 3

      def someMethod(someMethodArgument: Int) = someMethodArgument + 3
    }

    val someInstance = new SomeClass
    println(s"someInstance=$someInstance")
    println(s"someInstance.someValue=${someInstance.someValue}")
    println(s"someInstance.someVariable=${someInstance.someVariable}")
    println(s"someInstance.someMethod=${someInstance.someMethod}")
    println(s"someInstance.someMethod(someMethodArgument: Int)=${someInstance.someMethod(1)}")
    println(s"(new SomeClass == new SomeClass)=" + (new SomeClass == new SomeClass))





















    abstract class SomeAbstractClass {
      val someField = 3
      val someAbstractField: String
      var someAbstractVariable: String

      def someAbstractMethod: String

      def someMethod: String = "I am some method"
    }

    val anonymousClassInstance = new SomeAbstractClass {
      override val someAbstractField: String = "some value"
      override var someAbstractVariable: String = "some value"

      override def someAbstractMethod: String = ??? // not implemented => throw new UnsupportedOperationException
    }

    class ClassImplementingAbstractClass extends SomeAbstractClass {
      override val someAbstractField: String = "some value"
      override var someAbstractVariable: String = "some value"

      override def someAbstractMethod: String = "Implemented!"
    }



















    trait SomeTrait {
      def someAbstractMethod: String

      def someMethod: String = "Hi!"

      //it's not static!
      val someValue: Int = 1
      val someAbstractValue: Int
      var someVariable: Int = 2
    }

    class SomeClassImplementingTrait extends SomeTrait {
      override def someAbstractMethod: String = "I am implemented!"

      override val someAbstractValue: Int = 0
    }

    class SomeAnotherClassImplementingTrait extends SomeTrait {
      override def someAbstractMethod: String = "I am implemented in another class!"

      override val someAbstractValue: Int = 2
    }

    println("new SomeClassImplementingTrait().someAbstractValue =" + new SomeClassImplementingTrait().someAbstractValue)
    println("new SomeAnotherClassImplementingTrait().someAbstractValue =" + new SomeAnotherClassImplementingTrait().someAbstractValue)

    trait SomeAnotherTrait {
      def anotherTraitAbstractMethod: String
    }

    class SomeAnotherClassImplementingTwoTraits extends SomeTrait with SomeAnotherTrait {
      override def someAbstractMethod: String = "I am implemented in another class!"

      override val someAbstractValue: Int = 2

      override def anotherTraitAbstractMethod: String = "Some another trait method implementation"
    }

























    trait A {
      def someA: String
    }

    trait B extends A {
      def someB: String
    }

    class X {
      def something = "Hi"
    }

    //traits can extend classes
    trait C extends X with B






    trait Death{
      def method: Unit = {
        println("Death")
      }
    }

    trait Diamond extends Death {
      override def method: Unit = {
        super.method
        println("Diamond")
      }
    }

    trait Of extends Death {
      override def method: Unit = {
        super.method
        println("Of")
      }
    }

    trait Deadly extends Diamond with Of with Death{
      override def method: Unit = {
        super.method
        println("Deadly")
      }
    }

    println("Diamond of Death:")
    val deadly = new Deadly{}
    deadly.method






    object ObjectCanImplementTrait extends A {
      override def someA: String = "Implemented in object"
    }

    def passingObjectAsArgument(a: A) = {
      println(s"passingObjectAsArgument for $a= ${a.someA}")
    }

    passingObjectAsArgument(ObjectCanImplementTrait)



















    val someAnonymousImplementationOnTheFly = new SomeClassImplementingTrait() with SomeAnotherTrait {
      override def anotherTraitAbstractMethod: String = "Anonymous implementation"
    }

    println("someAnonymousImplementationOnTheFly.anotherTraitAbstractMethod=" + someAnonymousImplementationOnTheFly.anotherTraitAbstractMethod)
















    class ClassWithConstructor(x: Int, y: Int)

    case class CaseClassWithConstructors(x: Int, y: Int){
      def this(x: Int) {
        this(x, 0)
      }

      def this(str: String) {
        this(str.hashCode, str.length)
      }
    }

    val caseClassInstance = CaseClassWithConstructors(0, 1) //no need for new operator => apply method
    println("caseClassInstance=" + caseClassInstance) // toString implemented
    println("CaseClassWithConstructors(0, 1) == CaseClassWithConstructors(0, 1)=" + (CaseClassWithConstructors(0, 1) == CaseClassWithConstructors(0, 1))) //equals/hashcode for primary constructor implemented

    val CaseClassWithConstructors(x, y) = caseClassInstance //unapply method
    println(s"Deconstructed x=$x y=$y")

    val caseClassInstanceCopy = caseClassInstance.copy(x = 11) //copy method
    CaseClassWithConstructors(y = 0, x = 1)

    case class CaseClassWithDefaultConstructorParams(x: Int = 0, y: Int = 0)
    val caseClassWithDefaultConstructorParams = CaseClassWithDefaultConstructorParams()























    //Self-types
    class Z

    trait ThisTraitCanBeImplementedOnlyWithZClassSubtypes {
      this: Z =>
    }

    //class Y extends ThisTraitCanBeImplementedOnlyWithZClass // illegal inheritance

    class SomeZ extends Z with ThisTraitCanBeImplementedOnlyWithZClassSubtypes







    trait ThisTraitCanBeImplementedOnlyAlongWithSpecifiedMethods {
      self: {
        def methodA(a: String): Int
        def methodB(a: Int): String
      } =>

      methodA("Yes, you can call methods here")
    }

    class SomeX extends ThisTraitCanBeImplementedOnlyAlongWithSpecifiedMethods {
      def methodA(a: String): Int = 0 //has to be implemented
      def methodB(a: Int): String = "0" //has to be implemented
    }




















    //Companion objects (object with the same name as class) has access to private members of class
    object Companion {
      Companion(x).x //works
    }
    case class Companion(private val x: Int) {

    }

    //Companion(x).x //Doesn't work => private


    object FactoryObject {
      def from(x: Int) = new FactoryObject(x)
    }
    class FactoryObject private(x: Int)

    //new FactoryObject(0) //prohibited -> private constructor
    FactoryObject.from(0) //factory method





















    class DefaultGettersAndSetters(
                                    noSetterAndGetter: Int,
                                    val withGetter: Int,
                                    var withGetterAndSetter: Int
                                  )

    case class DefaultCaseClassGettersAndSetters(
                                                  defaultsToValWithGetter: Int,
                                                  val withGetter: Int,
                                                  var withGetterAndSetter: Int
                                                )

    case class AccessModifiers() {
      var publicField: Int = 0
      private var privateField: Int = 0
      protected var protectedField: Int = 0
      private[lesson1] var packagePrivateField1: Int = 0
      private[training] var packagePrivateField2: Int = 0
      private[this] var objectPrivateField: Int = 0
    }







    //manual setters/getters
    class Person() {
      // Private age variable, renamed to _age
      private var _age = 0
      var name = ""

      // Getter
      def age = _age

      // Setter
      def age_= (value:Int):Unit = _age = value
    }

    val person = new Person()
    person.age = 10
    val age = person.age
  }
}
