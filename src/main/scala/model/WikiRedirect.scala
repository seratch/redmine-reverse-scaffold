package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class WikiRedirect(
  id: Long,
  wikiId: Int,
  title: Option[String] = None,
  redirectsTo: Option[String] = None,
  createdOn: DateTime,
  redirectsToWikiId: Int
)

object WikiRedirect extends SkinnyCRUDMapper[WikiRedirect] {
  override lazy val tableName = "wiki_redirects"
  override lazy val defaultAlias = createAlias("wr")

  override def extract(rs: WrappedResultSet, rn: ResultName[WikiRedirect]): WikiRedirect = {
    autoConstruct(rs, rn)
  }
}
