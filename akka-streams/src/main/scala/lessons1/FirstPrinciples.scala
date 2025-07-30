package lessons1

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

object FirstPrinciples extends App {
  implicit val system = ActorSystem("FirstPrinciples")
  implicit val materializer = ActorMaterializer()

  // source
  val source = Source(1 to 10)
  // sink
  val sink = Sink.foreach[Int](println)

  val graph = source.to(sink)
  //graph.run()

  // flows transform elements
  val flow = Flow[Int].map(x => x + 1)
  val sourceWithFlow = source.via(flow)
  val flowWithSink = flow.to(sink)

  //sourceWithFlow.to(sink).run()
  //source.to(flowWithSink).run()
  //source.via(flow).to(sink).run()

  // nulls are not allowed
  //val illegalSource = Source.single[String](null)
  //illegalSource.to(Sink.foreach(println)).run()

  // various kinds of sources
  val finiteSource = Source.single(1)
  val anotherFiniteSource = Source(List(1, 2, 3))
  val emptySource = Source.empty[Int]

  val infiniteSource = Source(Stream.from(1)) // do not confuse Akka stream with collection Stream

  import scala.concurrent.ExecutionContext.Implicits.global

  val futureSource = Source.future(scala.concurrent.Future(42))

  // sink
  val theMostBoringSink = Sink.ignore
  val foreachSink = Sink.foreach[String](println)
  val headSink = Sink.head[Int] // retrieves head and then closes the stream
  val foldSink = Sink.fold[Int, Int](0)((a, b) => a + b)

  // flows - usually mapped to collection operators
  val mapFlow = Flow[Int].map(x => 2 * x)
  val takeFlow = Flow[Int].take(5)
  // drop, filter
  // NOT have flatMap

  // source -> flow -> flow -> ... -> sink

  val doubleFlowGraph = source.via(mapFlow).via(takeFlow).to(sink)
  //doubleFlowGraph.run()

  val mapSource = Source(1 to 10).map(x => x * 2) // eq Source(1 to 10).via(Flow[Int].map(x => x * 2))

  // run streams directly
  //mapSource.runForeach(println) // mapSource.to(Sink.foreach[Int](println)).run()

  // OPERATORS = components

  /**
   * Exercise: create a stream that takes the names of persons, then you will keep the first 2 names
   * with length > 5 characters
   */

  val namesSource = Source(List("Alice", "Bob", "Charlie", "David", "Martin", "AkkaStreams"))
  val longNameFlow = Flow[String].filter(name => name.length > 5)
  val limitFlow = Flow[String].take(2)
  val nameSink = Sink.foreach[String](println)

  println("Exercise:")
  //namesSource.via(longNameFlow).via(limitFlow).to(nameSink).run()
  namesSource.filter(_.length > 5).take(2).runForeach(println)

  println("main thread finished")
  system.terminate()
}
