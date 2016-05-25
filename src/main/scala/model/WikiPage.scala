package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class WikiPage(
  id: Long,
  wikiId: Int,
  title: String,
  createdOn: DateTime,
  isProtected: Boolean,
  parentId: Option[Int] = None
)

object WikiPage extends SkinnyCRUDMapper[WikiPage] {
  override lazy val tableName = "wiki_pages"
  override lazy val defaultAlias = createAlias("wp")
  override lazy val nameConverters = Map("^isProtected$" -> "protected")

  override def extract(rs: WrappedResultSet, rn: ResultName[WikiPage]): WikiPage = {
    autoConstruct(rs, rn)
  }
}
