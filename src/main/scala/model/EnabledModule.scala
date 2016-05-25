package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class EnabledModule(
  id: Long,
  projectId: Option[Int] = None,
  name: String
)

object EnabledModule extends SkinnyCRUDMapper[EnabledModule] {
  override lazy val tableName = "enabled_modules"
  override lazy val defaultAlias = createAlias("em")

  override def extract(rs: WrappedResultSet, rn: ResultName[EnabledModule]): EnabledModule = {
    autoConstruct(rs, rn)
  }
}
