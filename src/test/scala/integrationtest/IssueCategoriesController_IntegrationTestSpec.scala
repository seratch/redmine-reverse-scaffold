package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class IssueCategoriesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.issueCategories, "/*")

  override def afterAll() {
    super.afterAll()
    IssueCategory.deleteAll()
  }

  def newIssueCategory = FactoryGirl(IssueCategory).create()

  it should "show issue categories" in {
    get("/issue_categories") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_categories/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_categories.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_categories.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a issue category in detail" in {
    get(s"/issue_categories/${newIssueCategory.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issue_categories/${newIssueCategory.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issue_categories/${newIssueCategory.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/issue_categories/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a issue category" in {
    post(s"/issue_categories",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "assigned_to_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/issue_categories",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "assigned_to_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        IssueCategory.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/issue_categories/${newIssueCategory.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a issue category" in {
    put(s"/issue_categories/${newIssueCategory.id}",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "assigned_to_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/issue_categories/${newIssueCategory.id}",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "assigned_to_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a issue category" in {
    delete(s"/issue_categories/${newIssueCategory.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/issue_categories/${newIssueCategory.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
