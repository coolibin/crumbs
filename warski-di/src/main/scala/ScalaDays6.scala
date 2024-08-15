import com.softwaremill.macwire.aop.{Interceptor, NoOpInterceptor, ProxyingInterceptor}

object ScalaDays6 extends App {

  import com.softwaremill.macwire._

  case class Field()
  case class Digger()
  trait PotatoFarm
  case class TraditionalPotatoFarm(field: Field, digger: Digger)
    extends PotatoFarm {
    println("New potato farm! Rejoice!")
  }

  case class TreeDPrintingPotatoFarm()
    extends PotatoFarm

  case class CowPasture(potatoFarm: PotatoFarm)
  case class Meatery(cowPasture: CowPasture)

  case class Restaurant(potatoFarm: PotatoFarm, meatery: Meatery) {
    def orderSteakWithPotatoes(): Unit = {
      println(s"Welcome to $this. Here's your order!")
    }
  }

  trait CropModule {
    def potatoFarm: PotatoFarm
  }

  trait TraditionalCropModule extends CropModule {
    lazy val field = wire[Field]
    lazy val digger = wire[Digger]
    lazy val potatoFarm: PotatoFarm = wire[TraditionalPotatoFarm]
  }

  trait ModernCropModule extends CropModule {
    lazy val potatoFarm = wire[TreeDPrintingPotatoFarm]
  }

  trait LivestockModule {
    lazy val cowPasture = wire[CowPasture]
    lazy val meatery = wire[Meatery]

    def potatoFarm: PotatoFarm
  }

  trait RestaurantModule extends CropModule with LivestockModule {
    lazy val restaurant = transactional(wire[Restaurant])

    def transactional: Interceptor
  }

  val app = new ModernCropModule with LivestockModule with RestaurantModule {

    lazy val transactional = ProxyingInterceptor {
      ctx =>
        println("About to invoke restaurant")
        val result = ctx.proceed()
        println("Restaurant invoked!")
        result
    }
  }
  //app.restaurant.orderSteakWithPotatoes()

  val appTesting = new TraditionalCropModule with LivestockModule with RestaurantModule {
    lazy val transactional = NoOpInterceptor

    override lazy val potatoFarm: PotatoFarm = new PotatoFarm {}
  }

  appTesting.restaurant.orderSteakWithPotatoes()
}
