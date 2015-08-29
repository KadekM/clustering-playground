import akka.actor.{ActorLogging, Actor}
import akka.cluster.client.{ClusterClientSettings, ClusterClient}
import scala.concurrent.duration._

class ClientToBackendActor extends Actor with ActorLogging {
  import context._

  val client = system.actorOf(
    ClusterClient.props(ClusterClientSettings(system)), "client")

  override def preStart(): Unit = {
    super.preStart()
    context.system.scheduler.schedule(1.seconds, 1.second, client, talkToReceptionist)
  }

  def receive = {
    case msg => log.info(s"[ClientToBackendActor] got $msg")
  }

  private def talkToReceptionist = {
    ClusterClient.Send("/user/someService", "Hi!!!", localAffinity = true)
  }
}
