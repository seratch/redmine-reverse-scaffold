package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class News(
  id: Long,
  projectId: Option[Int] = None,
  title: String,
  summary: Option[String] = None,
  description: Option[String] = None,
  authorId: Int,
  createdOn: Option[DateTime] = None,
  commentsCount: Int
)

object News extends SkinnyCRUDMapper[News] {
  override lazy val tableName = "news"
  override lazy val defaultAlias = createAlias("n")

  override def extract(rs: WrappedResultSet, rn: ResultName[News]): News = {
    autoConstruct(rs, rn)
  }
}
