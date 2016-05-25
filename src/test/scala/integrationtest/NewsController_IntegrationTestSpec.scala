package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class NewsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.news, "/*")

  override def afterAll() {
    super.afterAll()
    News.deleteAll()
  }

  def newNews = FactoryGirl(News).create()

  it should "show news" in {
    get("/news") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/news/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/news.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/news.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a news in detail" in {
    get(s"/news/${newNews.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/news/${newNews.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/news/${newNews.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/news/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a news" in {
    post(s"/news",
      "project_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "summary" -> "dummy",
      "description" -> "dummy",
      "author_id" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "comments_count" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/news",
        "project_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "summary" -> "dummy",
        "description" -> "dummy",
        "author_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "comments_count" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        News.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/news/${newNews.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a news" in {
    put(s"/news/${newNews.id}",
      "project_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "summary" -> "dummy",
      "description" -> "dummy",
      "author_id" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "comments_count" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/news/${newNews.id}",
        "project_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "summary" -> "dummy",
        "description" -> "dummy",
        "author_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "comments_count" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a news" in {
    delete(s"/news/${newNews.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/news/${newNews.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
