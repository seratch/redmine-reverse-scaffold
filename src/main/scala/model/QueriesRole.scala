package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class QueriesRole(
  queryId: Int,
  roleId: Int
)

object QueriesRole extends SkinnyNoIdCRUDMapper[QueriesRole] {
  override lazy val tableName = "queries_roles"
  override lazy val defaultAlias = createAlias("qr")

  override def extract(rs: WrappedResultSet, rn: ResultName[QueriesRole]): QueriesRole = {
    autoConstruct(rs, rn)
  }
}
