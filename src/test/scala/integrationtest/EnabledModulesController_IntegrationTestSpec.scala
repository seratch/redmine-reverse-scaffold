package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class EnabledModulesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.enabledModules, "/*")

  override def afterAll() {
    super.afterAll()
    EnabledModule.deleteAll()
  }

  def newEnabledModule = FactoryGirl(EnabledModule).create()

  it should "show enabled modules" in {
    get("/enabled_modules") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/enabled_modules/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/enabled_modules.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/enabled_modules.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a enabled module in detail" in {
    get(s"/enabled_modules/${newEnabledModule.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/enabled_modules/${newEnabledModule.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/enabled_modules/${newEnabledModule.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/enabled_modules/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a enabled module" in {
    post(s"/enabled_modules",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/enabled_modules",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        EnabledModule.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/enabled_modules/${newEnabledModule.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a enabled module" in {
    put(s"/enabled_modules/${newEnabledModule.id}",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/enabled_modules/${newEnabledModule.id}",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a enabled module" in {
    delete(s"/enabled_modules/${newEnabledModule.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/enabled_modules/${newEnabledModule.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
