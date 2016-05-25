package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class WikiPagesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    WikiPage.deleteAll()
  }

  def createMockController = new WikiPagesController with MockController
  def newWikiPage = FactoryGirl(WikiPage).create()

  describe("WikiPagesController") {

    describe("shows wiki pages") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiPages/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiPages/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a wiki page") {
      it("shows HTML response") {
        val wikiPage = newWikiPage
        val controller = createMockController
        controller.showResource(wikiPage.id)
        controller.status should equal(200)
        controller.getFromRequestScope[WikiPage]("item") should equal(Some(wikiPage))
        controller.renderCall.map(_.path) should equal(Some("/wikiPages/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiPages/new"))
      }
    }

    describe("creates a wiki page") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "wiki_id" -> Int.MaxValue.toString(),
          "title" -> "dummy",
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "protected" -> "true",
          "parent_id" -> Int.MaxValue.toString())
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
      val wikiPage = newWikiPage
      val controller = createMockController
      controller.editResource(wikiPage.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiPages/edit"))
    }

    it("updates a wiki page") {
      val wikiPage = newWikiPage
      val controller = createMockController
      controller.prepareParams(
        "wiki_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "protected" -> "true",
        "parent_id" -> Int.MaxValue.toString())
      controller.updateResource(wikiPage.id)
      controller.status should equal(200)
    }

    it("destroys a wiki page") {
      val wikiPage = newWikiPage
      val controller = createMockController
      controller.destroyResource(wikiPage.id)
      controller.status should equal(200)
    }

  }

}
