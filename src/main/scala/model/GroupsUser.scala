package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class GroupsUser(
  groupId: Int,
  userId: Int
)

object GroupsUser extends SkinnyNoIdCRUDMapper[GroupsUser] {
  override lazy val tableName = "groups_users"
  override lazy val defaultAlias = createAlias("gu")

  override def extract(rs: WrappedResultSet, rn: ResultName[GroupsUser]): GroupsUser = {
    autoConstruct(rs, rn)
  }
}
