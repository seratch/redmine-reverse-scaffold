package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Journal(
  id: Long,
  journalizedId: Int,
  journalizedType: String,
  userId: Int,
  notes: Option[String] = None,
  createdOn: DateTime,
  privateNotes: Boolean
)

object Journal extends SkinnyCRUDMapper[Journal] {
  override lazy val tableName = "journals"
  override lazy val defaultAlias = createAlias("j")

  override def extract(rs: WrappedResultSet, rn: ResultName[Journal]): Journal = {
    autoConstruct(rs, rn)
  }
}
