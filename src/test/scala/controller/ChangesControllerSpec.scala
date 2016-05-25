package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class ChangesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Change.deleteAll()
  }

  def createMockController = new ChangesController with MockController
  def newChange = FactoryGirl(Change).create()

  describe("ChangesController") {

    describe("shows changes") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changes/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changes/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a change") {
      it("shows HTML response") {
        val change = newChange
        val controller = createMockController
        controller.showResource(change.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Change]("item") should equal(Some(change))
        controller.renderCall.map(_.path) should equal(Some("/changes/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changes/new"))
      }
    }

    describe("creates a change") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "changeset_id" -> Int.MaxValue.toString(),
          "action" -> "dummy",
          "path" -> "dummy",
          "from_path" -> "dummy",
          "from_revision" -> "dummy",
          "revision" -> "dummy",
          "branch" -> "dummy")
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
      val change = newChange
      val controller = createMockController
      controller.editResource(change.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changes/edit"))
    }

    it("updates a change") {
      val change = newChange
      val controller = createMockController
      controller.prepareParams(
        "changeset_id" -> Int.MaxValue.toString(),
        "action" -> "dummy",
        "path" -> "dummy",
        "from_path" -> "dummy",
        "from_revision" -> "dummy",
        "revision" -> "dummy",
        "branch" -> "dummy")
      controller.updateResource(change.id)
      controller.status should equal(200)
    }

    it("destroys a change") {
      val change = newChange
      val controller = createMockController
      controller.destroyResource(change.id)
      controller.status should equal(200)
    }

  }

}
