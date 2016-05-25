package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Version(
  id: Long,
  projectId: Int,
  name: String,
  description: Option[String] = None,
  effectiveDate: Option[LocalDate] = None,
  createdOn: Option[DateTime] = None,
  updatedOn: Option[DateTime] = None,
  wikiPageTitle: Option[String] = None,
  status: Option[String] = None,
  sharing: String
)

object Version extends SkinnyCRUDMapper[Version] {
  override lazy val tableName = "versions"
  override lazy val defaultAlias = createAlias("v")

  override def extract(rs: WrappedResultSet, rn: ResultName[Version]): Version = {
    autoConstruct(rs, rn)
  }
}
