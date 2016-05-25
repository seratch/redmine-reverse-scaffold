package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class UserPreference(
  id: Long,
  userId: Int,
  others: Option[String] = None,
  hideMail: Option[Boolean] = None,
  timeZone: Option[String] = None
)

object UserPreference extends SkinnyCRUDMapper[UserPreference] {
  override lazy val tableName = "user_preferences"
  override lazy val defaultAlias = createAlias("up")

  override def extract(rs: WrappedResultSet, rn: ResultName[UserPreference]): UserPreference = {
    autoConstruct(rs, rn)
  }
}
