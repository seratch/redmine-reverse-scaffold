package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class NewsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    News.deleteAll()
  }

  def createMockController = new NewsController with MockController
  def newNews = FactoryGirl(News).create()

  describe("NewsController") {

    describe("shows news") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/news/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/news/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a news") {
      it("shows HTML response") {
        val news = newNews
        val controller = createMockController
        controller.showResource(news.id)
        controller.status should equal(200)
        controller.getFromRequestScope[News]("item") should equal(Some(news))
        controller.renderCall.map(_.path) should equal(Some("/news/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/news/new"))
      }
    }

    describe("creates a news") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "title" -> "dummy",
          "summary" -> "dummy",
          "description" -> "dummy",
          "author_id" -> Int.MaxValue.toString(),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "comments_count" -> Int.MaxValue.toString())
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
      val news = newNews
      val controller = createMockController
      controller.editResource(news.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/news/edit"))
    }

    it("updates a news") {
      val news = newNews
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "summary" -> "dummy",
        "description" -> "dummy",
        "author_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "comments_count" -> Int.MaxValue.toString())
      controller.updateResource(news.id)
      controller.status should equal(200)
    }

    it("destroys a news") {
      val news = newNews
      val controller = createMockController
      controller.destroyResource(news.id)
      controller.status should equal(200)
    }

  }

}
