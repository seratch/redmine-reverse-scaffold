package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class AuthSourcesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.authSources, "/*")

  override def afterAll() {
    super.afterAll()
    AuthSource.deleteAll()
  }

  def newAuthSource = FactoryGirl(AuthSource).create()

  it should "show auth sources" in {
    get("/auth_sources") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/auth_sources/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/auth_sources.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/auth_sources.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a auth source in detail" in {
    get(s"/auth_sources/${newAuthSource.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/auth_sources/${newAuthSource.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/auth_sources/${newAuthSource.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/auth_sources/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a auth source" in {
    post(s"/auth_sources",
      "type" -> "dummy",
      "name" -> "dummy",
      "host" -> "dummy",
      "port" -> Int.MaxValue.toString(),
      "account" -> "dummy",
      "account_password" -> "dummy",
      "base_dn" -> "dummy",
      "attr_login" -> "dummy",
      "attr_firstname" -> "dummy",
      "attr_lastname" -> "dummy",
      "attr_mail" -> "dummy",
      "onthefly_register" -> "true",
      "tls" -> "true",
      "filter" -> "dummy",
      "timeout" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/auth_sources",
        "type" -> "dummy",
        "name" -> "dummy",
        "host" -> "dummy",
        "port" -> Int.MaxValue.toString(),
        "account" -> "dummy",
        "account_password" -> "dummy",
        "base_dn" -> "dummy",
        "attr_login" -> "dummy",
        "attr_firstname" -> "dummy",
        "attr_lastname" -> "dummy",
        "attr_mail" -> "dummy",
        "onthefly_register" -> "true",
        "tls" -> "true",
        "filter" -> "dummy",
        "timeout" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        AuthSource.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/auth_sources/${newAuthSource.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a auth source" in {
    put(s"/auth_sources/${newAuthSource.id}",
      "type" -> "dummy",
      "name" -> "dummy",
      "host" -> "dummy",
      "port" -> Int.MaxValue.toString(),
      "account" -> "dummy",
      "account_password" -> "dummy",
      "base_dn" -> "dummy",
      "attr_login" -> "dummy",
      "attr_firstname" -> "dummy",
      "attr_lastname" -> "dummy",
      "attr_mail" -> "dummy",
      "onthefly_register" -> "true",
      "tls" -> "true",
      "filter" -> "dummy",
      "timeout" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/auth_sources/${newAuthSource.id}",
        "type" -> "dummy",
        "name" -> "dummy",
        "host" -> "dummy",
        "port" -> Int.MaxValue.toString(),
        "account" -> "dummy",
        "account_password" -> "dummy",
        "base_dn" -> "dummy",
        "attr_login" -> "dummy",
        "attr_firstname" -> "dummy",
        "attr_lastname" -> "dummy",
        "attr_mail" -> "dummy",
        "onthefly_register" -> "true",
        "tls" -> "true",
        "filter" -> "dummy",
        "timeout" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a auth source" in {
    delete(s"/auth_sources/${newAuthSource.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/auth_sources/${newAuthSource.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
