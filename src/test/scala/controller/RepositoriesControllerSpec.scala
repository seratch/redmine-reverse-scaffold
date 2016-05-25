package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class RepositoriesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Repository.deleteAll()
  }

  def createMockController = new RepositoriesController with MockController
  def newRepository = FactoryGirl(Repository).create()

  describe("RepositoriesController") {

    describe("shows repositories") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/repositories/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/repositories/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a repository") {
      it("shows HTML response") {
        val repository = newRepository
        val controller = createMockController
        controller.showResource(repository.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Repository]("item") should equal(Some(repository))
        controller.renderCall.map(_.path) should equal(Some("/repositories/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/repositories/new"))
      }
    }

    describe("creates a repository") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "url" -> "dummy",
          "login" -> "dummy",
          "password" -> "dummy",
          "root_url" -> "dummy",
          "type" -> "dummy",
          "path_encoding" -> "dummy",
          "log_encoding" -> "dummy",
          "extra_info" -> "dummy",
          "identifier" -> "dummy",
          "is_default" -> "true",
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
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
      val repository = newRepository
      val controller = createMockController
      controller.editResource(repository.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/repositories/edit"))
    }

    it("updates a repository") {
      val repository = newRepository
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "url" -> "dummy",
        "login" -> "dummy",
        "password" -> "dummy",
        "root_url" -> "dummy",
        "type" -> "dummy",
        "path_encoding" -> "dummy",
        "log_encoding" -> "dummy",
        "extra_info" -> "dummy",
        "identifier" -> "dummy",
        "is_default" -> "true",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(repository.id)
      controller.status should equal(200)
    }

    it("destroys a repository") {
      val repository = newRepository
      val controller = createMockController
      controller.destroyResource(repository.id)
      controller.status should equal(200)
    }

  }

}
