package worker.backend

import akka.actor.typed.{ActorRef, ActorSystem}
import akka.cluster.typed.{ClusterSingleton, ClusterSingletonSettings, SingletonActor}
import worker.backend.WorkManager.Command

import scala.concurrent.duration._

/**
 * @author will.109
 * @date 2020/02/19
 **/
object WorkManagerSingleton {

  private val singletonName = "work-manager"
  private val singletonRole = "back-end"

  def init(system: ActorSystem[_]): ActorRef[Command] = {
    val workTimeout = system.settings.config.
      getDuration("distributed-workers.work-timeout").getSeconds.seconds

    ClusterSingleton(system).init(
      SingletonActor(WorkManager(workTimeout), singletonName)
        .withSettings(ClusterSingletonSettings(system).withRole(singletonRole)))
  }
}
