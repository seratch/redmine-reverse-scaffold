package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WikisController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.wikis, "/*")

  override def afterAll() {
    super.afterAll()
    Wiki.deleteAll()
  }

  def newWiki = FactoryGirl(Wiki).create()

  it should "show wikis" in {
    get("/wikis") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wikis/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wikis.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/wikis.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a wiki in detail" in {
    get(s"/wikis/${newWiki.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wikis/${newWiki.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/wikis/${newWiki.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/wikis/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a wiki" in {
    post(s"/wikis",
      "project_id" -> Int.MaxValue.toString(),
      "start_page" -> "dummy",
      "status" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/wikis",
        "project_id" -> Int.MaxValue.toString(),
        "start_page" -> "dummy",
        "status" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Wiki.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/wikis/${newWiki.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a wiki" in {
    put(s"/wikis/${newWiki.id}",
      "project_id" -> Int.MaxValue.toString(),
      "start_page" -> "dummy",
      "status" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/wikis/${newWiki.id}",
        "project_id" -> Int.MaxValue.toString(),
        "start_page" -> "dummy",
        "status" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a wiki" in {
    delete(s"/wikis/${newWiki.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/wikis/${newWiki.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
