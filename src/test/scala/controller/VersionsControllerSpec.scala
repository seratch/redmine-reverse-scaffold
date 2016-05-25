package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class VersionsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Version.deleteAll()
  }

  def createMockController = new VersionsController with MockController
  def newVersion = FactoryGirl(Version).create()

  describe("VersionsController") {

    describe("shows versions") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/versions/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/versions/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a version") {
      it("shows HTML response") {
        val version = newVersion
        val controller = createMockController
        controller.showResource(version.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Version]("item") should equal(Some(version))
        controller.renderCall.map(_.path) should equal(Some("/versions/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/versions/new"))
      }
    }

    describe("creates a version") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "name" -> "dummy",
          "description" -> "dummy",
          "effective_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "wiki_page_title" -> "dummy",
          "status" -> "dummy",
          "sharing" -> "dummy")
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
      val version = newVersion
      val controller = createMockController
      controller.editResource(version.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/versions/edit"))
    }

    it("updates a version") {
      val version = newVersion
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "description" -> "dummy",
        "effective_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "wiki_page_title" -> "dummy",
        "status" -> "dummy",
        "sharing" -> "dummy")
      controller.updateResource(version.id)
      controller.status should equal(200)
    }

    it("destroys a version") {
      val version = newVersion
      val controller = createMockController
      controller.destroyResource(version.id)
      controller.status should equal(200)
    }

  }

}
