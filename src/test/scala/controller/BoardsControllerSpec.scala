package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class BoardsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Board.deleteAll()
  }

  def createMockController = new BoardsController with MockController
  def newBoard = FactoryGirl(Board).create()

  describe("BoardsController") {

    describe("shows boards") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/boards/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/boards/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a board") {
      it("shows HTML response") {
        val board = newBoard
        val controller = createMockController
        controller.showResource(board.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Board]("item") should equal(Some(board))
        controller.renderCall.map(_.path) should equal(Some("/boards/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/boards/new"))
      }
    }

    describe("creates a board") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "name" -> "dummy",
          "description" -> "dummy",
          "position" -> Int.MaxValue.toString(),
          "topics_count" -> Int.MaxValue.toString(),
          "messages_count" -> Int.MaxValue.toString(),
          "last_message_id" -> Int.MaxValue.toString(),
          "parent_id" -> Int.MaxValue.toString())
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
      val board = newBoard
      val controller = createMockController
      controller.editResource(board.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/boards/edit"))
    }

    it("updates a board") {
      val board = newBoard
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "description" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "topics_count" -> Int.MaxValue.toString(),
        "messages_count" -> Int.MaxValue.toString(),
        "last_message_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString())
      controller.updateResource(board.id)
      controller.status should equal(200)
    }

    it("destroys a board") {
      val board = newBoard
      val controller = createMockController
      controller.destroyResource(board.id)
      controller.status should equal(200)
    }

  }

}
