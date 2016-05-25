package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class EnumerationsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.enumerations, "/*")

  override def afterAll() {
    super.afterAll()
    Enumeration.deleteAll()
  }

  def newEnumeration = FactoryGirl(Enumeration).create()

  it should "show enumerations" in {
    get("/enumerations") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/enumerations/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/enumerations.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/enumerations.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a enumeration in detail" in {
    get(s"/enumerations/${newEnumeration.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/enumerations/${newEnumeration.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/enumerations/${newEnumeration.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/enumerations/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a enumeration" in {
    post(s"/enumerations",
      "name" -> "dummy",
      "position" -> Int.MaxValue.toString(),
      "is_default" -> "true",
      "type" -> "dummy",
      "active" -> "true",
      "project_id" -> Int.MaxValue.toString(),
      "parent_id" -> Int.MaxValue.toString(),
      "position_name" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/enumerations",
        "name" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "is_default" -> "true",
        "type" -> "dummy",
        "active" -> "true",
        "project_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "position_name" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Enumeration.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/enumerations/${newEnumeration.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a enumeration" in {
    put(s"/enumerations/${newEnumeration.id}",
      "name" -> "dummy",
      "position" -> Int.MaxValue.toString(),
      "is_default" -> "true",
      "type" -> "dummy",
      "active" -> "true",
      "project_id" -> Int.MaxValue.toString(),
      "parent_id" -> Int.MaxValue.toString(),
      "position_name" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/enumerations/${newEnumeration.id}",
        "name" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "is_default" -> "true",
        "type" -> "dummy",
        "active" -> "true",
        "project_id" -> Int.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "position_name" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a enumeration" in {
    delete(s"/enumerations/${newEnumeration.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/enumerations/${newEnumeration.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
