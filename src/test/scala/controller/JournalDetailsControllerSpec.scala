package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class JournalDetailsControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    JournalDetail.deleteAll()
  }

  def createMockController = new JournalDetailsController with MockController
  def newJournalDetail = FactoryGirl(JournalDetail).create()

  describe("JournalDetailsController") {

    describe("shows journal details") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journalDetails/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journalDetails/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a journal detail") {
      it("shows HTML response") {
        val journalDetail = newJournalDetail
        val controller = createMockController
        controller.showResource(journalDetail.id)
        controller.status should equal(200)
        controller.getFromRequestScope[JournalDetail]("item") should equal(Some(journalDetail))
        controller.renderCall.map(_.path) should equal(Some("/journalDetails/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journalDetails/new"))
      }
    }

    describe("creates a journal detail") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "journal_id" -> Int.MaxValue.toString(),
          "property" -> "dummy",
          "prop_key" -> "dummy",
          "old_value" -> "dummy",
          "value" -> "dummy")
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
      val journalDetail = newJournalDetail
      val controller = createMockController
      controller.editResource(journalDetail.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/journalDetails/edit"))
    }

    it("updates a journal detail") {
      val journalDetail = newJournalDetail
      val controller = createMockController
      controller.prepareParams(
        "journal_id" -> Int.MaxValue.toString(),
        "property" -> "dummy",
        "prop_key" -> "dummy",
        "old_value" -> "dummy",
        "value" -> "dummy")
      controller.updateResource(journalDetail.id)
      controller.status should equal(200)
    }

    it("destroys a journal detail") {
      val journalDetail = newJournalDetail
      val controller = createMockController
      controller.destroyResource(journalDetail.id)
      controller.status should equal(200)
    }

  }

}
