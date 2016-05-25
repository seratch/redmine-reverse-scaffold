package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class IssueStatus(
  id: Long,
  name: String,
  isClosed: Boolean,
  position: Option[Int] = None,
  defaultDoneRatio: Option[Int] = None
)

object IssueStatus extends SkinnyCRUDMapper[IssueStatus] {
  override lazy val tableName = "issue_statuses"
  override lazy val defaultAlias = createAlias("is_")

  override def extract(rs: WrappedResultSet, rn: ResultName[IssueStatus]): IssueStatus = {
    autoConstruct(rs, rn)
  }
}
