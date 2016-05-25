package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class ImportItem(
  id: Long,
  importId: Int,
  position: Int,
  objId: Option[Int] = None,
  message: Option[String] = None
)

object ImportItem extends SkinnyCRUDMapper[ImportItem] {
  override lazy val tableName = "import_items"
  override lazy val defaultAlias = createAlias("ii")

  override def extract(rs: WrappedResultSet, rn: ResultName[ImportItem]): ImportItem = {
    autoConstruct(rs, rn)
  }
}
