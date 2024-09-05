package typemembers

class TypeConstraintsWithTypeClasses extends App {

  /**
   * Problem statement:
   * Make sure that the method in child classes returns only
   * the relevant types
   */
  trait Animal

  trait CanBreed[A] {
    def breed(a: A): List[A]
  }

  class Dog extends Animal
  object Dog {
    implicit object DogCanBreed extends CanBreed[Dog] {
      def breed(a: Dog): List[Dog] = List()
    }
  }

  implicit class CanBreedExt[A](animal: A) {
    def breed(implicit canBreed: CanBreed[A]): List[A] = canBreed.breed(animal)
  }

  val dog = new Dog
  dog.breed // returns a List[Dog]

  /**
   * new CanBreedExt[Dog].breed(Dog.DogsCanBreed)
   * where Dog.DogsCanBreed is an implicit to pass to .breed()
   */

}
