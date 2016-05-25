package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class AttachmentsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.attachments, "/*")

  override def afterAll() {
    super.afterAll()
    Attachment.deleteAll()
  }

  def newAttachment = FactoryGirl(Attachment).create()

  it should "show attachments" in {
    get("/attachments") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/attachments/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/attachments.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/attachments.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a attachment in detail" in {
    get(s"/attachments/${newAttachment.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/attachments/${newAttachment.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/attachments/${newAttachment.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/attachments/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a attachment" in {
    post(s"/attachments",
      "container_id" -> Int.MaxValue.toString(),
      "container_type" -> "dummy",
      "filename" -> "dummy",
      "disk_filename" -> "dummy",
      "filesize" -> Long.MaxValue.toString(),
      "content_type" -> "dummy",
      "digest" -> "dummy",
      "downloads" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "description" -> "dummy",
      "disk_directory" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/attachments",
        "container_id" -> Int.MaxValue.toString(),
        "container_type" -> "dummy",
        "filename" -> "dummy",
        "disk_filename" -> "dummy",
        "filesize" -> Long.MaxValue.toString(),
        "content_type" -> "dummy",
        "digest" -> "dummy",
        "downloads" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "description" -> "dummy",
        "disk_directory" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Attachment.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/attachments/${newAttachment.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a attachment" in {
    put(s"/attachments/${newAttachment.id}",
      "container_id" -> Int.MaxValue.toString(),
      "container_type" -> "dummy",
      "filename" -> "dummy",
      "disk_filename" -> "dummy",
      "filesize" -> Long.MaxValue.toString(),
      "content_type" -> "dummy",
      "digest" -> "dummy",
      "downloads" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "description" -> "dummy",
      "disk_directory" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/attachments/${newAttachment.id}",
        "container_id" -> Int.MaxValue.toString(),
        "container_type" -> "dummy",
        "filename" -> "dummy",
        "disk_filename" -> "dummy",
        "filesize" -> Long.MaxValue.toString(),
        "content_type" -> "dummy",
        "digest" -> "dummy",
        "downloads" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "description" -> "dummy",
        "disk_directory" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a attachment" in {
    delete(s"/attachments/${newAttachment.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/attachments/${newAttachment.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
