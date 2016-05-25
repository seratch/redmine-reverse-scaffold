package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class JournalsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.journals, "/*")

  override def afterAll() {
    super.afterAll()
    Journal.deleteAll()
  }

  def newJournal = FactoryGirl(Journal).create()

  it should "show journals" in {
    get("/journals") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/journals/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/journals.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/journals.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a journal in detail" in {
    get(s"/journals/${newJournal.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/journals/${newJournal.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/journals/${newJournal.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/journals/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a journal" in {
    post(s"/journals",
      "journalized_id" -> Int.MaxValue.toString(),
      "journalized_type" -> "dummy",
      "user_id" -> Int.MaxValue.toString(),
      "notes" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "private_notes" -> "true") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/journals",
        "journalized_id" -> Int.MaxValue.toString(),
        "journalized_type" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "notes" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "private_notes" -> "true",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Journal.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/journals/${newJournal.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a journal" in {
    put(s"/journals/${newJournal.id}",
      "journalized_id" -> Int.MaxValue.toString(),
      "journalized_type" -> "dummy",
      "user_id" -> Int.MaxValue.toString(),
      "notes" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "private_notes" -> "true") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/journals/${newJournal.id}",
        "journalized_id" -> Int.MaxValue.toString(),
        "journalized_type" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "notes" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "private_notes" -> "true",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a journal" in {
    delete(s"/journals/${newJournal.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/journals/${newJournal.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
