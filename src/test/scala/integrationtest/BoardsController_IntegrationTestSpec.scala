package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class BoardsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.boards, "/*")

  override def afterAll() {
    super.afterAll()
    Board.deleteAll()
  }

  def newBoard = FactoryGirl(Board).create()

  it should "show boards" in {
    get("/boards") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/boards/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/boards.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/boards.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a board in detail" in {
    get(s"/boards/${newBoard.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/boards/${newBoard.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/boards/${newBoard.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/boards/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a board" in {
    post(s"/boards",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "description" -> "dummy",
      "position" -> Int.MaxValue.toString(),
      "topics_count" -> Int.MaxValue.toString(),
      "messages_count" -> Int.MaxValue.toString(),
      "last_message_id" -> Int.MaxValue.toString(),
      "parent_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/boards",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "description" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "topics_count" -> Int.MaxValue.toString(),
        "messages_count" -> Int.MaxValue.toString(),
        "last_message_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Board.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/boards/${newBoard.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a board" in {
    put(s"/boards/${newBoard.id}",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "description" -> "dummy",
      "position" -> Int.MaxValue.toString(),
      "topics_count" -> Int.MaxValue.toString(),
      "messages_count" -> Int.MaxValue.toString(),
      "last_message_id" -> Int.MaxValue.toString(),
      "parent_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/boards/${newBoard.id}",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "description" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "topics_count" -> Int.MaxValue.toString(),
        "messages_count" -> Int.MaxValue.toString(),
        "last_message_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a board" in {
    delete(s"/boards/${newBoard.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/boards/${newBoard.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
