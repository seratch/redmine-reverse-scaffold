package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Changeet(
  id: Long,
  repositoryId: Int,
  revision: String,
  committer: Option[String] = None,
  committedOn: DateTime,
  comments: Option[String] = None,
  commitDate: Option[LocalDate] = None,
  scmid: Option[String] = None,
  userId: Option[Int] = None
)

object Changeet extends SkinnyCRUDMapper[Changeet] {
  override lazy val tableName = "changesets"
  override lazy val defaultAlias = createAlias("c")

  override def extract(rs: WrappedResultSet, rn: ResultName[Changeet]): Changeet = {
    autoConstruct(rs, rn)
  }
}
