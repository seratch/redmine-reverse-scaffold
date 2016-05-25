package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class JournalDetail(
  id: Long,
  journalId: Int,
  property: String,
  propKey: String,
  oldValue: Option[String] = None,
  value: Option[String] = None
)

object JournalDetail extends SkinnyCRUDMapper[JournalDetail] {
  override lazy val tableName = "journal_details"
  override lazy val defaultAlias = createAlias("jd")

  override def extract(rs: WrappedResultSet, rn: ResultName[JournalDetail]): JournalDetail = {
    autoConstruct(rs, rn)
  }
}
