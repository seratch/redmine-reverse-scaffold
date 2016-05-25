package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class WikisControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Wiki.deleteAll()
  }

  def createMockController = new WikisController with MockController
  def newWiki = FactoryGirl(Wiki).create()

  describe("WikisController") {

    describe("shows wikis") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikis/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikis/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a wiki") {
      it("shows HTML response") {
        val wiki = newWiki
        val controller = createMockController
        controller.showResource(wiki.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Wiki]("item") should equal(Some(wiki))
        controller.renderCall.map(_.path) should equal(Some("/wikis/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikis/new"))
      }
    }

    describe("creates a wiki") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "start_page" -> "dummy",
          "status" -> Int.MaxValue.toString())
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
      val wiki = newWiki
      val controller = createMockController
      controller.editResource(wiki.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikis/edit"))
    }

    it("updates a wiki") {
      val wiki = newWiki
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "start_page" -> "dummy",
        "status" -> Int.MaxValue.toString())
      controller.updateResource(wiki.id)
      controller.status should equal(200)
    }

    it("destroys a wiki") {
      val wiki = newWiki
      val controller = createMockController
      controller.destroyResource(wiki.id)
      controller.status should equal(200)
    }

  }

}
