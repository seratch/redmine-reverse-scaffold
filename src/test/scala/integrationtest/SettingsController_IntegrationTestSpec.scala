package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class SettingsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.settings, "/*")

  override def afterAll() {
    super.afterAll()
    Setting.deleteAll()
  }

  def newSetting = FactoryGirl(Setting).create()

  it should "show settings" in {
    get("/settings") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/settings/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/settings.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/settings.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a setting in detail" in {
    get(s"/settings/${newSetting.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/settings/${newSetting.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/settings/${newSetting.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/settings/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a setting" in {
    post(s"/settings",
      "name" -> "dummy",
      "value" -> "dummy",
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/settings",
        "name" -> "dummy",
        "value" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Setting.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/settings/${newSetting.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a setting" in {
    put(s"/settings/${newSetting.id}",
      "name" -> "dummy",
      "value" -> "dummy",
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/settings/${newSetting.id}",
        "name" -> "dummy",
        "value" -> "dummy",
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a setting" in {
    delete(s"/settings/${newSetting.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/settings/${newSetting.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
