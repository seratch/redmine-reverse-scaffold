package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class CustomFieldsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.customFields, "/*")

  override def afterAll() {
    super.afterAll()
    CustomField.deleteAll()
  }

  def newCustomField = FactoryGirl(CustomField).create()

  it should "show custom fields" in {
    get("/custom_fields") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_fields/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_fields.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_fields.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a custom field in detail" in {
    get(s"/custom_fields/${newCustomField.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/custom_fields/${newCustomField.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/custom_fields/${newCustomField.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/custom_fields/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a custom field" in {
    post(s"/custom_fields",
      "type" -> "dummy",
      "name" -> "dummy",
      "field_format" -> "dummy",
      "possible_values" -> "dummy",
      "regexp" -> "dummy",
      "min_length" -> Int.MaxValue.toString(),
      "max_length" -> Int.MaxValue.toString(),
      "is_required" -> "true",
      "is_for_all" -> "true",
      "is_filter" -> "true",
      "position" -> Int.MaxValue.toString(),
      "searchable" -> "true",
      "default_value" -> "dummy",
      "editable" -> "true",
      "visible" -> "true",
      "multiple" -> "true",
      "format_store" -> "dummy",
      "description" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/custom_fields",
        "type" -> "dummy",
        "name" -> "dummy",
        "field_format" -> "dummy",
        "possible_values" -> "dummy",
        "regexp" -> "dummy",
        "min_length" -> Int.MaxValue.toString(),
        "max_length" -> Int.MaxValue.toString(),
        "is_required" -> "true",
        "is_for_all" -> "true",
        "is_filter" -> "true",
        "position" -> Int.MaxValue.toString(),
        "searchable" -> "true",
        "default_value" -> "dummy",
        "editable" -> "true",
        "visible" -> "true",
        "multiple" -> "true",
        "format_store" -> "dummy",
        "description" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        CustomField.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/custom_fields/${newCustomField.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a custom field" in {
    put(s"/custom_fields/${newCustomField.id}",
      "type" -> "dummy",
      "name" -> "dummy",
      "field_format" -> "dummy",
      "possible_values" -> "dummy",
      "regexp" -> "dummy",
      "min_length" -> Int.MaxValue.toString(),
      "max_length" -> Int.MaxValue.toString(),
      "is_required" -> "true",
      "is_for_all" -> "true",
      "is_filter" -> "true",
      "position" -> Int.MaxValue.toString(),
      "searchable" -> "true",
      "default_value" -> "dummy",
      "editable" -> "true",
      "visible" -> "true",
      "multiple" -> "true",
      "format_store" -> "dummy",
      "description" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/custom_fields/${newCustomField.id}",
        "type" -> "dummy",
        "name" -> "dummy",
        "field_format" -> "dummy",
        "possible_values" -> "dummy",
        "regexp" -> "dummy",
        "min_length" -> Int.MaxValue.toString(),
        "max_length" -> Int.MaxValue.toString(),
        "is_required" -> "true",
        "is_for_all" -> "true",
        "is_filter" -> "true",
        "position" -> Int.MaxValue.toString(),
        "searchable" -> "true",
        "default_value" -> "dummy",
        "editable" -> "true",
        "visible" -> "true",
        "multiple" -> "true",
        "format_store" -> "dummy",
        "description" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a custom field" in {
    delete(s"/custom_fields/${newCustomField.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/custom_fields/${newCustomField.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
