package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class ChangeetsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Changeet.deleteAll()
  }

  def createMockController = new ChangeetsController with MockController
  def newChangeet = FactoryGirl(Changeet).create()

  describe("ChangeetsController") {

    describe("shows changeets") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changeets/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changeets/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a changeet") {
      it("shows HTML response") {
        val changeet = newChangeet
        val controller = createMockController
        controller.showResource(changeet.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Changeet]("item") should equal(Some(changeet))
        controller.renderCall.map(_.path) should equal(Some("/changeets/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changeets/new"))
      }
    }

    describe("creates a changeet") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "repository_id" -> Int.MaxValue.toString(),
          "revision" -> "dummy",
          "committer" -> "dummy",
          "committed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "comments" -> "dummy",
          "commit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
          "scmid" -> "dummy",
          "user_id" -> Int.MaxValue.toString())
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
      val changeet = newChangeet
      val controller = createMockController
      controller.editResource(changeet.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/changeets/edit"))
    }

    it("updates a changeet") {
      val changeet = newChangeet
      val controller = createMockController
      controller.prepareParams(
        "repository_id" -> Int.MaxValue.toString(),
        "revision" -> "dummy",
        "committer" -> "dummy",
        "committed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "comments" -> "dummy",
        "commit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "scmid" -> "dummy",
        "user_id" -> Int.MaxValue.toString())
      controller.updateResource(changeet.id)
      controller.status should equal(200)
    }

    it("destroys a changeet") {
      val changeet = newChangeet
      val controller = createMockController
      controller.destroyResource(changeet.id)
      controller.status should equal(200)
    }

  }

}
