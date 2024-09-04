package di

object DependencyInjectionWithCakePattern extends App {

  // classic DI
  trait Component

  class ComponentA extends Component
  class ComponentB extends Component
  class DependentComponent(val component: Component)

  // Cake pattern

  trait ScalaComponent {
    def action(x: Int): String
  }

  trait ScalaDependentComponent {
    self: ScalaComponent =>
    def dependentAction(x: Int): String
  }

  trait ScalaApplication {
    self: ScalaDependentComponent =>
    def appAction(x: Int): Unit
  }

  trait ConcreteComponent extends ScalaComponent {
    override def action(x: Int): String = " ConcreteComponent "
  }

  trait ConcreteDependentComponent extends ScalaDependentComponent with ConcreteComponent{
    override def dependentAction(x: Int): String = action(x) + " ConcreteDependentComponent "
  }

  trait ConcreteApp extends ScalaApplication with ConcreteDependentComponent{
    def appAction(x: Int): Unit = println(dependentAction(x) + " ConcreteApp ")
  }

  object ConcreteApp extends ConcreteApp

  ConcreteApp.appAction(10)
}
