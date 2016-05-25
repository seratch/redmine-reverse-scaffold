package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class MessagesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.messages, "/*")

  override def afterAll() {
    super.afterAll()
    Message.deleteAll()
  }

  def newMessage = FactoryGirl(Message).create()

  it should "show messages" in {
    get("/messages") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/messages/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/messages.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/messages.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a message in detail" in {
    get(s"/messages/${newMessage.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/messages/${newMessage.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/messages/${newMessage.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/messages/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a message" in {
    post(s"/messages",
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
      "sticky" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/messages",
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
        "sticky" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Message.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/messages/${newMessage.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a message" in {
    put(s"/messages/${newMessage.id}",
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
      "sticky" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/messages/${newMessage.id}",
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
        "sticky" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a message" in {
    delete(s"/messages/${newMessage.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/messages/${newMessage.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
