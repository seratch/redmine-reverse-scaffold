package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class MessagesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Message.deleteAll()
  }

  def createMockController = new MessagesController with MockController
  def newMessage = FactoryGirl(Message).create()

  describe("MessagesController") {

    describe("shows messages") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/messages/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/messages/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a message") {
      it("shows HTML response") {
        val message = newMessage
        val controller = createMockController
        controller.showResource(message.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Message]("item") should equal(Some(message))
        controller.renderCall.map(_.path) should equal(Some("/messages/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/messages/new"))
      }
    }

    describe("creates a message") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "board_id" -> Int.MaxValue.toString(),
          "parent_id" -> Int.MaxValue.toString(),
          "subject" -> "dummy",
          "content" -> "dummy",
          "author_id" -> Int.MaxValue.toString(),
          "replies_count" -> Int.MaxValue.toString(),
          "last_reply_id" -> Int.MaxValue.toString(),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "locked" -> "true",
          "sticky" -> Int.MaxValue.toString())
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
      val message = newMessage
      val controller = createMockController
      controller.editResource(message.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/messages/edit"))
    }

    it("updates a message") {
      val message = newMessage
      val controller = createMockController
      controller.prepareParams(
        "board_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "subject" -> "dummy",
        "content" -> "dummy",
        "author_id" -> Int.MaxValue.toString(),
        "replies_count" -> Int.MaxValue.toString(),
        "last_reply_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "locked" -> "true",
        "sticky" -> Int.MaxValue.toString())
      controller.updateResource(message.id)
      controller.status should equal(200)
    }

    it("destroys a message") {
      val message = newMessage
      val controller = createMockController
      controller.destroyResource(message.id)
      controller.status should equal(200)
    }

  }

}
