package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class TokensController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.tokens, "/*")

  override def afterAll() {
    super.afterAll()
    Token.deleteAll()
  }

  def newToken = FactoryGirl(Token).create()

  it should "show tokens" in {
    get("/tokens") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/tokens/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/tokens.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/tokens.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a token in detail" in {
    get(s"/tokens/${newToken.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/tokens/${newToken.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/tokens/${newToken.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/tokens/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a token" in {
    post(s"/tokens",
      "user_id" -> Int.MaxValue.toString(),
      "action" -> "dummy",
      "value" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/tokens",
        "user_id" -> Int.MaxValue.toString(),
        "action" -> "dummy",
        "value" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Token.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/tokens/${newToken.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a token" in {
    put(s"/tokens/${newToken.id}",
      "user_id" -> Int.MaxValue.toString(),
      "action" -> "dummy",
      "value" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/tokens/${newToken.id}",
        "user_id" -> Int.MaxValue.toString(),
        "action" -> "dummy",
        "value" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a token" in {
    delete(s"/tokens/${newToken.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/tokens/${newToken.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
