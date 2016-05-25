package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class AuthSourcesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    AuthSource.deleteAll()
  }

  def createMockController = new AuthSourcesController with MockController
  def newAuthSource = FactoryGirl(AuthSource).create()

  describe("AuthSourcesController") {

    describe("shows auth sources") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/authSources/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/authSources/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a auth source") {
      it("shows HTML response") {
        val authSource = newAuthSource
        val controller = createMockController
        controller.showResource(authSource.id)
        controller.status should equal(200)
        controller.getFromRequestScope[AuthSource]("item") should equal(Some(authSource))
        controller.renderCall.map(_.path) should equal(Some("/authSources/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/authSources/new"))
      }
    }

    describe("creates a auth source") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "type" -> "dummy",
          "name" -> "dummy",
          "host" -> "dummy",
          "port" -> Int.MaxValue.toString(),
          "account" -> "dummy",
          "account_password" -> "dummy",
          "base_dn" -> "dummy",
          "attr_login" -> "dummy",
          "attr_firstname" -> "dummy",
          "attr_lastname" -> "dummy",
          "attr_mail" -> "dummy",
          "onthefly_register" -> "true",
          "tls" -> "true",
          "filter" -> "dummy",
          "timeout" -> Int.MaxValue.toString())
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
      val authSource = newAuthSource
      val controller = createMockController
      controller.editResource(authSource.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/authSources/edit"))
    }

    it("updates a auth source") {
      val authSource = newAuthSource
      val controller = createMockController
      controller.prepareParams(
        "type" -> "dummy",
        "name" -> "dummy",
        "host" -> "dummy",
        "port" -> Int.MaxValue.toString(),
        "account" -> "dummy",
        "account_password" -> "dummy",
        "base_dn" -> "dummy",
        "attr_login" -> "dummy",
        "attr_firstname" -> "dummy",
        "attr_lastname" -> "dummy",
        "attr_mail" -> "dummy",
        "onthefly_register" -> "true",
        "tls" -> "true",
        "filter" -> "dummy",
        "timeout" -> Int.MaxValue.toString())
      controller.updateResource(authSource.id)
      controller.status should equal(200)
    }

    it("destroys a auth source") {
      val authSource = newAuthSource
      val controller = createMockController
      controller.destroyResource(authSource.id)
      controller.status should equal(200)
    }

  }

}
