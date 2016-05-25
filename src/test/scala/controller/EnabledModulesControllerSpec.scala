package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class EnabledModulesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    EnabledModule.deleteAll()
  }

  def createMockController = new EnabledModulesController with MockController
  def newEnabledModule = FactoryGirl(EnabledModule).create()

  describe("EnabledModulesController") {

    describe("shows enabled modules") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enabledModules/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enabledModules/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a enabled module") {
      it("shows HTML response") {
        val enabledModule = newEnabledModule
        val controller = createMockController
        controller.showResource(enabledModule.id)
        controller.status should equal(200)
        controller.getFromRequestScope[EnabledModule]("item") should equal(Some(enabledModule))
        controller.renderCall.map(_.path) should equal(Some("/enabledModules/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enabledModules/new"))
      }
    }

    describe("creates a enabled module") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "name" -> "dummy")
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
      val enabledModule = newEnabledModule
      val controller = createMockController
      controller.editResource(enabledModule.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/enabledModules/edit"))
    }

    it("updates a enabled module") {
      val enabledModule = newEnabledModule
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy")
      controller.updateResource(enabledModule.id)
      controller.status should equal(200)
    }

    it("destroys a enabled module") {
      val enabledModule = newEnabledModule
      val controller = createMockController
      controller.destroyResource(enabledModule.id)
      controller.status should equal(200)
    }

  }

}
