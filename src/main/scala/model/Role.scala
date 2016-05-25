package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Role(
  id: Long,
  name: String,
  position: Option[Int] = None,
  assignable: Option[Boolean] = None,
  builtin: Int,
  permissions: Option[String] = None,
  issuesVisibility: String,
  usersVisibility: String,
  timeEntriesVisibility: String,
  allRolesManaged: Boolean
)

object Role extends SkinnyCRUDMapper[Role] {
  override lazy val tableName = "roles"
  override lazy val defaultAlias = createAlias("r")

  override def extract(rs: WrappedResultSet, rn: ResultName[Role]): Role = {
    autoConstruct(rs, rn)
  }
}
