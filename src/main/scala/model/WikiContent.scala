package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class WikiContent(
  id: Long,
  pageId: Int,
  authorId: Option[Int] = None,
  text: Option[String] = None,
  comments: Option[String] = None,
  updatedOn: DateTime,
  version: Int
)

object WikiContent extends SkinnyCRUDMapper[WikiContent] {
  override lazy val tableName = "wiki_contents"
  override lazy val defaultAlias = createAlias("wc")

  override def extract(rs: WrappedResultSet, rn: ResultName[WikiContent]): WikiContent = {
    autoConstruct(rs, rn)
  }
}
