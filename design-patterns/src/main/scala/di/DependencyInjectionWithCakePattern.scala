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

  trait ScalaDependentComponent { self: ScalaComponent =>
    def dependentAction(x: Int): String = action(x) + " this rocks!"
  }

  trait ScalaApplication { self: ScalaDependentComponent => }

  // layer1 - small components
  trait Picture extends ScalaComponent { def showPicture(): Unit = ??? }
  trait Stats extends ScalaComponent { def showStats(): Unit = ??? }

  // layer2 - compose
  trait Profile extends ScalaDependentComponent with Picture
  trait Analytics extends ScalaDependentComponent with Stats

  // layer3 - app
  trait AnalyticsApp extends ScalaApplication with Analytics

}
