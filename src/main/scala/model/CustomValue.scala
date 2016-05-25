package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class CustomValue(
  id: Long,
  customizedType: String,
  customizedId: Int,
  customFieldId: Int,
  value: Option[String] = None
)

object CustomValue extends SkinnyCRUDMapper[CustomValue] {
  override lazy val tableName = "custom_values"
  override lazy val defaultAlias = createAlias("cv")

  override def extract(rs: WrappedResultSet, rn: ResultName[CustomValue]): CustomValue = {
    autoConstruct(rs, rn)
  }
}
