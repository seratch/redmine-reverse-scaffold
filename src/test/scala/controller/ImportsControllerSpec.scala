package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class ImportsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Import.deleteAll()
  }

  def createMockController = new ImportsController with MockController
  def newImport = FactoryGirl(Import).create()

  describe("ImportsController") {

    describe("shows imports") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/imports/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/imports/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a import") {
      it("shows HTML response") {
        val import = newImport
        val controller = createMockController
        controller.showResource(import.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Import]("item") should equal(Some(import))
        controller.renderCall.map(_.path) should equal(Some("/imports/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/imports/new"))
      }
    }

    describe("creates a import") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "type" -> "dummy",
          "user_id" -> Int.MaxValue.toString(),
          "filename" -> "dummy",
          "settings" -> "dummy",
          "total_items" -> Int.MaxValue.toString(),
          "finished" -> "true",
          "created_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_at" -> skinny.util.DateTimeUtil.toString(new DateTime()))
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
      val import = newImport
      val controller = createMockController
      controller.editResource(import.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/imports/edit"))
    }

    it("updates a import") {
      val import = newImport
      val controller = createMockController
      controller.prepareParams(
        "type" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "filename" -> "dummy",
        "settings" -> "dummy",
        "total_items" -> Int.MaxValue.toString(),
        "finished" -> "true",
        "created_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_at" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(import.id)
      controller.status should equal(200)
    }

    it("destroys a import") {
      val import = newImport
      val controller = createMockController
      controller.destroyResource(import.id)
      controller.status should equal(200)
    }

  }

}
