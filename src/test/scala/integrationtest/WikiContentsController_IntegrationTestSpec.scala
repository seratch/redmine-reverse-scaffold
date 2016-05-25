package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WikiContentsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.wikiContents, "/*")

  override def afterAll() {
    super.afterAll()
    WikiContent.deleteAll()
  }

  def newWikiContent = FactoryGirl(WikiContent).create()

  it should "show wiki contents" in {
    get("/wiki_contents") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_contents/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_contents.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_contents.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a wiki content in detail" in {
    get(s"/wiki_contents/${newWikiContent.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_contents/${newWikiContent.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_contents/${newWikiContent.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/wiki_contents/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a wiki content" in {
    post(s"/wiki_contents",
      "page_id" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "text" -> "dummy",
      "comments" -> "dummy",
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/wiki_contents",
        "page_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "text" -> "dummy",
        "comments" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        WikiContent.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/wiki_contents/${newWikiContent.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a wiki content" in {
    put(s"/wiki_contents/${newWikiContent.id}",
      "page_id" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "text" -> "dummy",
      "comments" -> "dummy",
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/wiki_contents/${newWikiContent.id}",
        "page_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "text" -> "dummy",
        "comments" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a wiki content" in {
    delete(s"/wiki_contents/${newWikiContent.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/wiki_contents/${newWikiContent.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
