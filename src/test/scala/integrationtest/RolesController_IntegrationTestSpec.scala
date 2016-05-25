package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class RolesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.roles, "/*")

  override def afterAll() {
    super.afterAll()
    Role.deleteAll()
  }

  def newRole = FactoryGirl(Role).create()

  it should "show roles" in {
    get("/roles") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/roles/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/roles.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/roles.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a role in detail" in {
    get(s"/roles/${newRole.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/roles/${newRole.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/roles/${newRole.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/roles/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a role" in {
    post(s"/roles",
      "name" -> "dummy",
      "position" -> Int.MaxValue.toString(),
      "assignable" -> "true",
      "builtin" -> Int.MaxValue.toString(),
      "permissions" -> "dummy",
      "issues_visibility" -> "dummy",
      "users_visibility" -> "dummy",
      "time_entries_visibility" -> "dummy",
      "all_roles_managed" -> "true") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/roles",
        "name" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "assignable" -> "true",
        "builtin" -> Int.MaxValue.toString(),
        "permissions" -> "dummy",
        "issues_visibility" -> "dummy",
        "users_visibility" -> "dummy",
        "time_entries_visibility" -> "dummy",
        "all_roles_managed" -> "true",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Role.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/roles/${newRole.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a role" in {
    put(s"/roles/${newRole.id}",
      "name" -> "dummy",
      "position" -> Int.MaxValue.toString(),
      "assignable" -> "true",
      "builtin" -> Int.MaxValue.toString(),
      "permissions" -> "dummy",
      "issues_visibility" -> "dummy",
      "users_visibility" -> "dummy",
      "time_entries_visibility" -> "dummy",
      "all_roles_managed" -> "true") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/roles/${newRole.id}",
        "name" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "assignable" -> "true",
        "builtin" -> Int.MaxValue.toString(),
        "permissions" -> "dummy",
        "issues_visibility" -> "dummy",
        "users_visibility" -> "dummy",
        "time_entries_visibility" -> "dummy",
        "all_roles_managed" -> "true",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a role" in {
    delete(s"/roles/${newRole.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/roles/${newRole.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
