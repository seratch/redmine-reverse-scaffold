package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class CustomField(
  id: Long,
  theType: String,
  name: String,
  fieldFormat: String,
  possibleValues: Option[String] = None,
  regexp: Option[String] = None,
  minLength: Option[Int] = None,
  maxLength: Option[Int] = None,
  isRequired: Boolean,
  isForAll: Boolean,
  isFilter: Boolean,
  position: Option[Int] = None,
  searchable: Option[Boolean] = None,
  defaultValue: Option[String] = None,
  editable: Option[Boolean] = None,
  visible: Boolean,
  multiple: Option[Boolean] = None,
  formatStore: Option[String] = None,
  description: Option[String] = None
)

object CustomField extends SkinnyCRUDMapper[CustomField] {
  override lazy val tableName = "custom_fields"
  override lazy val defaultAlias = createAlias("cf")
  override lazy val nameConverters = Map("^theType$" -> "type")

  override def extract(rs: WrappedResultSet, rn: ResultName[CustomField]): CustomField = {
    autoConstruct(rs, rn)
  }
}
