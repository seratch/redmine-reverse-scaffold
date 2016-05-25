package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class ChangeetsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.changeets, "/*")

  override def afterAll() {
    super.afterAll()
    Changeet.deleteAll()
  }

  def newChangeet = FactoryGirl(Changeet).create()

  it should "show changeets" in {
    get("/changeets") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/changeets/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/changeets.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/changeets.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a changeet in detail" in {
    get(s"/changeets/${newChangeet.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/changeets/${newChangeet.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/changeets/${newChangeet.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/changeets/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a changeet" in {
    post(s"/changeets",
      "repository_id" -> Int.MaxValue.toString(),
      "revision" -> "dummy",
      "committer" -> "dummy",
      "committed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "comments" -> "dummy",
      "commit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "scmid" -> "dummy",
      "user_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/changeets",
        "repository_id" -> Int.MaxValue.toString(),
        "revision" -> "dummy",
        "committer" -> "dummy",
        "committed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "comments" -> "dummy",
        "commit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "scmid" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Changeet.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/changeets/${newChangeet.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a changeet" in {
    put(s"/changeets/${newChangeet.id}",
      "repository_id" -> Int.MaxValue.toString(),
      "revision" -> "dummy",
      "committer" -> "dummy",
      "committed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "comments" -> "dummy",
      "commit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
      "scmid" -> "dummy",
      "user_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/changeets/${newChangeet.id}",
        "repository_id" -> Int.MaxValue.toString(),
        "revision" -> "dummy",
        "committer" -> "dummy",
        "committed_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "comments" -> "dummy",
        "commit_date" -> skinny.util.DateTimeUtil.toString(new LocalDate()),
        "scmid" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a changeet" in {
    delete(s"/changeets/${newChangeet.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/changeets/${newChangeet.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
