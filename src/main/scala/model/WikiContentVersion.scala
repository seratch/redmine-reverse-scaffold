package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class WikiContentVersion(
  id: Long,
  wikiContentId: Int,
  pageId: Int,
  authorId: Option[Int] = None,
  data: Option[Array[Byte]] = None,
  compression: Option[String] = None,
  comments: Option[String] = None,
  updatedOn: DateTime,
  version: Int
)

object WikiContentVersion extends SkinnyCRUDMapper[WikiContentVersion] {
  override lazy val tableName = "wiki_content_versions"
  override lazy val defaultAlias = createAlias("wcv")

  override def extract(rs: WrappedResultSet, rn: ResultName[WikiContentVersion]): WikiContentVersion = {
    autoConstruct(rs, rn)
  }
}
