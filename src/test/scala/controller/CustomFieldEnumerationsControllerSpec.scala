package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class CustomFieldEnumerationsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    CustomFieldEnumeration.deleteAll()
  }

  def createMockController = new CustomFieldEnumerationsController with MockController
  def newCustomFieldEnumeration = FactoryGirl(CustomFieldEnumeration).create()

  describe("CustomFieldEnumerationsController") {

    describe("shows custom field enumerations") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFieldEnumerations/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFieldEnumerations/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a custom field enumeration") {
      it("shows HTML response") {
        val customFieldEnumeration = newCustomFieldEnumeration
        val controller = createMockController
        controller.showResource(customFieldEnumeration.id)
        controller.status should equal(200)
        controller.getFromRequestScope[CustomFieldEnumeration]("item") should equal(Some(customFieldEnumeration))
        controller.renderCall.map(_.path) should equal(Some("/customFieldEnumerations/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFieldEnumerations/new"))
      }
    }

    describe("creates a custom field enumeration") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "custom_field_id" -> Int.MaxValue.toString(),
          "name" -> "dummy",
          "active" -> "true",
          "position" -> Int.MaxValue.toString())
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
      val customFieldEnumeration = newCustomFieldEnumeration
      val controller = createMockController
      controller.editResource(customFieldEnumeration.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFieldEnumerations/edit"))
    }

    it("updates a custom field enumeration") {
      val customFieldEnumeration = newCustomFieldEnumeration
      val controller = createMockController
      controller.prepareParams(
        "custom_field_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "active" -> "true",
        "position" -> Int.MaxValue.toString())
      controller.updateResource(customFieldEnumeration.id)
      controller.status should equal(200)
    }

    it("destroys a custom field enumeration") {
      val customFieldEnumeration = newCustomFieldEnumeration
      val controller = createMockController
      controller.destroyResource(customFieldEnumeration.id)
      controller.status should equal(200)
    }

  }

}
