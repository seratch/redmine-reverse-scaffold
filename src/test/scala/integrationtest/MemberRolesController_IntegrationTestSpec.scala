package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class MemberRolesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.memberRoles, "/*")

  override def afterAll() {
    super.afterAll()
    MemberRole.deleteAll()
  }

  def newMemberRole = FactoryGirl(MemberRole).create()

  it should "show member roles" in {
    get("/member_roles") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/member_roles/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/member_roles.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/member_roles.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a member role in detail" in {
    get(s"/member_roles/${newMemberRole.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/member_roles/${newMemberRole.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/member_roles/${newMemberRole.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/member_roles/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a member role" in {
    post(s"/member_roles",
      "member_id" -> Int.MaxValue.toString(),
      "role_id" -> Int.MaxValue.toString(),
      "inherited_from" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/member_roles",
        "member_id" -> Int.MaxValue.toString(),
        "role_id" -> Int.MaxValue.toString(),
        "inherited_from" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        MemberRole.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/member_roles/${newMemberRole.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a member role" in {
    put(s"/member_roles/${newMemberRole.id}",
      "member_id" -> Int.MaxValue.toString(),
      "role_id" -> Int.MaxValue.toString(),
      "inherited_from" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/member_roles/${newMemberRole.id}",
        "member_id" -> Int.MaxValue.toString(),
        "role_id" -> Int.MaxValue.toString(),
        "inherited_from" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a member role" in {
    delete(s"/member_roles/${newMemberRole.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/member_roles/${newMemberRole.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
