package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class AuthSource(
  id: Long,
  theType: String,
  name: String,
  host: Option[String] = None,
  port: Option[Int] = None,
  account: Option[String] = None,
  accountPassword: Option[String] = None,
  baseDn: Option[String] = None,
  attrLogin: Option[String] = None,
  attrFirstname: Option[String] = None,
  attrLastname: Option[String] = None,
  attrMail: Option[String] = None,
  ontheflyRegister: Boolean,
  tls: Boolean,
  filter: Option[String] = None,
  timeout: Option[Int] = None
)

object AuthSource extends SkinnyCRUDMapper[AuthSource] {
  override lazy val tableName = "auth_sources"
  override lazy val defaultAlias = createAlias("as_")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[AuthSource]): AuthSource = {
    autoConstruct(rs, rn)
  }
}
