package variance

object VariancePositions extends App {

  /**
   * Rule of thumb
   * - use covariance when creating a collection of things
   * - use contravariance when creating a group of actions
   */

  class Vehicle
  class Bike extends Vehicle
  class Car extends Vehicle

  class IParking[T](vehicles: List[T]) {
    def park(vehicle: T): IParking[T] = new IParking(vehicle :: vehicles)
    def impound(vehicles: List[T]): IParking[T] = new IParking(vehicles ++ this.vehicles)
    def checkVehicles(conditions: String): List[T] = Nil
  }

  class CParking[+T](vehicles: List[T]) {
    def park[S >: T](vehicle: S): CParking[S] = new CParking(vehicle :: this.vehicles)
    def impound[S >: T](vehicles: List[S]): IParking[S] = new IParking(vehicles ++ this.vehicles)
    def checkVehicles(conditions: String): List[T] = Nil
  }

  class XParking[-T](vehicles: List[T]) {
    def park(vehicle: T): XParking[T]  = new XParking(vehicle :: this.vehicles)
    def impound(vehicles: List[T]): XParking[T] = new XParking(vehicles ++ this.vehicles)
    def checkVehicles[S <: T](conditions: String): List[S] = Nil
  }

  val p = new CParking(List(new Car))
  val p2: CParking[Car] = p.park(new Car)
  val p3: CParking[Vehicle] = p2.park(new Bike)

}
