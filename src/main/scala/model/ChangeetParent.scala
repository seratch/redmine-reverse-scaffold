package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class ChangeetParent(
  changesetId: Int,
  parentId: Int
)

object ChangeetParent extends SkinnyNoIdCRUDMapper[ChangeetParent] {
  override lazy val tableName = "changeset_parents"
  override lazy val defaultAlias = createAlias("cp")

  override def extract(rs: WrappedResultSet, rn: ResultName[ChangeetParent]): ChangeetParent = {
    autoConstruct(rs, rn)
  }
}
