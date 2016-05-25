package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class WikiContentVersionsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    WikiContentVersion.deleteAll()
  }

  def createMockController = new WikiContentVersionsController with MockController
  def newWikiContentVersion = FactoryGirl(WikiContentVersion).create()

  describe("WikiContentVersionsController") {

    describe("shows wiki content versions") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContentVersions/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContentVersions/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a wiki content version") {
      it("shows HTML response") {
        val wikiContentVersion = newWikiContentVersion
        val controller = createMockController
        controller.showResource(wikiContentVersion.id)
        controller.status should equal(200)
        controller.getFromRequestScope[WikiContentVersion]("item") should equal(Some(wikiContentVersion))
        controller.renderCall.map(_.path) should equal(Some("/wikiContentVersions/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContentVersions/new"))
      }
    }

    describe("creates a wiki content version") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "wiki_content_id" -> Int.MaxValue.toString(),
          "page_id" -> Int.MaxValue.toString(),
          "author_id" -> Int.MaxValue.toString(),
          "data" -> "dummy",
          "compression" -> "dummy",
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
      val wikiContentVersion = newWikiContentVersion
      val controller = createMockController
      controller.editResource(wikiContentVersion.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/wikiContentVersions/edit"))
    }

    it("updates a wiki content version") {
      val wikiContentVersion = newWikiContentVersion
      val controller = createMockController
      controller.prepareParams(
        "wiki_content_id" -> Int.MaxValue.toString(),
        "page_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "data" -> "dummy",
        "compression" -> "dummy",
        "comments" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "version" -> Int.MaxValue.toString())
      controller.updateResource(wikiContentVersion.id)
      controller.status should equal(200)
    }

    it("destroys a wiki content version") {
      val wikiContentVersion = newWikiContentVersion
      val controller = createMockController
      controller.destroyResource(wikiContentVersion.id)
      controller.status should equal(200)
    }

  }

}
