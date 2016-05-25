package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class EnumerationsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Enumeration.deleteAll()
  }

  def createMockController = new EnumerationsController with MockController
  def newEnumeration = FactoryGirl(Enumeration).create()

  describe("EnumerationsController") {

    describe("shows enumerations") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enumerations/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enumerations/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a enumeration") {
      it("shows HTML response") {
        val enumeration = newEnumeration
        val controller = createMockController
        controller.showResource(enumeration.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Enumeration]("item") should equal(Some(enumeration))
        controller.renderCall.map(_.path) should equal(Some("/enumerations/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enumerations/new"))
      }
    }

    describe("creates a enumeration") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "position" -> Int.MaxValue.toString(),
          "is_default" -> "true",
          "type" -> "dummy",
          "active" -> "true",
          "project_id" -> Int.MaxValue.toString(),
          "parent_id" -> Int.MaxValue.toString(),
          "position_name" -> "dummy")
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
      val enumeration = newEnumeration
      val controller = createMockController
      controller.editResource(enumeration.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enumerations/edit"))
    }

    it("updates a enumeration") {
      val enumeration = newEnumeration
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "is_default" -> "true",
        "type" -> "dummy",
        "active" -> "true",
        "project_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "position_name" -> "dummy")
      controller.updateResource(enumeration.id)
      controller.status should equal(200)
    }

    it("destroys a enumeration") {
      val enumeration = newEnumeration
      val controller = createMockController
      controller.destroyResource(enumeration.id)
      controller.status should equal(200)
    }

  }

}
