package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class DocumentsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Document.deleteAll()
  }

  def createMockController = new DocumentsController with MockController
  def newDocument = FactoryGirl(Document).create()

  describe("DocumentsController") {

    describe("shows documents") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/documents/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/documents/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a document") {
      it("shows HTML response") {
        val document = newDocument
        val controller = createMockController
        controller.showResource(document.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Document]("item") should equal(Some(document))
        controller.renderCall.map(_.path) should equal(Some("/documents/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/documents/new"))
      }
    }

    describe("creates a document") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "category_id" -> Int.MaxValue.toString(),
          "title" -> "dummy",
          "description" -> "dummy",
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
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
      val document = newDocument
      val controller = createMockController
      controller.editResource(document.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/documents/edit"))
    }

    it("updates a document") {
      val document = newDocument
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "category_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "description" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(document.id)
      controller.status should equal(200)
    }

    it("destroys a document") {
      val document = newDocument
      val controller = createMockController
      controller.destroyResource(document.id)
      controller.status should equal(200)
    }

  }

}
