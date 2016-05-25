package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class CustomFieldsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    CustomField.deleteAll()
  }

  def createMockController = new CustomFieldsController with MockController
  def newCustomField = FactoryGirl(CustomField).create()

  describe("CustomFieldsController") {

    describe("shows custom fields") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFields/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFields/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a custom field") {
      it("shows HTML response") {
        val customField = newCustomField
        val controller = createMockController
        controller.showResource(customField.id)
        controller.status should equal(200)
        controller.getFromRequestScope[CustomField]("item") should equal(Some(customField))
        controller.renderCall.map(_.path) should equal(Some("/customFields/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFields/new"))
      }
    }

    describe("creates a custom field") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "type" -> "dummy",
          "name" -> "dummy",
          "field_format" -> "dummy",
          "possible_values" -> "dummy",
          "regexp" -> "dummy",
          "min_length" -> Int.MaxValue.toString(),
          "max_length" -> Int.MaxValue.toString(),
          "is_required" -> "true",
          "is_for_all" -> "true",
          "is_filter" -> "true",
          "position" -> Int.MaxValue.toString(),
          "searchable" -> "true",
          "default_value" -> "dummy",
          "editable" -> "true",
          "visible" -> "true",
          "multiple" -> "true",
          "format_store" -> "dummy",
          "description" -> "dummy")
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
      val customField = newCustomField
      val controller = createMockController
      controller.editResource(customField.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customFields/edit"))
    }

    it("updates a custom field") {
      val customField = newCustomField
      val controller = createMockController
      controller.prepareParams(
        "type" -> "dummy",
        "name" -> "dummy",
        "field_format" -> "dummy",
        "possible_values" -> "dummy",
        "regexp" -> "dummy",
        "min_length" -> Int.MaxValue.toString(),
        "max_length" -> Int.MaxValue.toString(),
        "is_required" -> "true",
        "is_for_all" -> "true",
        "is_filter" -> "true",
        "position" -> Int.MaxValue.toString(),
        "searchable" -> "true",
        "default_value" -> "dummy",
        "editable" -> "true",
        "visible" -> "true",
        "multiple" -> "true",
        "format_store" -> "dummy",
        "description" -> "dummy")
      controller.updateResource(customField.id)
      controller.status should equal(200)
    }

    it("destroys a custom field") {
      val customField = newCustomField
      val controller = createMockController
      controller.destroyResource(customField.id)
      controller.status should equal(200)
    }

  }

}
