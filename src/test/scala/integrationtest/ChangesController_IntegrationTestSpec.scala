package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class ChangesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.changes, "/*")

  override def afterAll() {
    super.afterAll()
    Change.deleteAll()
  }

  def newChange = FactoryGirl(Change).create()

  it should "show changes" in {
    get("/changes") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/changes/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/changes.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/changes.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a change in detail" in {
    get(s"/changes/${newChange.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/changes/${newChange.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/changes/${newChange.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/changes/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a change" in {
    post(s"/changes",
      "changeset_id" -> Int.MaxValue.toString(),
      "action" -> "dummy",
      "path" -> "dummy",
      "from_path" -> "dummy",
      "from_revision" -> "dummy",
      "revision" -> "dummy",
      "branch" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/changes",
        "changeset_id" -> Int.MaxValue.toString(),
        "action" -> "dummy",
        "path" -> "dummy",
        "from_path" -> "dummy",
        "from_revision" -> "dummy",
        "revision" -> "dummy",
        "branch" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Change.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/changes/${newChange.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a change" in {
    put(s"/changes/${newChange.id}",
      "changeset_id" -> Int.MaxValue.toString(),
      "action" -> "dummy",
      "path" -> "dummy",
      "from_path" -> "dummy",
      "from_revision" -> "dummy",
      "revision" -> "dummy",
      "branch" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/changes/${newChange.id}",
        "changeset_id" -> Int.MaxValue.toString(),
        "action" -> "dummy",
        "path" -> "dummy",
        "from_path" -> "dummy",
        "from_revision" -> "dummy",
        "revision" -> "dummy",
        "branch" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a change" in {
    delete(s"/changes/${newChange.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/changes/${newChange.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
