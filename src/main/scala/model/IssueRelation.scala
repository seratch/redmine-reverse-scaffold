package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class IssueRelation(
  id: Long,
  issueFromId: Int,
  issueToId: Int,
  relationType: String,
  delay: Option[Int] = None
)

object IssueRelation extends SkinnyCRUDMapper[IssueRelation] {
  override lazy val tableName = "issue_relations"
  override lazy val defaultAlias = createAlias("ir")

  override def extract(rs: WrappedResultSet, rn: ResultName[IssueRelation]): IssueRelation = {
    autoConstruct(rs, rn)
  }
}
