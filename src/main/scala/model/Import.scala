package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Import(
  id: Long,
  theType: Option[String] = None,
  userId: Int,
  filename: Option[String] = None,
  settings: Option[String] = None,
  totalItems: Option[Int] = None,
  finished: Boolean,
  createdAt: DateTime,
  updatedAt: DateTime
)

object Import extends SkinnyCRUDMapper[Import] {
  override lazy val tableName = "imports"
  override lazy val defaultAlias = createAlias("i")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[Import]): Import = {
    autoConstruct(rs, rn)
  }
}
