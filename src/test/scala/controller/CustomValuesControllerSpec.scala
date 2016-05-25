package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class CustomValuesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    CustomValue.deleteAll()
  }

  def createMockController = new CustomValuesController with MockController
  def newCustomValue = FactoryGirl(CustomValue).create()

  describe("CustomValuesController") {

    describe("shows custom values") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customValues/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customValues/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a custom value") {
      it("shows HTML response") {
        val customValue = newCustomValue
        val controller = createMockController
        controller.showResource(customValue.id)
        controller.status should equal(200)
        controller.getFromRequestScope[CustomValue]("item") should equal(Some(customValue))
        controller.renderCall.map(_.path) should equal(Some("/customValues/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customValues/new"))
      }
    }

    describe("creates a custom value") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "customized_type" -> "dummy",
          "customized_id" -> Int.MaxValue.toString(),
          "custom_field_id" -> Int.MaxValue.toString(),
          "value" -> "dummy")
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
      val customValue = newCustomValue
      val controller = createMockController
      controller.editResource(customValue.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/customValues/edit"))
    }

    it("updates a custom value") {
      val customValue = newCustomValue
      val controller = createMockController
      controller.prepareParams(
        "customized_type" -> "dummy",
        "customized_id" -> Int.MaxValue.toString(),
        "custom_field_id" -> Int.MaxValue.toString(),
        "value" -> "dummy")
      controller.updateResource(customValue.id)
      controller.status should equal(200)
    }

    it("destroys a custom value") {
      val customValue = newCustomValue
      val controller = createMockController
      controller.destroyResource(customValue.id)
      controller.status should equal(200)
    }

  }

}
