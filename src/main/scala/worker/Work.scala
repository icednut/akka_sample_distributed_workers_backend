package worker

/**
 * @author will.109
 * @date 2020/02/19
 **/
case class Work(workId: String, job: Any) extends CborSerializable

case class WorkResult(workId: String, result: Any) extends CborSerializable
