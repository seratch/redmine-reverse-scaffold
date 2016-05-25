package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Issue(
  id: Long,
  trackerId: Int,
  projectId: Int,
  subject: String,
  description: Option[String] = None,
  dueDate: Option[LocalDate] = None,
  categoryId: Option[Int] = None,
  statusId: Int,
  assignedToId: Option[Int] = None,
  priorityId: Int,
  fixedVersionId: Option[Int] = None,
  authorId: Int,
  lockVersion: Int,
  createdOn: Option[DateTime] = None,
  updatedOn: Option[DateTime] = None,
  startDate: Option[LocalDate] = None,
  doneRatio: Int,
  estimatedHours: Option[Float] = None,
  parentId: Option[Int] = None,
  rootId: Option[Int] = None,
  lft: Option[Int] = None,
  rgt: Option[Int] = None,
  isPrivate: Boolean,
  closedOn: Option[DateTime] = None
)

object Issue extends SkinnyCRUDMapper[Issue] {
  override lazy val tableName = "issues"
  override lazy val defaultAlias = createAlias("i")

  override def extract(rs: WrappedResultSet, rn: ResultName[Issue]): Issue = {
    autoConstruct(rs, rn)
  }
}
