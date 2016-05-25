package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class WatchersControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Watcher.deleteAll()
  }

  def createMockController = new WatchersController with MockController
  def newWatcher = FactoryGirl(Watcher).create()

  describe("WatchersController") {

    describe("shows watchers") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/watchers/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/watchers/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a watcher") {
      it("shows HTML response") {
        val watcher = newWatcher
        val controller = createMockController
        controller.showResource(watcher.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Watcher]("item") should equal(Some(watcher))
        controller.renderCall.map(_.path) should equal(Some("/watchers/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/watchers/new"))
      }
    }

    describe("creates a watcher") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "watchable_type" -> "dummy",
          "watchable_id" -> Int.MaxValue.toString(),
          "user_id" -> Int.MaxValue.toString())
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
      val watcher = newWatcher
      val controller = createMockController
      controller.editResource(watcher.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/watchers/edit"))
    }

    it("updates a watcher") {
      val watcher = newWatcher
      val controller = createMockController
      controller.prepareParams(
        "watchable_type" -> "dummy",
        "watchable_id" -> Int.MaxValue.toString(),
        "user_id" -> Int.MaxValue.toString())
      controller.updateResource(watcher.id)
      controller.status should equal(200)
    }

    it("destroys a watcher") {
      val watcher = newWatcher
      val controller = createMockController
      controller.destroyResource(watcher.id)
      controller.status should equal(200)
    }

  }

}
