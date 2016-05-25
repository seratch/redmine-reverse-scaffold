package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class IssueStatusesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.issueStatuses, "/*")

  override def afterAll() {
    super.afterAll()
    IssueStatus.deleteAll()
  }

  def newIssueStatus = FactoryGirl(IssueStatus).create()

  it should "show issue statuses" in {
    get("/issue_statuses") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_statuses/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_statuses.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_statuses.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a issue status in detail" in {
    get(s"/issue_statuses/${newIssueStatus.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issue_statuses/${newIssueStatus.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issue_statuses/${newIssueStatus.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/issue_statuses/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a issue status" in {
    post(s"/issue_statuses",
      "name" -> "dummy",
      "is_closed" -> "true",
      "position" -> Int.MaxValue.toString(),
      "default_done_ratio" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/issue_statuses",
        "name" -> "dummy",
        "is_closed" -> "true",
        "position" -> Int.MaxValue.toString(),
        "default_done_ratio" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        IssueStatus.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/issue_statuses/${newIssueStatus.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a issue status" in {
    put(s"/issue_statuses/${newIssueStatus.id}",
      "name" -> "dummy",
      "is_closed" -> "true",
      "position" -> Int.MaxValue.toString(),
      "default_done_ratio" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/issue_statuses/${newIssueStatus.id}",
        "name" -> "dummy",
        "is_closed" -> "true",
        "position" -> Int.MaxValue.toString(),
        "default_done_ratio" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a issue status" in {
    delete(s"/issue_statuses/${newIssueStatus.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/issue_statuses/${newIssueStatus.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
