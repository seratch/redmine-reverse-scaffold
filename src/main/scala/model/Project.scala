package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Project(
  id: Long,
  name: String,
  description: Option[String] = None,
  homepage: Option[String] = None,
  isPublic: Boolean,
  parentId: Option[Int] = None,
  createdOn: Option[DateTime] = None,
  updatedOn: Option[DateTime] = None,
  identifier: Option[String] = None,
  status: Int,
  lft: Option[Int] = None,
  rgt: Option[Int] = None,
  inheritMembers: Boolean,
  defaultVersionId: Option[Int] = None
)

object Project extends SkinnyCRUDMapper[Project] {
  override lazy val tableName = "projects"
  override lazy val defaultAlias = createAlias("p")

  override def extract(rs: WrappedResultSet, rn: ResultName[Project]): Project = {
    autoConstruct(rs, rn)
  }
}
