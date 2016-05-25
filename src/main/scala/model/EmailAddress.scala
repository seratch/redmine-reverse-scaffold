package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class EmailAddress(
  id: Long,
  userId: Int,
  address: String,
  isDefault: Boolean,
  notifyRequired: Boolean,
  createdOn: DateTime,
  updatedOn: DateTime
)

object EmailAddress extends SkinnyCRUDMapper[EmailAddress] {
  override lazy val tableName = "email_addresses"
  override lazy val defaultAlias = createAlias("ea")
  override lazy val nameConverters = Map("^notifyRequired$" -> "notify")

  override def extract(rs: WrappedResultSet, rn: ResultName[EmailAddress]): EmailAddress = {
    autoConstruct(rs, rn)
  }
}
