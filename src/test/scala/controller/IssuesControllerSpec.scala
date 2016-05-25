package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class IssuesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Issue.deleteAll()
  }

  def createMockController = new IssuesController with MockController
  def newIssue = FactoryGirl(Issue).create()

  describe("IssuesController") {

    describe("shows issues") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issues/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issues/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a issue") {
      it("shows HTML response") {
        val issue = newIssue
        val controller = createMockController
        controller.showResource(issue.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Issue]("item") should equal(Some(issue))
        controller.renderCall.map(_.path) should equal(Some("/issues/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issues/new"))
      }
    }

    describe("creates a issue") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "tracker_id" -> Int.MaxValue.toString(),
          "project_id" -> Int.MaxValue.toString(),
          "subject" -> "dummy",
          "description" -> "dummy",
          "due_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
          "category_id" -> Int.MaxValue.toString(),
          "status_id" -> Int.MaxValue.toString(),
          "assigned_to_id" -> Int.MaxValue.toString(),
          "priority_id" -> Int.MaxValue.toString(),
          "fixed_version_id" -> Int.MaxValue.toString(),
          "author_id" -> Int.MaxValue.toString(),
          "lock_version" -> Int.MaxValue.toString(),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "start_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
          "done_ratio" -> Int.MaxValue.toString(),
          "estimated_hours" -> Float.MaxValue.toString(),
          "parent_id" -> Int.MaxValue.toString(),
          "root_id" -> Int.MaxValue.toString(),
          "lft" -> Int.MaxValue.toString(),
          "rgt" -> Int.MaxValue.toString(),
          "is_private" -> "true",
          "closed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
        controller.createResource()
        controller.status should equal(200)
      }

      it("fails with invalid parameters") {
        val controller = createMockController
        controller.prepareParams() // no parameters
        controller.createResource()
        controller.status should equal(400)
        controller.errorMessages.size should be >(0)
      }
    }

    it("shows a resource edit input form") {
      val issue = newIssue
      val controller = createMockController
      controller.editResource(issue.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issues/edit"))
    }

    it("updates a issue") {
      val issue = newIssue
      val controller = createMockController
      controller.prepareParams(
        "tracker_id" -> Int.MaxValue.toString(),
        "project_id" -> Int.MaxValue.toString(),
        "subject" -> "dummy",
        "description" -> "dummy",
        "due_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "category_id" -> Int.MaxValue.toString(),
        "status_id" -> Int.MaxValue.toString(),
        "assigned_to_id" -> Int.MaxValue.toString(),
        "priority_id" -> Int.MaxValue.toString(),
        "fixed_version_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "lock_version" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "start_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "done_ratio" -> Int.MaxValue.toString(),
        "estimated_hours" -> Float.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "root_id" -> Int.MaxValue.toString(),
        "lft" -> Int.MaxValue.toString(),
        "rgt" -> Int.MaxValue.toString(),
        "is_private" -> "true",
        "closed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(issue.id)
      controller.status should equal(200)
    }

    it("destroys a issue") {
      val issue = newIssue
      val controller = createMockController
      controller.destroyResource(issue.id)
      controller.status should equal(200)
    }

  }

}
