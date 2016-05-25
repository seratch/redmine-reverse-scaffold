package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class VersionsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.versions, "/*")

  override def afterAll() {
    super.afterAll()
    Version.deleteAll()
  }

  def newVersion = FactoryGirl(Version).create()

  it should "show versions" in {
    get("/versions") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/versions/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/versions.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/versions.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a version in detail" in {
    get(s"/versions/${newVersion.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/versions/${newVersion.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/versions/${newVersion.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/versions/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a version" in {
    post(s"/versions",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "description" -> "dummy",
      "effective_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "wiki_page_title" -> "dummy",
      "status" -> "dummy",
      "sharing" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/versions",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "description" -> "dummy",
        "effective_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "wiki_page_title" -> "dummy",
        "status" -> "dummy",
        "sharing" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Version.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/versions/${newVersion.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a version" in {
    put(s"/versions/${newVersion.id}",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "description" -> "dummy",
      "effective_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "wiki_page_title" -> "dummy",
      "status" -> "dummy",
      "sharing" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/versions/${newVersion.id}",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "description" -> "dummy",
        "effective_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "wiki_page_title" -> "dummy",
        "status" -> "dummy",
        "sharing" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a version" in {
    delete(s"/versions/${newVersion.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/versions/${newVersion.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
