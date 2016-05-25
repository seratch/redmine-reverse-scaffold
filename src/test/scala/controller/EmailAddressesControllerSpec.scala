package controller

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import model._

// NOTICE before/after filters won't be executed by default
class EmailAddressesControllerSpec extends FunSpec with Matchers with BeforeAndAfterAll with DBSettings {

  override def afterAll() {
    super.afterAll()
    EmailAddress.deleteAll()
  }

  def createMockController = new EmailAddressesController with MockController
  def newEmailAddress = FactoryGirl(EmailAddress).create()

  describe("EmailAddressesController") {

    describe("shows email addresses") {
      it("shows HTML response") {
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/emailAddresses/index"))
        controller.contentType should equal("text/html; charset=utf-8")
      }

      it("shows JSON response") {
        implicit val format = Format.JSON
        val controller = createMockController
        controller.showResources()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/emailAddresses/index"))
        controller.contentType should equal("application/json; charset=utf-8")
      }
    }

    describe("shows a email address") {
      it("shows HTML response") {
        val emailAddress = newEmailAddress
        val controller = createMockController
        controller.showResource(emailAddress.id)
        controller.status should equal(200)
        controller.getFromRequestScope[EmailAddress]("item") should equal(Some(emailAddress))
        controller.renderCall.map(_.path) should equal(Some("/emailAddresses/show"))
      }
    }

    describe("shows new resource input form") {
      it("shows HTML response") {
        val controller = createMockController
        controller.newResource()
        controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/emailAddresses/new"))
      }
    }

    describe("creates a email address") {
      it("succeeds with valid parameters") {
        val controller = createMockController
        controller.prepareParams(
          "user_id" -> Int.MaxValue.toString(),
          "address" -> "dummy",
          "is_default" -> "true",
          "notify" -> "true",
          "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
          "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
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
      val emailAddress = newEmailAddress
      val controller = createMockController
      controller.editResource(emailAddress.id)
      controller.status should equal(200)
        controller.renderCall.map(_.path) should equal(Some("/emailAddresses/edit"))
    }

    it("updates a email address") {
      val emailAddress = newEmailAddress
      val controller = createMockController
      controller.prepareParams(
        "user_id" -> Int.MaxValue.toString(),
        "address" -> "dummy",
        "is_default" -> "true",
        "notify" -> "true",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()))
      controller.updateResource(emailAddress.id)
      controller.status should equal(200)
    }

    it("destroys a email address") {
      val emailAddress = newEmailAddress
      val controller = createMockController
      controller.destroyResource(emailAddress.id)
      controller.status should equal(200)
    }

  }

}
