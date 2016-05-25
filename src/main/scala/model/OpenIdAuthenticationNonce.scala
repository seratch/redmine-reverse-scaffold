package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class OpenIdAuthenticationNonce(
  id: Long,
  timestamp: Int,
  serverUrl: Option[String] = None,
  salt: String
)

object OpenIdAuthenticationNonce extends SkinnyCRUDMapper[OpenIdAuthenticationNonce] {
  override lazy val tableName = "open_id_authentication_nonces"
  override lazy val defaultAlias = createAlias("oian")

  override def extract(rs: WrappedResultSet, rn: ResultName[OpenIdAuthenticationNonce]): OpenIdAuthenticationNonce = {
    autoConstruct(rs, rn)
  }
}
