package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class CustomFieldsProject(
  customFieldId: Int,
  projectId: Int
)

object CustomFieldsProject extends SkinnyNoIdCRUDMapper[CustomFieldsProject] {
  override lazy val tableName = "custom_fields_projects"
  override lazy val defaultAlias = createAlias("cfp")

  override def extract(rs: WrappedResultSet, rn: ResultName[CustomFieldsProject]): CustomFieldsProject = {
    autoConstruct(rs, rn)
  }
}
