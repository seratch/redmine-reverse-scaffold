package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class OpenIdAuthenticationAssociation(
  id: Long,
  issued: Option[Int] = None,
  lifetime: Option[Int] = None,
  handle: Option[String] = None,
  assocType: Option[String] = None,
  serverUrl: Option[Array[Byte]] = None,
  secret: Option[Array[Byte]] = None
)

object OpenIdAuthenticationAssociation extends SkinnyCRUDMapper[OpenIdAuthenticationAssociation] {
  override lazy val tableName = "open_id_authentication_associations"
  override lazy val defaultAlias = createAlias("oiaa")

  override def extract(rs: WrappedResultSet, rn: ResultName[OpenIdAuthenticationAssociation]): OpenIdAuthenticationAssociation = {
    autoConstruct(rs, rn)
  }
}
