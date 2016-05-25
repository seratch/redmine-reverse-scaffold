package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class CommentsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Comment.deleteAll()
  }

  def createMockController = new CommentsController with MockController
  def newComment = FactoryGirl(Comment).create()

  describe("CommentsController") {

    describe("shows comments") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/comments/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/comments/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a comment") {
      it("shows HTML response") {
        val comment = newComment
        val controller = createMockController
        controller.showResource(comment.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Comment]("item") should equal(Some(comment))
        controller.renderCall.map(_.path) should equal(Some("/comments/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/comments/new"))
      }
    }

    describe("creates a comment") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "commented_type" -> "dummy",
          "commented_id" -> Int.MaxValue.toString(),
          "author_id" -> Int.MaxValue.toString(),
          "comments" -> "dummy",
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
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
      val comment = newComment
      val controller = createMockController
      controller.editResource(comment.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/comments/edit"))
    }

    it("updates a comment") {
      val comment = newComment
      val controller = createMockController
      controller.prepareParams(
        "commented_type" -> "dummy",
        "commented_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "comments" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(comment.id)
      controller.status should equal(200)
    }

    it("destroys a comment") {
      val comment = newComment
      val controller = createMockController
      controller.destroyResource(comment.id)
      controller.status should equal(200)
    }

  }

}
