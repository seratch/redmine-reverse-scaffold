package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class SettingsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Setting.deleteAll()
  }

  def createMockController = new SettingsController with MockController
  def newSetting = FactoryGirl(Setting).create()

  describe("SettingsController") {

    describe("shows settings") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/settings/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/settings/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a setting") {
      it("shows HTML response") {
        val setting = newSetting
        val controller = createMockController
        controller.showResource(setting.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Setting]("item") should equal(Some(setting))
        controller.renderCall.map(_.path) should equal(Some("/settings/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/settings/new"))
      }
    }

    describe("creates a setting") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "value" -> "dummy",
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
      val setting = newSetting
      val controller = createMockController
      controller.editResource(setting.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/settings/edit"))
    }

    it("updates a setting") {
      val setting = newSetting
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "value" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(setting.id)
      controller.status should equal(200)
    }

    it("destroys a setting") {
      val setting = newSetting
      val controller = createMockController
      controller.destroyResource(setting.id)
      controller.status should equal(200)
    }

  }

}
