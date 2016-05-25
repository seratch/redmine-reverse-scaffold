package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class MemberRole(
  id: Long,
  memberId: Int,
  roleId: Int,
  inheritedFrom: Option[Int] = None
)

object MemberRole extends SkinnyCRUDMapper[MemberRole] {
  override lazy val tableName = "member_roles"
  override lazy val defaultAlias = createAlias("mr")

  override def extract(rs: WrappedResultSet, rn: ResultName[MemberRole]): MemberRole = {
    autoConstruct(rs, rn)
  }
}
