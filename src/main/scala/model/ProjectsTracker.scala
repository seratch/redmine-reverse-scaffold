package model

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class ProjectsTracker(
  projectId: Int,
  trackerId: Int
)

object ProjectsTracker extends SkinnyNoIdCRUDMapper[ProjectsTracker] {
  override lazy val tableName = "projects_trackers"
  override lazy val defaultAlias = createAlias("pt")

  override def extract(rs: WrappedResultSet, rn: ResultName[ProjectsTracker]): ProjectsTracker = {
    autoConstruct(rs, rn)
  }
}
