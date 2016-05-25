package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class EmailAddressesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.emailAddresses, "/*")

  override def afterAll() {
    super.afterAll()
    EmailAddress.deleteAll()
  }

  def newEmailAddress = FactoryGirl(EmailAddress).create()

  it should "show email addresses" in {
    get("/email_addresses") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/email_addresses/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/email_addresses.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/email_addresses.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a email address in detail" in {
    get(s"/email_addresses/${newEmailAddress.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/email_addresses/${newEmailAddress.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/email_addresses/${newEmailAddress.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/email_addresses/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a email address" in {
    post(s"/email_addresses",
      "user_id" -> Int.MaxValue.toString(),
      "address" -> "dummy",
      "is_default" -> "true",
      "notify" -> "true",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/email_addresses",
        "user_id" -> Int.MaxValue.toString(),
        "address" -> "dummy",
        "is_default" -> "true",
        "notify" -> "true",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        EmailAddress.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/email_addresses/${newEmailAddress.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a email address" in {
    put(s"/email_addresses/${newEmailAddress.id}",
      "user_id" -> Int.MaxValue.toString(),
      "address" -> "dummy",
      "is_default" -> "true",
      "notify" -> "true",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/email_addresses/${newEmailAddress.id}",
        "user_id" -> Int.MaxValue.toString(),
        "address" -> "dummy",
        "is_default" -> "true",
        "notify" -> "true",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a email address" in {
    delete(s"/email_addresses/${newEmailAddress.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/email_addresses/${newEmailAddress.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
