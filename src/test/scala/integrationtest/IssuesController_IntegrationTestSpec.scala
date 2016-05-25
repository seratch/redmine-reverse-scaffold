package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class IssuesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.issues, "/*")

  override def afterAll() {
    super.afterAll()
    Issue.deleteAll()
  }

  def newIssue = FactoryGirl(Issue).create()

  it should "show issues" in {
    get("/issues") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issues/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issues.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issues.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a issue in detail" in {
    get(s"/issues/${newIssue.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issues/${newIssue.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issues/${newIssue.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/issues/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a issue" in {
    post(s"/issues",
      "tracker_id" -> Int.MaxValue.toString(),
      "project_id" -> Int.MaxValue.toString(),
      "subject" -> "dummy",
      "description" -> "dummy",
      "due_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "category_id" -> Int.MaxValue.toString(),
      "status_id" -> Int.MaxValue.toString(),
      "assigned_to_id" -> Int.MaxValue.toString(),
      "priority_id" -> Int.MaxValue.toString(),
      "fixed_version_id" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "lock_version" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "start_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "done_ratio" -> Int.MaxValue.toString(),
      "estimated_hours" -> Float.MaxValue.toString(),
      "parent_id" -> Int.MaxValue.toString(),
      "root_id" -> Int.MaxValue.toString(),
      "lft" -> Int.MaxValue.toString(),
      "rgt" -> Int.MaxValue.toString(),
      "is_private" -> "true",
      "closed_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/issues",
        "tracker_id" -> Int.MaxValue.toString(),
        "project_id" -> Int.MaxValue.toString(),
        "subject" -> "dummy",
        "description" -> "dummy",
        "due_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "category_id" -> Int.MaxValue.toString(),
        "status_id" -> Int.MaxValue.toString(),
        "assigned_to_id" -> Int.MaxValue.toString(),
        "priority_id" -> Int.MaxValue.toString(),
        "fixed_version_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "lock_version" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "start_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "done_ratio" -> Int.MaxValue.toString(),
        "estimated_hours" -> Float.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "root_id" -> Int.MaxValue.toString(),
        "lft" -> Int.MaxValue.toString(),
        "rgt" -> Int.MaxValue.toString(),
        "is_private" -> "true",
        "closed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Issue.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/issues/${newIssue.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a issue" in {
    put(s"/issues/${newIssue.id}",
      "tracker_id" -> Int.MaxValue.toString(),
      "project_id" -> Int.MaxValue.toString(),
      "subject" -> "dummy",
      "description" -> "dummy",
      "due_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "category_id" -> Int.MaxValue.toString(),
      "status_id" -> Int.MaxValue.toString(),
      "assigned_to_id" -> Int.MaxValue.toString(),
      "priority_id" -> Int.MaxValue.toString(),
      "fixed_version_id" -> Int.MaxValue.toString(),
      "author_id" -> Int.MaxValue.toString(),
      "lock_version" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "start_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "done_ratio" -> Int.MaxValue.toString(),
      "estimated_hours" -> Float.MaxValue.toString(),
      "parent_id" -> Int.MaxValue.toString(),
      "root_id" -> Int.MaxValue.toString(),
      "lft" -> Int.MaxValue.toString(),
      "rgt" -> Int.MaxValue.toString(),
      "is_private" -> "true",
      "closed_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/issues/${newIssue.id}",
        "tracker_id" -> Int.MaxValue.toString(),
        "project_id" -> Int.MaxValue.toString(),
        "subject" -> "dummy",
        "description" -> "dummy",
        "due_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "category_id" -> Int.MaxValue.toString(),
        "status_id" -> Int.MaxValue.toString(),
        "assigned_to_id" -> Int.MaxValue.toString(),
        "priority_id" -> Int.MaxValue.toString(),
        "fixed_version_id" -> Int.MaxValue.toString(),
        "author_id" -> Int.MaxValue.toString(),
        "lock_version" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "start_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "done_ratio" -> Int.MaxValue.toString(),
        "estimated_hours" -> Float.MaxValue.toString(),
        "parent_id" -> Int.MaxValue.toString(),
        "root_id" -> Int.MaxValue.toString(),
        "lft" -> Int.MaxValue.toString(),
        "rgt" -> Int.MaxValue.toString(),
        "is_private" -> "true",
        "closed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a issue" in {
    delete(s"/issues/${newIssue.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/issues/${newIssue.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
