package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class UserPreferencesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    UserPreference.deleteAll()
  }

  def createMockController = new UserPreferencesController with MockController
  def newUserPreference = FactoryGirl(UserPreference).create()

  describe("UserPreferencesController") {

    describe("shows user preferences") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/userPreferences/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/userPreferences/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a user preference") {
      it("shows HTML response") {
        val userPreference = newUserPreference
        val controller = createMockController
        controller.showResource(userPreference.id)
        controller.status should equal(200)
        controller.getFromRequestScope[UserPreference]("item") should equal(Some(userPreference))
        controller.renderCall.map(_.path) should equal(Some("/userPreferences/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/userPreferences/new"))
      }
    }

    describe("creates a user preference") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "user_id" -> Int.MaxValue.toString(),
          "others" -> "dummy",
          "hide_mail" -> "true",
          "time_zone" -> "dummy")
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
      val userPreference = newUserPreference
      val controller = createMockController
      controller.editResource(userPreference.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/userPreferences/edit"))
    }

    it("updates a user preference") {
      val userPreference = newUserPreference
      val controller = createMockController
      controller.prepareParams(
        "user_id" -> Int.MaxValue.toString(),
        "others" -> "dummy",
        "hide_mail" -> "true",
        "time_zone" -> "dummy")
      controller.updateResource(userPreference.id)
      controller.status should equal(200)
    }

    it("destroys a user preference") {
      val userPreference = newUserPreference
      val controller = createMockController
      controller.destroyResource(userPreference.id)
      controller.status should equal(200)
    }

  }

}
