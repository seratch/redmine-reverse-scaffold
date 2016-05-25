package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Tracker(
  id: Long,
  name: String,
  isInChlog: Boolean,
  position: Option[Int] = None,
  isInRoadmap: Boolean,
  fieldsBits: Option[Int] = None,
  defaultStatusId: Option[Int] = None
)

object Tracker extends SkinnyCRUDMapper[Tracker] {
  override lazy val tableName = "trackers"
  override lazy val defaultAlias = createAlias("t")

  override def extract(rs: WrappedResultSet, rn: ResultName[Tracker]): Tracker = {
    autoConstruct(rs, rn)
  }
}
