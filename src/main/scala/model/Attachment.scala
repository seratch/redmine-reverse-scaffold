package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Attachment(
  id: Long,
  containerId: Option[Int] = None,
  containerType: Option[String] = None,
  filename: String,
  diskFilename: String,
  filesize: Long,
  contentType: Option[String] = None,
  digest: String,
  downloads: Int,
  authorId: Int,
  createdOn: Option[DateTime] = None,
  description: Option[String] = None,
  diskDirectory: Option[String] = None
)

object Attachment extends SkinnyCRUDMapper[Attachment] {
  override lazy val tableName = "attachments"
  override lazy val defaultAlias = createAlias("a")

  override def extract(rs: WrappedResultSet, rn: ResultName[Attachment]): Attachment = {
    autoConstruct(rs, rn)
  }
}
