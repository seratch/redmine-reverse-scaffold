package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class TokensControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Token.deleteAll()
  }

  def createMockController = new TokensController with MockController
  def newToken = FactoryGirl(Token).create()

  describe("TokensController") {

    describe("shows tokens") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/tokens/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/tokens/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a token") {
      it("shows HTML response") {
        val token = newToken
        val controller = createMockController
        controller.showResource(token.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Token]("item") should equal(Some(token))
        controller.renderCall.map(_.path) should equal(Some("/tokens/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/tokens/new"))
      }
    }

    describe("creates a token") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "user_id" -> Int.MaxValue.toString(),
          "action" -> "dummy",
          "value" -> "dummy",
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
      val token = newToken
      val controller = createMockController
      controller.editResource(token.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/tokens/edit"))
    }

    it("updates a token") {
      val token = newToken
      val controller = createMockController
      controller.prepareParams(
        "user_id" -> Int.MaxValue.toString(),
        "action" -> "dummy",
        "value" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(token.id)
      controller.status should equal(200)
    }

    it("destroys a token") {
      val token = newToken
      val controller = createMockController
      controller.destroyResource(token.id)
      controller.status should equal(200)
    }

  }

}
