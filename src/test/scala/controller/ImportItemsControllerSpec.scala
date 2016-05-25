package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class ImportItemsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    ImportItem.deleteAll()
  }

  def createMockController = new ImportItemsController with MockController
  def newImportItem = FactoryGirl(ImportItem).create()

  describe("ImportItemsController") {

    describe("shows import items") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/importItems/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/importItems/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a import item") {
      it("shows HTML response") {
        val importItem = newImportItem
        val controller = createMockController
        controller.showResource(importItem.id)
        controller.status should equal(200)
        controller.getFromRequestScope[ImportItem]("item") should equal(Some(importItem))
        controller.renderCall.map(_.path) should equal(Some("/importItems/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/importItems/new"))
      }
    }

    describe("creates a import item") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "import_id" -> Int.MaxValue.toString(),
          "position" -> Int.MaxValue.toString(),
          "obj_id" -> Int.MaxValue.toString(),
          "message" -> "dummy")
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
      val importItem = newImportItem
      val controller = createMockController
      controller.editResource(importItem.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/importItems/edit"))
    }

    it("updates a import item") {
      val importItem = newImportItem
      val controller = createMockController
      controller.prepareParams(
        "import_id" -> Int.MaxValue.toString(),
        "position" -> Int.MaxValue.toString(),
        "obj_id" -> Int.MaxValue.toString(),
        "message" -> "dummy")
      controller.updateResource(importItem.id)
      controller.status should equal(200)
    }

    it("destroys a import item") {
      val importItem = newImportItem
      val controller = createMockController
      controller.destroyResource(importItem.id)
      controller.status should equal(200)
    }

  }

}
