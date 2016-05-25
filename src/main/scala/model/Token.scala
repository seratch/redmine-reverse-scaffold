package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Token(
  id: Long,
  userId: Int,
  action: String,
  value: String,
  createdOn: DateTime,
  updatedOn: Option[DateTime] = None
)

object Token extends SkinnyCRUDMapper[Token] {
  override lazy val tableName = "tokens"
  override lazy val defaultAlias = createAlias("t")

  override def extract(rs: WrappedResultSet, rn: ResultName[Token]): Token = {
    autoConstruct(rs, rn)
  }
}
