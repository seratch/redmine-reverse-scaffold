package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class IssueCategory(
  id: Long,
  projectId: Int,
  name: String,
  assignedToId: Option[Int] = None
)

object IssueCategory extends SkinnyCRUDMapper[IssueCategory] {
  override lazy val tableName = "issue_categories"
  override lazy val defaultAlias = createAlias("ic")

  override def extract(rs: WrappedResultSet, rn: ResultName[IssueCategory]): IssueCategory = {
    autoConstruct(rs, rn)
  }
}
