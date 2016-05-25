package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class RolesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Role.deleteAll()
  }

  def createMockController = new RolesController with MockController
  def newRole = FactoryGirl(Role).create()

  describe("RolesController") {

    describe("shows roles") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/roles/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/roles/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a role") {
      it("shows HTML response") {
        val role = newRole
        val controller = createMockController
        controller.showResource(role.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Role]("item") should equal(Some(role))
        controller.renderCall.map(_.path) should equal(Some("/roles/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/roles/new"))
      }
    }

    describe("creates a role") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "position" -> Int.MaxValue.toString(),
          "assignable" -> "true",
          "builtin" -> Int.MaxValue.toString(),
          "permissions" -> "dummy",
          "issues_visibility" -> "dummy",
          "users_visibility" -> "dummy",
          "time_entries_visibility" -> "dummy",
          "all_roles_managed" -> "true")
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
      val role = newRole
      val controller = createMockController
      controller.editResource(role.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/roles/edit"))
    }

    it("updates a role") {
      val role = newRole
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "position" -> Int.MaxValue.toString(),
        "assignable" -> "true",
        "builtin" -> Int.MaxValue.toString(),
        "permissions" -> "dummy",
        "issues_visibility" -> "dummy",
        "users_visibility" -> "dummy",
        "time_entries_visibility" -> "dummy",
        "all_roles_managed" -> "true")
      controller.updateResource(role.id)
      controller.status should equal(200)
    }

    it("destroys a role") {
      val role = newRole
      val controller = createMockController
      controller.destroyResource(role.id)
      controller.status should equal(200)
    }

  }

}
