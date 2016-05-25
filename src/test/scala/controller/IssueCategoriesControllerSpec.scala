package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class IssueCategoriesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    IssueCategory.deleteAll()
  }

  def createMockController = new IssueCategoriesController with MockController
  def newIssueCategory = FactoryGirl(IssueCategory).create()

  describe("IssueCategoriesController") {

    describe("shows issue categories") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueCategories/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueCategories/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a issue category") {
      it("shows HTML response") {
        val issueCategory = newIssueCategory
        val controller = createMockController
        controller.showResource(issueCategory.id)
        controller.status should equal(200)
        controller.getFromRequestScope[IssueCategory]("item") should equal(Some(issueCategory))
        controller.renderCall.map(_.path) should equal(Some("/issueCategories/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueCategories/new"))
      }
    }

    describe("creates a issue category") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "name" -> "dummy",
          "assigned_to_id" -> Int.MaxValue.toString())
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
      val issueCategory = newIssueCategory
      val controller = createMockController
      controller.editResource(issueCategory.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueCategories/edit"))
    }

    it("updates a issue category") {
      val issueCategory = newIssueCategory
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "assigned_to_id" -> Int.MaxValue.toString())
      controller.updateResource(issueCategory.id)
      controller.status should equal(200)
    }

    it("destroys a issue category") {
      val issueCategory = newIssueCategory
      val controller = createMockController
      controller.destroyResource(issueCategory.id)
      controller.status should equal(200)
    }

  }

}
