package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class MemberRolesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    MemberRole.deleteAll()
  }

  def createMockController = new MemberRolesController with MockController
  def newMemberRole = FactoryGirl(MemberRole).create()

  describe("MemberRolesController") {

    describe("shows member roles") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/memberRoles/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/memberRoles/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a member role") {
      it("shows HTML response") {
        val memberRole = newMemberRole
        val controller = createMockController
        controller.showResource(memberRole.id)
        controller.status should equal(200)
        controller.getFromRequestScope[MemberRole]("item") should equal(Some(memberRole))
        controller.renderCall.map(_.path) should equal(Some("/memberRoles/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/memberRoles/new"))
      }
    }

    describe("creates a member role") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "member_id" -> Int.MaxValue.toString(),
          "role_id" -> Int.MaxValue.toString(),
          "inherited_from" -> Int.MaxValue.toString())
        controller.createResource()
        controller.status should equal(200)
      }

      it("fails with invalid parameters") {
        val controller = createMockController
        controller.prepareParams() // no parameters
        controller.createResource()
        controller.status should equal(400)
        controller.errorMessages.size should be >(0)
      }
    }

    it("shows a resource edit input form") {
      val memberRole = newMemberRole
      val controller = createMockController
      controller.editResource(memberRole.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/memberRoles/edit"))
    }

    it("updates a member role") {
      val memberRole = newMemberRole
      val controller = createMockController
      controller.prepareParams(
        "member_id" -> Int.MaxValue.toString(),
        "role_id" -> Int.MaxValue.toString(),
        "inherited_from" -> Int.MaxValue.toString())
      controller.updateResource(memberRole.id)
      controller.status should equal(200)
    }

    it("destroys a member role") {
      val memberRole = newMemberRole
      val controller = createMockController
      controller.destroyResource(memberRole.id)
      controller.status should equal(200)
    }

  }

}
