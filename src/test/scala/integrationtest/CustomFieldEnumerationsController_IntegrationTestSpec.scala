package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class CustomFieldEnumerationsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.customFieldEnumerations, "/*")

  override def afterAll() {
    super.afterAll()
    CustomFieldEnumeration.deleteAll()
  }

  def newCustomFieldEnumeration = FactoryGirl(CustomFieldEnumeration).create()

  it should "show custom field enumerations" in {
    get("/custom_field_enumerations") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_field_enumerations/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_field_enumerations.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_field_enumerations.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a custom field enumeration in detail" in {
    get(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/custom_field_enumerations/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a custom field enumeration" in {
    post(s"/custom_field_enumerations",
      "custom_field_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "active" -> "true",
      "position" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/custom_field_enumerations",
        "custom_field_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "active" -> "true",
        "position" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        CustomFieldEnumeration.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a custom field enumeration" in {
    put(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}",
      "custom_field_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "active" -> "true",
      "position" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}",
        "custom_field_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "active" -> "true",
        "position" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a custom field enumeration" in {
    delete(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/custom_field_enumerations/${newCustomFieldEnumeration.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
