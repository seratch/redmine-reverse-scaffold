package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class WikiContentsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    WikiContent.deleteAll()
  }

  def createMockController = new WikiContentsController with MockController
  def newWikiContent = FactoryGirl(WikiContent).create()

  describe("WikiContentsController") {

    describe("shows wiki contents") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContents/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContents/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a wiki content") {
      it("shows HTML response") {
        val wikiContent = newWikiContent
        val controller = createMockController
        controller.showResource(wikiContent.id)
        controller.status should equal(200)
        controller.getFromRequestScope[WikiContent]("item") should equal(Some(wikiContent))
        controller.renderCall.map(_.path) should equal(Some("/wikiContents/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContents/new"))
      }
    }

    describe("creates a wiki content") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "page_id" -> Int.MaxValue.toString(),
          "author_id" -> Int.MaxValue.toString(),
          "text" -> "dummy",
          "comments" -> "dummy",
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "version" -> Int.MaxValue.toString())
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
      val wikiContent = newWikiContent
      val controller = createMockController
      controller.editResource(wikiContent.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContents/edit"))
    }

    it("updates a wiki content") {
      val wikiContent = newWikiContent
      val controller = createMockController
      controller.prepareParams(
        "page_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "text" -> "dummy",
        "comments" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "version" -> Int.MaxValue.toString())
      controller.updateResource(wikiContent.id)
      controller.status should equal(200)
    }

    it("destroys a wiki content") {
      val wikiContent = newWikiContent
      val controller = createMockController
      controller.destroyResource(wikiContent.id)
      controller.status should equal(200)
    }

  }

}
