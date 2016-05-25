package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class TimeEntriesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    TimeEntry.deleteAll()
  }

  def createMockController = new TimeEntriesController with MockController
  def newTimeEntry = FactoryGirl(TimeEntry).create()

  describe("TimeEntriesController") {

    describe("shows time entries") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/timeEntries/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/timeEntries/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a time entry") {
      it("shows HTML response") {
        val timeEntry = newTimeEntry
        val controller = createMockController
        controller.showResource(timeEntry.id)
        controller.status should equal(200)
        controller.getFromRequestScope[TimeEntry]("item") should equal(Some(timeEntry))
        controller.renderCall.map(_.path) should equal(Some("/timeEntries/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/timeEntries/new"))
      }
    }

    describe("creates a time entry") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "user_id" -> Int.MaxValue.toString(),
          "issue_id" -> Int.MaxValue.toString(),
          "hours" -> Float.MaxValue.toString(),
          "comments" -> "dummy",
          "activity_id" -> Int.MaxValue.toString(),
          "spent_on" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
          "tyear" -> Int.MaxValue.toString(),
          "tmonth" -> Int.MaxValue.toString(),
          "tweek" -> Int.MaxValue.toString(),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
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
      val timeEntry = newTimeEntry
      val controller = createMockController
      controller.editResource(timeEntry.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/timeEntries/edit"))
    }

    it("updates a time entry") {
      val timeEntry = newTimeEntry
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "user_id" -> Int.MaxValue.toString(),
        "issue_id" -> Int.MaxValue.toString(),
        "hours" -> Float.MaxValue.toString(),
        "comments" -> "dummy",
        "activity_id" -> Int.MaxValue.toString(),
        "spent_on" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "tyear" -> Int.MaxValue.toString(),
        "tmonth" -> Int.MaxValue.toString(),
        "tweek" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(timeEntry.id)
      controller.status should equal(200)
    }

    it("destroys a time entry") {
      val timeEntry = newTimeEntry
      val controller = createMockController
      controller.destroyResource(timeEntry.id)
      controller.status should equal(200)
    }

  }

}
