package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class SchemaMigration(
  version: String
)

object SchemaMigration extends SkinnyNoIdCRUDMapper[SchemaMigration] {
  override lazy val tableName = "schema_migrations"
  override lazy val defaultAlias = createAlias("sm")

  override def extract(rs: WrappedResultSet, rn: ResultName[SchemaMigration]): SchemaMigration = {
    autoConstruct(rs, rn)
  }
}
