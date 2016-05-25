package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class CustomFieldsRole(
  customFieldId: Int,
  roleId: Int
)

object CustomFieldsRole extends SkinnyNoIdCRUDMapper[CustomFieldsRole] {
  override lazy val tableName = "custom_fields_roles"
  override lazy val defaultAlias = createAlias("cfr")

  override def extract(rs: WrappedResultSet, rn: ResultName[CustomFieldsRole]): CustomFieldsRole = {
    autoConstruct(rs, rn)
  }
}
