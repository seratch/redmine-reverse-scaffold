package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class WorkflowsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Workflow.deleteAll()
  }

  def createMockController = new WorkflowsController with MockController
  def newWorkflow = FactoryGirl(Workflow).create()

  describe("WorkflowsController") {

    describe("shows workflows") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/workflows/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/workflows/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a workflow") {
      it("shows HTML response") {
        val workflow = newWorkflow
        val controller = createMockController
        controller.showResource(workflow.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Workflow]("item") should equal(Some(workflow))
        controller.renderCall.map(_.path) should equal(Some("/workflows/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/workflows/new"))
      }
    }

    describe("creates a workflow") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "tracker_id" -> Int.MaxValue.toString(),
          "old_status_id" -> Int.MaxValue.toString(),
          "new_status_id" -> Int.MaxValue.toString(),
          "role_id" -> Int.MaxValue.toString(),
          "assignee" -> "true",
          "author" -> "true",
          "type" -> "dummy",
          "field_name" -> "dummy",
          "rule" -> "dummy")
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
      val workflow = newWorkflow
      val controller = createMockController
      controller.editResource(workflow.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/workflows/edit"))
    }

    it("updates a workflow") {
      val workflow = newWorkflow
      val controller = createMockController
      controller.prepareParams(
        "tracker_id" -> Int.MaxValue.toString(),
        "old_status_id" -> Int.MaxValue.toString(),
        "new_status_id" -> Int.MaxValue.toString(),
        "role_id" -> Int.MaxValue.toString(),
        "assignee" -> "true",
        "author" -> "true",
        "type" -> "dummy",
        "field_name" -> "dummy",
        "rule" -> "dummy")
      controller.updateResource(workflow.id)
      controller.status should equal(200)
    }

    it("destroys a workflow") {
      val workflow = newWorkflow
      val controller = createMockController
      controller.destroyResource(workflow.id)
      controller.status should equal(200)
    }

  }

}
