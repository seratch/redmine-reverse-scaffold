package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class QueriesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Query.deleteAll()
  }

  def createMockController = new QueriesController with MockController
  def newQuery = FactoryGirl(Query).create()

  describe("QueriesController") {

    describe("shows queries") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/queries/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/queries/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a query") {
      it("shows HTML response") {
        val query = newQuery
        val controller = createMockController
        controller.showResource(query.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Query]("item") should equal(Some(query))
        controller.renderCall.map(_.path) should equal(Some("/queries/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/queries/new"))
      }
    }

    describe("creates a query") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "project_id" -> Int.MaxValue.toString(),
          "name" -> "dummy",
          "filters" -> "dummy",
          "user_id" -> Int.MaxValue.toString(),
          "column_names" -> "dummy",
          "sort_criteria" -> "dummy",
          "group_by" -> "dummy",
          "type" -> "dummy",
          "visibility" -> Int.MaxValue.toString(),
          "options" -> "dummy")
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
      val query = newQuery
      val controller = createMockController
      controller.editResource(query.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/queries/edit"))
    }

    it("updates a query") {
      val query = newQuery
      val controller = createMockController
      controller.prepareParams(
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "filters" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "column_names" -> "dummy",
        "sort_criteria" -> "dummy",
        "group_by" -> "dummy",
        "type" -> "dummy",
        "visibility" -> Int.MaxValue.toString(),
        "options" -> "dummy")
      controller.updateResource(query.id)
      controller.status should equal(200)
    }

    it("destroys a query") {
      val query = newQuery
      val controller = createMockController
      controller.destroyResource(query.id)
      controller.status should equal(200)
    }

  }

}
