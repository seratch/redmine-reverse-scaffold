package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Document(
  id: Long,
  projectId: Int,
  categoryId: Int,
  title: String,
  description: Option[String] = None,
  createdOn: Option[DateTime] = None
)

object Document extends SkinnyCRUDMapper[Document] {
  override lazy val tableName = "documents"
  override lazy val defaultAlias = createAlias("d")

  override def extract(rs: WrappedResultSet, rn: ResultName[Document]): Document = {
    autoConstruct(rs, rn)
  }
}
