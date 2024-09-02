package typemembers

object TypeMembers extends App {

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class AnimalCollection {
    type AnimalType
    type BoundedAnimal <: Animal
    type SuperBoundedAnimal >: Dog <: Animal
    type AnimalC = Cat
  }

  val ac = new AnimalCollection
  //val dog: ac.AnimalType = new Cat// error

  val pup: ac.SuperBoundedAnimal  = new Dog
  val cat: ac.AnimalC = new Cat
  type CatAlias = Cat
  val anotherCat: CatAlias = new Cat

  /* alternative to generics */
  trait MyList {
    type T
    def add(element: T): MyList
  }

  /* overriding a type member */
  class NonEmptyList(value: Int) extends MyList {
    override type T = Int
    override def add(element: Int): MyList = ???
  }

  // .type
  type CatsType = cat.type
  val newCate: CatsType = cat

}
