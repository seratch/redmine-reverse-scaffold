package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WikiRedirectsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.wikiRedirects, "/*")

  override def afterAll() {
    super.afterAll()
    WikiRedirect.deleteAll()
  }

  def newWikiRedirect = FactoryGirl(WikiRedirect).create()

  it should "show wiki redirects" in {
    get("/wiki_redirects") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_redirects/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_redirects.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wiki_redirects.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a wiki redirect in detail" in {
    get(s"/wiki_redirects/${newWikiRedirect.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_redirects/${newWikiRedirect.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wiki_redirects/${newWikiRedirect.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/wiki_redirects/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a wiki redirect" in {
    post(s"/wiki_redirects",
      "wiki_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "redirects_to" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "redirects_to_wiki_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/wiki_redirects",
        "wiki_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "redirects_to" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "redirects_to_wiki_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        WikiRedirect.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/wiki_redirects/${newWikiRedirect.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a wiki redirect" in {
    put(s"/wiki_redirects/${newWikiRedirect.id}",
      "wiki_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "redirects_to" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "redirects_to_wiki_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/wiki_redirects/${newWikiRedirect.id}",
        "wiki_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "redirects_to" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "redirects_to_wiki_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a wiki redirect" in {
    delete(s"/wiki_redirects/${newWikiRedirect.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/wiki_redirects/${newWikiRedirect.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
