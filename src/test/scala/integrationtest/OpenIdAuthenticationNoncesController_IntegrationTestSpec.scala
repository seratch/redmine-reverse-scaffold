package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class OpenIdAuthenticationNoncesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.openIdAuthenticationNonces, "/*")

  override def afterAll() {
    super.afterAll()
    OpenIdAuthenticationNonce.deleteAll()
  }

  def newOpenIdAuthenticationNonce = FactoryGirl(OpenIdAuthenticationNonce).create()

  it should "show open id authentication nonces" in {
    get("/open_id_authentication_nonces") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/open_id_authentication_nonces/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/open_id_authentication_nonces.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/open_id_authentication_nonces.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a open id authentication nonce in detail" in {
    get(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/open_id_authentication_nonces/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a open id authentication nonce" in {
    post(s"/open_id_authentication_nonces",
      "timestamp" -> Int.MaxValue.toString(),
      "server_url" -> "dummy",
      "salt" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/open_id_authentication_nonces",
        "timestamp" -> Int.MaxValue.toString(),
        "server_url" -> "dummy",
        "salt" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        OpenIdAuthenticationNonce.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a open id authentication nonce" in {
    put(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}",
      "timestamp" -> Int.MaxValue.toString(),
      "server_url" -> "dummy",
      "salt" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}",
        "timestamp" -> Int.MaxValue.toString(),
        "server_url" -> "dummy",
        "salt" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a open id authentication nonce" in {
    delete(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/open_id_authentication_nonces/${newOpenIdAuthenticationNonce.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
