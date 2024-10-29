package lessons1

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}

import scala.util.{Failure, Success}

object MaterializingStreams extends App {

  implicit val system = ActorSystem("MaterializingStreams")
  implicit val materializer = ActorMaterializer()

  import system.dispatcher

  val simpleGraph = Source(1 to 10).to(Sink.foreach(println))
  //val simpleMaterializedValue = simpleGraph.run()

  val source = Source(1 to 10)
  val sink = Sink.reduce[Int]((a, b) => a + b)

  val someFuture = source.runWith(sink)

  someFuture.onComplete {
    case Success(value) => println(s"The sum of all elements is: $value")
    case Failure(ex) => println(s"The sum of the elements could not be computed: $ex")
  }

  // choosing materialized value
  val simpleSource = Source(1 to 10)
  val simpleFlow = Flow[Int].map(x => x + 1)
  val simpleSink = Sink.foreach[Int](println)
  //simpleSource.viaMat(simpleFlow)((sourceMat, flowMat) => flowMat)
  val graph = simpleSource.viaMat(simpleFlow)(Keep.right).toMat(simpleSink)(Keep.right)

  graph.run().onComplete {
    case Success(_) => println("Stream processing finished")
    case Failure(ex) => println(s"Stream processing failed with: $ex")
  }

  // sugars
  //val sum = Source(1 to 10).runWith(Sink.reduce[Int](_ + _)) // source.to(Sink.reduce)(Keep.right)
  val sum = Source(1 to 10).runReduce(_ + _)

  // backwards
  Sink.foreach[Int](println).runWith(Source.single(42))

  // both ways
  Flow[Int].map(x => x * 2).runWith(simpleSource, simpleSink)

  /**
   * return the last element out of a source
   * compute the total word count out of stream o sentences
   *   - map, fold, reduce
   */

  val flowLastElement = Flow[Int].fold[Int](0)((_, y) => y)

  val lastElem = Source(1 to 10).toMat(Sink.last)(Keep.right).run()
    .map(x => println(s"last element: $x"))
  val lastElem2 = Source(1 to 10).runWith(Sink.last)
    .map(x => println(s"last element: $x"))

  val sentenceSource = Source(List(
    "I love Akka Streams",
    "This is amazing",
    "Learning from Rock the JVM"
  ))

  val wordCountSink = Sink.fold[Int, String](0)((currentWords, newSentence) => currentWords + newSentence.split(" ").length)
  sentenceSource.toMat(wordCountSink)(Keep.right).run().onComplete {
    case Success(value) => println(s"Total word count: $value")
    case Failure(ex) => println(s"Word count failed: $ex")
  }

  sentenceSource.runWith(wordCountSink).onComplete {
    case Success(value) => println(s"Total word count: $value")
    case Failure(ex) => println(s"Word count failed: $ex")
  }

  sentenceSource.map(_.split(" ").length).runWith(Sink.reduce[Int](_ + _)).onComplete {
    case Success(value) => println(s"Total word count alternative: $value")
    case Failure(ex) => println(s"Word count failed: $ex")
  }

  sentenceSource.runFold(0)((currentWords, newSentence) => currentWords + newSentence.split(" ").length)
    .onComplete {
      case Success(value) => println(s"Total word count alternative 2: $value")
      case Failure(ex) => println(s"Word count failed: $ex")
    }

  val wordCountFlow = Flow[String].fold[Int](0)((currentWords, newSentence) => currentWords + newSentence.split(" ").length)

  wordCountFlow.runWith(sentenceSource, Sink.head)._2.onComplete {
    case Success(value) => println(s"Total word count alternative 3: $value")
    case Failure(ex) => println(s"Word count failed: $ex")
  }

}
