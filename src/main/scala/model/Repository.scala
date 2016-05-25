package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Repository(
  id: Long,
  projectId: Int,
  url: String,
  login: Option[String] = None,
  password: Option[String] = None,
  rootUrl: Option[String] = None,
  theType: Option[String] = None,
  pathEncoding: Option[String] = None,
  logEncoding: Option[String] = None,
  extraInfo: Option[String] = None,
  identifier: Option[String] = None,
  isDefault: Option[Boolean] = None,
  createdOn: Option[DateTime] = None
)

object Repository extends SkinnyCRUDMapper[Repository] {
  override lazy val tableName = "repositories"
  override lazy val defaultAlias = createAlias("r")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[Repository]): Repository = {
    autoConstruct(rs, rn)
  }
}
