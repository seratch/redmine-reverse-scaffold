package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class CustomValuesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.customValues, "/*")

  override def afterAll() {
    super.afterAll()
    CustomValue.deleteAll()
  }

  def newCustomValue = FactoryGirl(CustomValue).create()

  it should "show custom values" in {
    get("/custom_values") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_values/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_values.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/custom_values.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a custom value in detail" in {
    get(s"/custom_values/${newCustomValue.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/custom_values/${newCustomValue.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/custom_values/${newCustomValue.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/custom_values/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a custom value" in {
    post(s"/custom_values",
      "customized_type" -> "dummy",
      "customized_id" -> Int.MaxValue.toString(),
      "custom_field_id" -> Int.MaxValue.toString(),
      "value" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/custom_values",
        "customized_type" -> "dummy",
        "customized_id" -> Int.MaxValue.toString(),
        "custom_field_id" -> Int.MaxValue.toString(),
        "value" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        CustomValue.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/custom_values/${newCustomValue.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a custom value" in {
    put(s"/custom_values/${newCustomValue.id}",
      "customized_type" -> "dummy",
      "customized_id" -> Int.MaxValue.toString(),
      "custom_field_id" -> Int.MaxValue.toString(),
      "value" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/custom_values/${newCustomValue.id}",
        "customized_type" -> "dummy",
        "customized_id" -> Int.MaxValue.toString(),
        "custom_field_id" -> Int.MaxValue.toString(),
        "value" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a custom value" in {
    delete(s"/custom_values/${newCustomValue.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/custom_values/${newCustomValue.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
