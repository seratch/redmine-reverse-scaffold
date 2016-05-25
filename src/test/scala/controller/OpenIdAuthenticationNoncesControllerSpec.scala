package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class OpenIdAuthenticationNoncesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    OpenIdAuthenticationNonce.deleteAll()
  }

  def createMockController = new OpenIdAuthenticationNoncesController with MockController
  def newOpenIdAuthenticationNonce = FactoryGirl(OpenIdAuthenticationNonce).create()

  describe("OpenIdAuthenticationNoncesController") {

    describe("shows open id authentication nonces") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationNonces/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationNonces/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a open id authentication nonce") {
      it("shows HTML response") {
        val openIdAuthenticationNonce = newOpenIdAuthenticationNonce
        val controller = createMockController
        controller.showResource(openIdAuthenticationNonce.id)
        controller.status should equal(200)
        controller.getFromRequestScope[OpenIdAuthenticationNonce]("item") should equal(Some(openIdAuthenticationNonce))
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationNonces/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationNonces/new"))
      }
    }

    describe("creates a open id authentication nonce") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "timestamp" -> Int.MaxValue.toString(),
          "server_url" -> "dummy",
          "salt" -> "dummy")
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
      val openIdAuthenticationNonce = newOpenIdAuthenticationNonce
      val controller = createMockController
      controller.editResource(openIdAuthenticationNonce.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationNonces/edit"))
    }

    it("updates a open id authentication nonce") {
      val openIdAuthenticationNonce = newOpenIdAuthenticationNonce
      val controller = createMockController
      controller.prepareParams(
        "timestamp" -> Int.MaxValue.toString(),
        "server_url" -> "dummy",
        "salt" -> "dummy")
      controller.updateResource(openIdAuthenticationNonce.id)
      controller.status should equal(200)
    }

    it("destroys a open id authentication nonce") {
      val openIdAuthenticationNonce = newOpenIdAuthenticationNonce
      val controller = createMockController
      controller.destroyResource(openIdAuthenticationNonce.id)
      controller.status should equal(200)
    }

  }

}
