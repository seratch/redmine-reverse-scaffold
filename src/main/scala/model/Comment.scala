package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Comment(
  id: Long,
  commentedType: String,
  commentedId: Int,
  authorId: Int,
  comments: Option[String] = None,
  createdOn: DateTime,
  updatedOn: DateTime
)

object Comment extends SkinnyCRUDMapper[Comment] {
  override lazy val tableName = "comments"
  override lazy val defaultAlias = createAlias("c")

  override def extract(rs: WrappedResultSet, rn: ResultName[Comment]): Comment = {
    autoConstruct(rs, rn)
  }
}
