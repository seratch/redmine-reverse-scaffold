package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class OpenIdAuthenticationAssociationsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.openIdAuthenticationAssociations, "/*")

  override def afterAll() {
    super.afterAll()
    OpenIdAuthenticationAssociation.deleteAll()
  }

  def newOpenIdAuthenticationAssociation = FactoryGirl(OpenIdAuthenticationAssociation).create()

  it should "show open id authentication associations" in {
    get("/open_id_authentication_associations") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/open_id_authentication_associations/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/open_id_authentication_associations.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/open_id_authentication_associations.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a open id authentication association in detail" in {
    get(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/open_id_authentication_associations/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a open id authentication association" in {
    post(s"/open_id_authentication_associations",
      "issued" -> Int.MaxValue.toString(),
      "lifetime" -> Int.MaxValue.toString(),
      "handle" -> "dummy",
      "assoc_type" -> "dummy",
      "server_url" -> "dummy",
      "secret" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/open_id_authentication_associations",
        "issued" -> Int.MaxValue.toString(),
        "lifetime" -> Int.MaxValue.toString(),
        "handle" -> "dummy",
        "assoc_type" -> "dummy",
        "server_url" -> "dummy",
        "secret" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        OpenIdAuthenticationAssociation.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a open id authentication association" in {
    put(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}",
      "issued" -> Int.MaxValue.toString(),
      "lifetime" -> Int.MaxValue.toString(),
      "handle" -> "dummy",
      "assoc_type" -> "dummy",
      "server_url" -> "dummy",
      "secret" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}",
        "issued" -> Int.MaxValue.toString(),
        "lifetime" -> Int.MaxValue.toString(),
        "handle" -> "dummy",
        "assoc_type" -> "dummy",
        "server_url" -> "dummy",
        "secret" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a open id authentication association" in {
    delete(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/open_id_authentication_associations/${newOpenIdAuthenticationAssociation.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
