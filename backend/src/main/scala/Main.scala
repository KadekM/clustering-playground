import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    val (s1, s2, s3) = (startBackend(2441), startBackend(2442), startBackend(2443))

    println("~~")
    StdIn.readLine()

    Await.ready(s1.terminate(), 10.seconds)
    Await.ready(s2.terminate(), 10.seconds)
    Await.ready(s3.terminate(), 10.seconds)
    println("done")
  }

  private def startBackend(port: Int): ActorSystem = {
    val conf =
      ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
        .withFallback(ConfigFactory.load)

    val system = ActorSystem("BackendSystem", conf)
    
    system.actorOf(Props[SomeServiceActor], "someService")
    system
  }
}
