package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class CustomFieldEnumeration(
  id: Long,
  customFieldId: Int,
  name: String,
  active: Boolean,
  position: Int
)

object CustomFieldEnumeration extends SkinnyCRUDMapper[CustomFieldEnumeration] {
  override lazy val tableName = "custom_field_enumerations"
  override lazy val defaultAlias = createAlias("cfe")

  override def extract(rs: WrappedResultSet, rn: ResultName[CustomFieldEnumeration]): CustomFieldEnumeration = {
    autoConstruct(rs, rn)
  }
}
