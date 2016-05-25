package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class AttachmentsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Attachment.deleteAll()
  }

  def createMockController = new AttachmentsController with MockController
  def newAttachment = FactoryGirl(Attachment).create()

  describe("AttachmentsController") {

    describe("shows attachments") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/attachments/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/attachments/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a attachment") {
      it("shows HTML response") {
        val attachment = newAttachment
        val controller = createMockController
        controller.showResource(attachment.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Attachment]("item") should equal(Some(attachment))
        controller.renderCall.map(_.path) should equal(Some("/attachments/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/attachments/new"))
      }
    }

    describe("creates a attachment") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "container_id" -> Int.MaxValue.toString(),
          "container_type" -> "dummy",
          "filename" -> "dummy",
          "disk_filename" -> "dummy",
          "filesize" -> Long.MaxValue.toString(),
          "content_type" -> "dummy",
          "digest" -> "dummy",
          "downloads" -> Int.MaxValue.toString(),
          "author_id" -> Int.MaxValue.toString(),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "description" -> "dummy",
          "disk_directory" -> "dummy")
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
      val attachment = newAttachment
      val controller = createMockController
      controller.editResource(attachment.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/attachments/edit"))
    }

    it("updates a attachment") {
      val attachment = newAttachment
      val controller = createMockController
      controller.prepareParams(
        "container_id" -> Int.MaxValue.toString(),
        "container_type" -> "dummy",
        "filename" -> "dummy",
        "disk_filename" -> "dummy",
        "filesize" -> Long.MaxValue.toString(),
        "content_type" -> "dummy",
        "digest" -> "dummy",
        "downloads" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "description" -> "dummy",
        "disk_directory" -> "dummy")
      controller.updateResource(attachment.id)
      controller.status should equal(200)
    }

    it("destroys a attachment") {
      val attachment = newAttachment
      val controller = createMockController
      controller.destroyResource(attachment.id)
      controller.status should equal(200)
    }

  }

}
