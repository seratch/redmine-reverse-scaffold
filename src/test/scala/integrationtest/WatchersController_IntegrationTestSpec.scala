package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WatchersController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.watchers, "/*")

  override def afterAll() {
    super.afterAll()
    Watcher.deleteAll()
  }

  def newWatcher = FactoryGirl(Watcher).create()

  it should "show watchers" in {
    get("/watchers") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/watchers/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/watchers.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/watchers.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a watcher in detail" in {
    get(s"/watchers/${newWatcher.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/watchers/${newWatcher.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/watchers/${newWatcher.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/watchers/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a watcher" in {
    post(s"/watchers",
      "watchable_type" -> "dummy",
      "watchable_id" -> Int.MaxValue.toString(),
      "user_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/watchers",
        "watchable_type" -> "dummy",
        "watchable_id" -> Int.MaxValue.toString(),
        "user_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Watcher.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/watchers/${newWatcher.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a watcher" in {
    put(s"/watchers/${newWatcher.id}",
      "watchable_type" -> "dummy",
      "watchable_id" -> Int.MaxValue.toString(),
      "user_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/watchers/${newWatcher.id}",
        "watchable_type" -> "dummy",
        "watchable_id" -> Int.MaxValue.toString(),
        "user_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a watcher" in {
    delete(s"/watchers/${newWatcher.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/watchers/${newWatcher.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
