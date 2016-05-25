package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Query(
  id: Long,
  projectId: Option[Int] = None,
  name: String,
  filters: Option[String] = None,
  userId: Int,
  columnNames: Option[String] = None,
  sortCriteria: Option[String] = None,
  groupBy: Option[String] = None,
  theType: Option[String] = None,
  visibility: Option[Int] = None,
  options: Option[String] = None
)

object Query extends SkinnyCRUDMapper[Query] {
  override lazy val tableName = "queries"
  override lazy val defaultAlias = createAlias("q")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[Query]): Query = {
    autoConstruct(rs, rn)
  }
}
