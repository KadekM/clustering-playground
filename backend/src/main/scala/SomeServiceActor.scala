import akka.actor.{ActorLogging, Actor}
import akka.cluster.client.ClusterClientReceptionist

class SomeServiceActor extends Actor with ActorLogging {

  override def preStart(): Unit = {
    super.preStart()
    ClusterClientReceptionist(context.system).registerService(self)
  }

  override def postStop(): Unit = {
    super.postStop()
    ClusterClientReceptionist(context.system).unregisterService(self)
  }

  def receive = {
    case msg => log.info(s"[backend] someService got $msg")
  }
}
