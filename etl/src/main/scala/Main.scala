import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.io.StdIn
import scala.concurrent.duration._

object Main {
  def main(args: Array[String]): Unit = {
    val (s1, s2) = (startEtl(54180), startEtl(54181))

    println("~~")
    StdIn.readLine()

    Await.ready(s1.terminate(), 10.seconds)
    Await.ready(s2.terminate(), 10.seconds)
    println("done")
  }

  def startEtl(port: Int): ActorSystem = {
    val conf =
      ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.load)

    val system = ActorSystem("EtlSystem", conf)
    system.actorOf(Props[ClientToBackendActor], "clientToBackend")
    system
  }
}
