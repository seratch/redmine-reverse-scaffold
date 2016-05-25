package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Member(
  id: Long,
  userId: Int,
  projectId: Int,
  createdOn: Option[DateTime] = None,
  mailNotification: Boolean
)

object Member extends SkinnyCRUDMapper[Member] {
  override lazy val tableName = "members"
  override lazy val defaultAlias = createAlias("m")

  override def extract(rs: WrappedResultSet, rn: ResultName[Member]): Member = {
    autoConstruct(rs, rn)
  }
}
