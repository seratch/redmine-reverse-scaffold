package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class TimeEntry(
  id: Long,
  projectId: Int,
  userId: Int,
  issueId: Option[Int] = None,
  hours: Float,
  comments: Option[String] = None,
  activityId: Int,
  spentOn: LocalDate,
  tyear: Int,
  tmonth: Int,
  tweek: Int,
  createdOn: DateTime,
  updatedOn: DateTime
)

object TimeEntry extends SkinnyCRUDMapper[TimeEntry] {
  override lazy val tableName = "time_entries"
  override lazy val defaultAlias = createAlias("te")

  override def extract(rs: WrappedResultSet, rn: ResultName[TimeEntry]): TimeEntry = {
    autoConstruct(rs, rn)
  }
}
