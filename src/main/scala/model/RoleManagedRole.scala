package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class RoleManagedRole(
  roleId: Int,
  managedRoleId: Int
)

object RoleManagedRole extends SkinnyNoIdCRUDMapper[RoleManagedRole] {
  override lazy val tableName = "roles_managed_roles"
  override lazy val defaultAlias = createAlias("rmr")

  override def extract(rs: WrappedResultSet, rn: ResultName[RoleManagedRole]): RoleManagedRole = {
    autoConstruct(rs, rn)
  }
}
