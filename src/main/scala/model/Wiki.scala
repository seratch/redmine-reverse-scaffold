package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Wiki(
  id: Long,
  projectId: Int,
  startPage: String,
  status: Int
)

object Wiki extends SkinnyCRUDMapper[Wiki] {
  override lazy val tableName = "wikis"
  override lazy val defaultAlias = createAlias("w")

  override def extract(rs: WrappedResultSet, rn: ResultName[Wiki]): Wiki = {
    autoConstruct(rs, rn)
  }
}
