package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class TimeEntriesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.timeEntries, "/*")

  override def afterAll() {
    super.afterAll()
    TimeEntry.deleteAll()
  }

  def newTimeEntry = FactoryGirl(TimeEntry).create()

  it should "show time entries" in {
    get("/time_entries") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/time_entries/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/time_entries.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/time_entries.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a time entry in detail" in {
    get(s"/time_entries/${newTimeEntry.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/time_entries/${newTimeEntry.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/time_entries/${newTimeEntry.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/time_entries/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a time entry" in {
    post(s"/time_entries",
      "project_id" -> Int.MaxValue.toString(),
      "user_id" -> Int.MaxValue.toString(),
      "issue_id" -> Int.MaxValue.toString(),
      "hours" -> Float.MaxValue.toString(),
      "comments" -> "dummy",
      "activity_id" -> Int.MaxValue.toString(),
      "spent_on" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "tyear" -> Int.MaxValue.toString(),
      "tmonth" -> Int.MaxValue.toString(),
      "tweek" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/time_entries",
        "project_id" -> Int.MaxValue.toString(),
        "user_id" -> Int.MaxValue.toString(),
        "issue_id" -> Int.MaxValue.toString(),
        "hours" -> Float.MaxValue.toString(),
        "comments" -> "dummy",
        "activity_id" -> Int.MaxValue.toString(),
        "spent_on" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "tyear" -> Int.MaxValue.toString(),
        "tmonth" -> Int.MaxValue.toString(),
        "tweek" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        TimeEntry.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/time_entries/${newTimeEntry.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a time entry" in {
    put(s"/time_entries/${newTimeEntry.id}",
      "project_id" -> Int.MaxValue.toString(),
      "user_id" -> Int.MaxValue.toString(),
      "issue_id" -> Int.MaxValue.toString(),
      "hours" -> Float.MaxValue.toString(),
      "comments" -> "dummy",
      "activity_id" -> Int.MaxValue.toString(),
      "spent_on" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "tyear" -> Int.MaxValue.toString(),
      "tmonth" -> Int.MaxValue.toString(),
      "tweek" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/time_entries/${newTimeEntry.id}",
        "project_id" -> Int.MaxValue.toString(),
        "user_id" -> Int.MaxValue.toString(),
        "issue_id" -> Int.MaxValue.toString(),
        "hours" -> Float.MaxValue.toString(),
        "comments" -> "dummy",
        "activity_id" -> Int.MaxValue.toString(),
        "spent_on" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "tyear" -> Int.MaxValue.toString(),
        "tmonth" -> Int.MaxValue.toString(),
        "tweek" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a time entry" in {
    delete(s"/time_entries/${newTimeEntry.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/time_entries/${newTimeEntry.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
