package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class ImportItemsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.importItems, "/*")

  override def afterAll() {
    super.afterAll()
    ImportItem.deleteAll()
  }

  def newImportItem = FactoryGirl(ImportItem).create()

  it should "show import items" in {
    get("/import_items") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/import_items/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/import_items.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/import_items.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a import item in detail" in {
    get(s"/import_items/${newImportItem.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/import_items/${newImportItem.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/import_items/${newImportItem.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/import_items/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a import item" in {
    post(s"/import_items",
      "import_id" -> Int.MaxValue.toString(),
      "position" -> Int.MaxValue.toString(),
      "obj_id" -> Int.MaxValue.toString(),
      "message" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/import_items",
        "import_id" -> Int.MaxValue.toString(),
        "position" -> Int.MaxValue.toString(),
        "obj_id" -> Int.MaxValue.toString(),
        "message" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        ImportItem.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/import_items/${newImportItem.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a import item" in {
    put(s"/import_items/${newImportItem.id}",
      "import_id" -> Int.MaxValue.toString(),
      "position" -> Int.MaxValue.toString(),
      "obj_id" -> Int.MaxValue.toString(),
      "message" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/import_items/${newImportItem.id}",
        "import_id" -> Int.MaxValue.toString(),
        "position" -> Int.MaxValue.toString(),
        "obj_id" -> Int.MaxValue.toString(),
        "message" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a import item" in {
    delete(s"/import_items/${newImportItem.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/import_items/${newImportItem.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
