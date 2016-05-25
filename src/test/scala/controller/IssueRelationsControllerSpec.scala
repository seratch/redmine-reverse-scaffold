package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class IssueRelationsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    IssueRelation.deleteAll()
  }

  def createMockController = new IssueRelationsController with MockController
  def newIssueRelation = FactoryGirl(IssueRelation).create()

  describe("IssueRelationsController") {

    describe("shows issue relations") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueRelations/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueRelations/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a issue relation") {
      it("shows HTML response") {
        val issueRelation = newIssueRelation
        val controller = createMockController
        controller.showResource(issueRelation.id)
        controller.status should equal(200)
        controller.getFromRequestScope[IssueRelation]("item") should equal(Some(issueRelation))
        controller.renderCall.map(_.path) should equal(Some("/issueRelations/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueRelations/new"))
      }
    }

    describe("creates a issue relation") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "issue_from_id" -> Int.MaxValue.toString(),
          "issue_to_id" -> Int.MaxValue.toString(),
          "relation_type" -> "dummy",
          "delay" -> Int.MaxValue.toString())
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
      val issueRelation = newIssueRelation
      val controller = createMockController
      controller.editResource(issueRelation.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/issueRelations/edit"))
    }

    it("updates a issue relation") {
      val issueRelation = newIssueRelation
      val controller = createMockController
      controller.prepareParams(
        "issue_from_id" -> Int.MaxValue.toString(),
        "issue_to_id" -> Int.MaxValue.toString(),
        "relation_type" -> "dummy",
        "delay" -> Int.MaxValue.toString())
      controller.updateResource(issueRelation.id)
      controller.status should equal(200)
    }

    it("destroys a issue relation") {
      val issueRelation = newIssueRelation
      val controller = createMockController
      controller.destroyResource(issueRelation.id)
      controller.status should equal(200)
    }

  }

}
