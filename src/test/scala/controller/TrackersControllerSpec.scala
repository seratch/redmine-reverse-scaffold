package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class TrackersControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Tracker.deleteAll()
  }

  def createMockController = new TrackersController with MockController
  def newTracker = FactoryGirl(Tracker).create()

  describe("TrackersController") {

    describe("shows trackers") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/trackers/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/trackers/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a tracker") {
      it("shows HTML response") {
        val tracker = newTracker
        val controller = createMockController
        controller.showResource(tracker.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Tracker]("item") should equal(Some(tracker))
        controller.renderCall.map(_.path) should equal(Some("/trackers/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/trackers/new"))
      }
    }

    describe("creates a tracker") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "is_in_chlog" -> "true",
          "position" -> Int.MaxValue.toString(),
          "is_in_roadmap" -> "true",
          "fields_bits" -> Int.MaxValue.toString(),
          "default_status_id" -> Int.MaxValue.toString())
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
      val tracker = newTracker
      val controller = createMockController
      controller.editResource(tracker.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/trackers/edit"))
    }

    it("updates a tracker") {
      val tracker = newTracker
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "is_in_chlog" -> "true",
        "position" -> Int.MaxValue.toString(),
        "is_in_roadmap" -> "true",
        "fields_bits" -> Int.MaxValue.toString(),
        "default_status_id" -> Int.MaxValue.toString())
      controller.updateResource(tracker.id)
      controller.status should equal(200)
    }

    it("destroys a tracker") {
      val tracker = newTracker
      val controller = createMockController
      controller.destroyResource(tracker.id)
      controller.status should equal(200)
    }

  }

}
