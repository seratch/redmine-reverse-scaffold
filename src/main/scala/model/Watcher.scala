package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Watcher(
  id: Long,
  watchableType: String,
  watchableId: Int,
  userId: Option[Int] = None
)

object Watcher extends SkinnyCRUDMapper[Watcher] {
  override lazy val tableName = "watchers"
  override lazy val defaultAlias = createAlias("w")

  override def extract(rs: WrappedResultSet, rn: ResultName[Watcher]): Watcher = {
    autoConstruct(rs, rn)
  }
}
