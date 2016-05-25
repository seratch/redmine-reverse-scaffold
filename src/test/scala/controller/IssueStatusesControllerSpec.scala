package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class IssueStatusesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    IssueStatus.deleteAll()
  }

  def createMockController = new IssueStatusesController with MockController
  def newIssueStatus = FactoryGirl(IssueStatus).create()

  describe("IssueStatusesController") {

    describe("shows issue statuses") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueStatuses/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueStatuses/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a issue status") {
      it("shows HTML response") {
        val issueStatus = newIssueStatus
        val controller = createMockController
        controller.showResource(issueStatus.id)
        controller.status should equal(200)
        controller.getFromRequestScope[IssueStatus]("item") should equal(Some(issueStatus))
        controller.renderCall.map(_.path) should equal(Some("/issueStatuses/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueStatuses/new"))
      }
    }

    describe("creates a issue status") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "is_closed" -> "true",
          "position" -> Int.MaxValue.toString(),
          "default_done_ratio" -> Int.MaxValue.toString())
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
      val issueStatus = newIssueStatus
      val controller = createMockController
      controller.editResource(issueStatus.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueStatuses/edit"))
    }

    it("updates a issue status") {
      val issueStatus = newIssueStatus
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "is_closed" -> "true",
        "position" -> Int.MaxValue.toString(),
        "default_done_ratio" -> Int.MaxValue.toString())
      controller.updateResource(issueStatus.id)
      controller.status should equal(200)
    }

    it("destroys a issue status") {
      val issueStatus = newIssueStatus
      val controller = createMockController
      controller.destroyResource(issueStatus.id)
      controller.status should equal(200)
    }

  }

}
