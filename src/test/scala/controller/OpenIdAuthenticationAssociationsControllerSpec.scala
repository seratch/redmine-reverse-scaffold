package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class OpenIdAuthenticationAssociationsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    OpenIdAuthenticationAssociation.deleteAll()
  }

  def createMockController = new OpenIdAuthenticationAssociationsController with MockController
  def newOpenIdAuthenticationAssociation = FactoryGirl(OpenIdAuthenticationAssociation).create()

  describe("OpenIdAuthenticationAssociationsController") {

    describe("shows open id authentication associations") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationAssociations/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationAssociations/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a open id authentication association") {
      it("shows HTML response") {
        val openIdAuthenticationAssociation = newOpenIdAuthenticationAssociation
        val controller = createMockController
        controller.showResource(openIdAuthenticationAssociation.id)
        controller.status should equal(200)
        controller.getFromRequestScope[OpenIdAuthenticationAssociation]("item") should equal(Some(openIdAuthenticationAssociation))
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationAssociations/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationAssociations/new"))
      }
    }

    describe("creates a open id authentication association") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "issued" -> Int.MaxValue.toString(),
          "lifetime" -> Int.MaxValue.toString(),
          "handle" -> "dummy",
          "assoc_type" -> "dummy",
          "server_url" -> "dummy",
          "secret" -> "dummy")
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
      val openIdAuthenticationAssociation = newOpenIdAuthenticationAssociation
      val controller = createMockController
      controller.editResource(openIdAuthenticationAssociation.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/openIdAuthenticationAssociations/edit"))
    }

    it("updates a open id authentication association") {
      val openIdAuthenticationAssociation = newOpenIdAuthenticationAssociation
      val controller = createMockController
      controller.prepareParams(
        "issued" -> Int.MaxValue.toString(),
        "lifetime" -> Int.MaxValue.toString(),
        "handle" -> "dummy",
        "assoc_type" -> "dummy",
        "server_url" -> "dummy",
        "secret" -> "dummy")
      controller.updateResource(openIdAuthenticationAssociation.id)
      controller.status should equal(200)
    }

    it("destroys a open id authentication association") {
      val openIdAuthenticationAssociation = newOpenIdAuthenticationAssociation
      val controller = createMockController
      controller.destroyResource(openIdAuthenticationAssociation.id)
      controller.status should equal(200)
    }

  }

}
