package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Setting(
  id: Long,
  name: String,
  value: Option[String] = None,
  updatedOn: Option[DateTime] = None
)

object Setting extends SkinnyCRUDMapper[Setting] {
  override lazy val tableName = "settings"
  override lazy val defaultAlias = createAlias("s")

  override def extract(rs: WrappedResultSet, rn: ResultName[Setting]): Setting = {
    autoConstruct(rs, rn)
  }
}
