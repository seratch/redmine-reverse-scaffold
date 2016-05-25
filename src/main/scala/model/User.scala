package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class User(
  id: Long,
  login: String,
  hashedPassword: String,
  firstname: String,
  lastname: String,
  admin: Boolean,
  status: Int,
  lastLoginOn: Option[DateTime] = None,
  language: Option[String] = None,
  authSourceId: Option[Int] = None,
  createdOn: Option[DateTime] = None,
  updatedOn: Option[DateTime] = None,
  theType: Option[String] = None,
  identityUrl: Option[String] = None,
  mailNotification: String,
  salt: Option[String] = None,
  mustChangePasswd: Boolean,
  passwdChangedOn: Option[DateTime] = None
)

object User extends SkinnyCRUDMapper[User] {
  override lazy val tableName = "users"
  override lazy val defaultAlias = createAlias("u")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[User]): User = {
    autoConstruct(rs, rn)
  }
}
