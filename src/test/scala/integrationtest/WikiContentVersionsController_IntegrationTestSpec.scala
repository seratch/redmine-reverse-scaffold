package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WikiContentVersionsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.wikiContentVersions, "/*")

  override def afterAll() {
    super.afterAll()
    WikiContentVersion.deleteAll()
  }

  def newWikiContentVersion = FactoryGirl(WikiContentVersion).create()

  it should "show wiki content versions" in {
    get("/wiki_content_versions") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_content_versions/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_content_versions.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_content_versions.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a wiki content version in detail" in {
    get(s"/wiki_content_versions/${newWikiContentVersion.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_content_versions/${newWikiContentVersion.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_content_versions/${newWikiContentVersion.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/wiki_content_versions/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a wiki content version" in {
    post(s"/wiki_content_versions",
      "wiki_content_id" -> Int.MaxValue.toString(),
      "page_id" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "data" -> "dummy",
      "compression" -> "dummy",
      "comments" -> "dummy",
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/wiki_content_versions",
        "wiki_content_id" -> Int.MaxValue.toString(),
        "page_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "data" -> "dummy",
        "compression" -> "dummy",
        "comments" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        WikiContentVersion.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/wiki_content_versions/${newWikiContentVersion.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a wiki content version" in {
    put(s"/wiki_content_versions/${newWikiContentVersion.id}",
      "wiki_content_id" -> Int.MaxValue.toString(),
      "page_id" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "data" -> "dummy",
      "compression" -> "dummy",
      "comments" -> "dummy",
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "version" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/wiki_content_versions/${newWikiContentVersion.id}",
        "wiki_content_id" -> Int.MaxValue.toString(),
        "page_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "data" -> "dummy",
        "compression" -> "dummy",
        "comments" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "version" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a wiki content version" in {
    delete(s"/wiki_content_versions/${newWikiContentVersion.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/wiki_content_versions/${newWikiContentVersion.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
