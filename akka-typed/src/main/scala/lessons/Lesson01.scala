package lessons

import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object Lesson01 extends App {

  object Counter {
    sealed trait Protocol
    case class Incr(n: Long) extends Protocol
    case class Get(replyTo: ActorRef[Long]) extends Protocol

    def behavior: Behavior[Protocol] = Behaviors.setup { ctx =>
      var value = 0L
      Behaviors.receiveMessage {
        case Incr(n) =>
          value += n
          Behaviors.same
        case Get(replyTo) =>
          replyTo ! value
          Behaviors.same
      }
    }
  }

  implicit val system: ActorSystem[Counter.Protocol] = ActorSystem(Counter.behavior, "Counter")

  system ! Counter.Incr(1)

  implicit val timeout: Timeout = 3.seconds
  val resultF = system.ask[Long](Counter.Get)

  resultF.foreach { result =>
    println(s"Counter value: $result")
  }


  system.terminate()

}
