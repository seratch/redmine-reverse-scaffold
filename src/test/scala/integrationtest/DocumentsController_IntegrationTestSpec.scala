package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class DocumentsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.documents, "/*")

  override def afterAll() {
    super.afterAll()
    Document.deleteAll()
  }

  def newDocument = FactoryGirl(Document).create()

  it should "show documents" in {
    get("/documents") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/documents/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/documents.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/documents.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a document in detail" in {
    get(s"/documents/${newDocument.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/documents/${newDocument.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/documents/${newDocument.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/documents/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a document" in {
    post(s"/documents",
      "project_id" -> Int.MaxValue.toString(),
      "category_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "description" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/documents",
        "project_id" -> Int.MaxValue.toString(),
        "category_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "description" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Document.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/documents/${newDocument.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a document" in {
    put(s"/documents/${newDocument.id}",
      "project_id" -> Int.MaxValue.toString(),
      "category_id" -> Int.MaxValue.toString(),
      "title" -> "dummy",
      "description" -> "dummy",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/documents/${newDocument.id}",
        "project_id" -> Int.MaxValue.toString(),
        "category_id" -> Int.MaxValue.toString(),
        "title" -> "dummy",
        "description" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a document" in {
    delete(s"/documents/${newDocument.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/documents/${newDocument.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
