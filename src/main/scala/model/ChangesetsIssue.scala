package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class ChangesetsIssue(
  changesetId: Int,
  issueId: Int
)

object ChangesetsIssue extends SkinnyNoIdCRUDMapper[ChangesetsIssue] {
  override lazy val tableName = "changesets_issues"
  override lazy val defaultAlias = createAlias("ci")

  override def extract(rs: WrappedResultSet, rn: ResultName[ChangesetsIssue]): ChangesetsIssue = {
    autoConstruct(rs, rn)
  }
}
