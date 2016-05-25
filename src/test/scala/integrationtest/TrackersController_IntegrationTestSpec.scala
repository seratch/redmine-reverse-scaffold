package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class TrackersController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.trackers, "/*")

  override def afterAll() {
    super.afterAll()
    Tracker.deleteAll()
  }

  def newTracker = FactoryGirl(Tracker).create()

  it should "show trackers" in {
    get("/trackers") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/trackers/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/trackers.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/trackers.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a tracker in detail" in {
    get(s"/trackers/${newTracker.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/trackers/${newTracker.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/trackers/${newTracker.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/trackers/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a tracker" in {
    post(s"/trackers",
      "name" -> "dummy",
      "is_in_chlog" -> "true",
      "position" -> Int.MaxValue.toString(),
      "is_in_roadmap" -> "true",
      "fields_bits" -> Int.MaxValue.toString(),
      "default_status_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/trackers",
        "name" -> "dummy",
        "is_in_chlog" -> "true",
        "position" -> Int.MaxValue.toString(),
        "is_in_roadmap" -> "true",
        "fields_bits" -> Int.MaxValue.toString(),
        "default_status_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Tracker.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/trackers/${newTracker.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a tracker" in {
    put(s"/trackers/${newTracker.id}",
      "name" -> "dummy",
      "is_in_chlog" -> "true",
      "position" -> Int.MaxValue.toString(),
      "is_in_roadmap" -> "true",
      "fields_bits" -> Int.MaxValue.toString(),
      "default_status_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/trackers/${newTracker.id}",
        "name" -> "dummy",
        "is_in_chlog" -> "true",
        "position" -> Int.MaxValue.toString(),
        "is_in_roadmap" -> "true",
        "fields_bits" -> Int.MaxValue.toString(),
        "default_status_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a tracker" in {
    delete(s"/trackers/${newTracker.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/trackers/${newTracker.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
