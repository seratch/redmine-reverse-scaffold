package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class ImportsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.imports, "/*")

  override def afterAll() {
    super.afterAll()
    Import.deleteAll()
  }

  def newImport = FactoryGirl(Import).create()

  it should "show imports" in {
    get("/imports") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/imports/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/imports.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/imports.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a import in detail" in {
    get(s"/imports/${newImport.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/imports/${newImport.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/imports/${newImport.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/imports/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a import" in {
    post(s"/imports",
      "type" -> "dummy",
      "user_id" -> Int.MaxValue.toString(),
      "filename" -> "dummy",
      "settings" -> "dummy",
      "total_items" -> Int.MaxValue.toString(),
      "finished" -> "true",
      "created_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_at" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/imports",
        "type" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "filename" -> "dummy",
        "settings" -> "dummy",
        "total_items" -> Int.MaxValue.toString(),
        "finished" -> "true",
        "created_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Import.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/imports/${newImport.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a import" in {
    put(s"/imports/${newImport.id}",
      "type" -> "dummy",
      "user_id" -> Int.MaxValue.toString(),
      "filename" -> "dummy",
      "settings" -> "dummy",
      "total_items" -> Int.MaxValue.toString(),
      "finished" -> "true",
      "created_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_at" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/imports/${newImport.id}",
        "type" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "filename" -> "dummy",
        "settings" -> "dummy",
        "total_items" -> Int.MaxValue.toString(),
        "finished" -> "true",
        "created_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_at" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a import" in {
    delete(s"/imports/${newImport.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/imports/${newImport.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
