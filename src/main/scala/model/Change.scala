package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Change(
  id: Long,
  changesetId: Int,
  action: String,
  path: String,
  fromPath: Option[String] = None,
  fromRevision: Option[String] = None,
  revision: Option[String] = None,
  branch: Option[String] = None
)

object Change extends SkinnyCRUDMapper[Change] {
  override lazy val tableName = "changes"
  override lazy val defaultAlias = createAlias("c")

  override def extract(rs: WrappedResultSet, rn: ResultName[Change]): Change = {
    autoConstruct(rs, rn)
  }
}
