package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Message(
  id: Long,
  boardId: Int,
  parentId: Option[Int] = None,
  subject: String,
  content: Option[String] = None,
  authorId: Option[Int] = None,
  repliesCount: Int,
  lastReplyId: Option[Int] = None,
  createdOn: DateTime,
  updatedOn: DateTime,
  locked: Option[Boolean] = None,
  sticky: Option[Int] = None
)

object Message extends SkinnyCRUDMapper[Message] {
  override lazy val tableName = "messages"
  override lazy val defaultAlias = createAlias("m")

  override def extract(rs: WrappedResultSet, rn: ResultName[Message]): Message = {
    autoConstruct(rs, rn)
  }
}
