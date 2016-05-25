package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Board(
  id: Long,
  projectId: Int,
  name: String,
  description: Option[String] = None,
  position: Option[Int] = None,
  topicsCount: Int,
  messagesCount: Int,
  lastMessageId: Option[Int] = None,
  parentId: Option[Int] = None
)

object Board extends SkinnyCRUDMapper[Board] {
  override lazy val tableName = "boards"
  override lazy val defaultAlias = createAlias("b")

  override def extract(rs: WrappedResultSet, rn: ResultName[Board]): Board = {
    autoConstruct(rs, rn)
  }
}
