package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class JournalsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    Journal.deleteAll()
  }

  def createMockController = new JournalsController with MockController
  def newJournal = FactoryGirl(Journal).create()

  describe("JournalsController") {

    describe("shows journals") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journals/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journals/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a journal") {
      it("shows HTML response") {
        val journal = newJournal
        val controller = createMockController
        controller.showResource(journal.id)
        controller.status should equal(200)
        controller.getFromRequestScope[Journal]("item") should equal(Some(journal))
        controller.renderCall.map(_.path) should equal(Some("/journals/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journals/new"))
      }
    }

    describe("creates a journal") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "journalized_id" -> Int.MaxValue.toString(),
          "journalized_type" -> "dummy",
          "user_id" -> Int.MaxValue.toString(),
          "notes" -> "dummy",
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "private_notes" -> "true")
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
      val journal = newJournal
      val controller = createMockController
      controller.editResource(journal.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journals/edit"))
    }

    it("updates a journal") {
      val journal = newJournal
      val controller = createMockController
      controller.prepareParams(
        "journalized_id" -> Int.MaxValue.toString(),
        "journalized_type" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "notes" -> "dummy",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "private_notes" -> "true")
      controller.updateResource(journal.id)
      controller.status should equal(200)
    }

    it("destroys a journal") {
      val journal = newJournal
      val controller = createMockController
      controller.destroyResource(journal.id)
      controller.status should equal(200)
    }

  }

}
