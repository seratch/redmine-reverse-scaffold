package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class ProjectsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Project.deleteAll()
  }

  def createMockController = new ProjectsController with MockController
  def newProject = FactoryGirl(Project).create()

  describe("ProjectsController") {

    describe("shows projects") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/projects/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/projects/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a project") {
      it("shows HTML response") {
        val project = newProject
        val controller = createMockController
        controller.showResource(project.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Project]("item") should equal(Some(project))
        controller.renderCall.map(_.path) should equal(Some("/projects/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/projects/new"))
      }
    }

    describe("creates a project") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "name" -> "dummy",
          "description" -> "dummy",
          "homepage" -> "dummy",
          "is_public" -> "true",
          "parent_id" -> Int.MaxValue.toString(),
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "identifier" -> "dummy",
          "status" -> Int.MaxValue.toString(),
          "lft" -> Int.MaxValue.toString(),
          "rgt" -> Int.MaxValue.toString(),
          "inherit_members" -> "true",
          "default_version_id" -> Int.MaxValue.toString())
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
      val project = newProject
      val controller = createMockController
      controller.editResource(project.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/projects/edit"))
    }

    it("updates a project") {
      val project = newProject
      val controller = createMockController
      controller.prepareParams(
        "name" -> "dummy",
        "description" -> "dummy",
        "homepage" -> "dummy",
        "is_public" -> "true",
        "parent_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "identifier" -> "dummy",
        "status" -> Int.MaxValue.toString(),
        "lft" -> Int.MaxValue.toString(),
        "rgt" -> Int.MaxValue.toString(),
        "inherit_members" -> "true",
        "default_version_id" -> Int.MaxValue.toString())
      controller.updateResource(project.id)
      controller.status should equal(200)
    }

    it("destroys a project") {
      val project = newProject
      val controller = createMockController
      controller.destroyResource(project.id)
      controller.status should equal(200)
    }

  }

}
