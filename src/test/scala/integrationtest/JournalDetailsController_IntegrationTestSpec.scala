package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class JournalDetailsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.journalDetails, "/*")

  override def afterAll() {
    super.afterAll()
    JournalDetail.deleteAll()
  }

  def newJournalDetail = FactoryGirl(JournalDetail).create()

  it should "show journal details" in {
    get("/journal_details") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/journal_details/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/journal_details.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/journal_details.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a journal detail in detail" in {
    get(s"/journal_details/${newJournalDetail.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/journal_details/${newJournalDetail.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/journal_details/${newJournalDetail.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/journal_details/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a journal detail" in {
    post(s"/journal_details",
      "journal_id" -> Int.MaxValue.toString(),
      "property" -> "dummy",
      "prop_key" -> "dummy",
      "old_value" -> "dummy",
      "value" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/journal_details",
        "journal_id" -> Int.MaxValue.toString(),
        "property" -> "dummy",
        "prop_key" -> "dummy",
        "old_value" -> "dummy",
        "value" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        JournalDetail.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/journal_details/${newJournalDetail.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a journal detail" in {
    put(s"/journal_details/${newJournalDetail.id}",
      "journal_id" -> Int.MaxValue.toString(),
      "property" -> "dummy",
      "prop_key" -> "dummy",
      "old_value" -> "dummy",
      "value" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/journal_details/${newJournalDetail.id}",
        "journal_id" -> Int.MaxValue.toString(),
        "property" -> "dummy",
        "prop_key" -> "dummy",
        "old_value" -> "dummy",
        "value" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a journal detail" in {
    delete(s"/journal_details/${newJournalDetail.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/journal_details/${newJournalDetail.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
