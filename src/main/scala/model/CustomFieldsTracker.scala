package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class CustomFieldsTracker(
  customFieldId: Int,
  trackerId: Int
)

object CustomFieldsTracker extends SkinnyNoIdCRUDMapper[CustomFieldsTracker] {
  override lazy val tableName = "custom_fields_trackers"
  override lazy val defaultAlias = createAlias("cft")

  override def extract(rs: WrappedResultSet, rn: ResultName[CustomFieldsTracker]): CustomFieldsTracker = {
    autoConstruct(rs, rn)
  }
}
