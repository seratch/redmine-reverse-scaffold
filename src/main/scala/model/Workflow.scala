package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Workflow(
  id: Long,
  trackerId: Int,
  oldStatusId: Int,
  newStatusId: Int,
  roleId: Int,
  assignee: Boolean,
  author: Boolean,
  theType: Option[String] = None,
  fieldName: Option[String] = None,
  rule: Option[String] = None
)

object Workflow extends SkinnyCRUDMapper[Workflow] {
  override lazy val tableName = "workflows"
  override lazy val defaultAlias = createAlias("w")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[Workflow]): Workflow = {
    autoConstruct(rs, rn)
  }
}
