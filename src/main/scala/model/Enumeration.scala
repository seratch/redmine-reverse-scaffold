package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Enumeration(
  id: Long,
  name: String,
  position: Option[Int] = None,
  isDefault: Boolean,
  theType: Option[String] = None,
  active: Boolean,
  projectId: Option[Int] = None,
  parentId: Option[Int] = None,
  positionName: Option[String] = None
)

object Enumeration extends SkinnyCRUDMapper[Enumeration] {
  override lazy val tableName = "enumerations"
  override lazy val defaultAlias = createAlias("e")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[Enumeration]): Enumeration = {
    autoConstruct(rs, rn)
  }
}
