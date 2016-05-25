package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class UserPreferencesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.userPreferences, "/*")

  override def afterAll() {
    super.afterAll()
    UserPreference.deleteAll()
  }

  def newUserPreference = FactoryGirl(UserPreference).create()

  it should "show user preferences" in {
    get("/user_preferences") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/user_preferences/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/user_preferences.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/user_preferences.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a user preference in detail" in {
    get(s"/user_preferences/${newUserPreference.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/user_preferences/${newUserPreference.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/user_preferences/${newUserPreference.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/user_preferences/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a user preference" in {
    post(s"/user_preferences",
      "user_id" -> Int.MaxValue.toString(),
      "others" -> "dummy",
      "hide_mail" -> "true",
      "time_zone" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/user_preferences",
        "user_id" -> Int.MaxValue.toString(),
        "others" -> "dummy",
        "hide_mail" -> "true",
        "time_zone" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        UserPreference.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/user_preferences/${newUserPreference.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a user preference" in {
    put(s"/user_preferences/${newUserPreference.id}",
      "user_id" -> Int.MaxValue.toString(),
      "others" -> "dummy",
      "hide_mail" -> "true",
      "time_zone" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/user_preferences/${newUserPreference.id}",
        "user_id" -> Int.MaxValue.toString(),
        "others" -> "dummy",
        "hide_mail" -> "true",
        "time_zone" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a user preference" in {
    delete(s"/user_preferences/${newUserPreference.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/user_preferences/${newUserPreference.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
