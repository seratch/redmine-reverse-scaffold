package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WikiPagesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.wikiPages, "/*")

  override def afterAll() {
    super.afterAll()
    WikiPage.deleteAll()
  }

  def newWikiPage = FactoryGirl(WikiPage).create()

  it should "show wiki pages" in {
    get("/wiki_pages") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_pages/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_pages.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_pages.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a wiki page in detail" in {
    get(s"/wiki_pages/${newWikiPage.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_pages/${newWikiPage.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_pages/${newWikiPage.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/wiki_pages/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a wiki page" in {
    post(s"/wiki_pages",
      "wiki_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "protected" -> "true",
      "parent_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/wiki_pages",
        "wiki_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "protected" -> "true",
        "parent_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        WikiPage.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/wiki_pages/${newWikiPage.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a wiki page" in {
    put(s"/wiki_pages/${newWikiPage.id}",
      "wiki_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "protected" -> "true",
      "parent_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/wiki_pages/${newWikiPage.id}",
        "wiki_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "protected" -> "true",
        "parent_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a wiki page" in {
    delete(s"/wiki_pages/${newWikiPage.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/wiki_pages/${newWikiPage.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
