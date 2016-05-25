package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class IssueRelationsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.issueRelations, "/*")

  override def afterAll() {
    super.afterAll()
    IssueRelation.deleteAll()
  }

  def newIssueRelation = FactoryGirl(IssueRelation).create()

  it should "show issue relations" in {
    get("/issue_relations") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_relations/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_relations.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/issue_relations.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a issue relation in detail" in {
    get(s"/issue_relations/${newIssueRelation.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issue_relations/${newIssueRelation.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/issue_relations/${newIssueRelation.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/issue_relations/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a issue relation" in {
    post(s"/issue_relations",
      "issue_from_id" -> Int.MaxValue.toString(),
      "issue_to_id" -> Int.MaxValue.toString(),
      "relation_type" -> "dummy",
      "delay" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/issue_relations",
        "issue_from_id" -> Int.MaxValue.toString(),
        "issue_to_id" -> Int.MaxValue.toString(),
        "relation_type" -> "dummy",
        "delay" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        IssueRelation.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/issue_relations/${newIssueRelation.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a issue relation" in {
    put(s"/issue_relations/${newIssueRelation.id}",
      "issue_from_id" -> Int.MaxValue.toString(),
      "issue_to_id" -> Int.MaxValue.toString(),
      "relation_type" -> "dummy",
      "delay" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/issue_relations/${newIssueRelation.id}",
        "issue_from_id" -> Int.MaxValue.toString(),
        "issue_to_id" -> Int.MaxValue.toString(),
        "relation_type" -> "dummy",
        "delay" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a issue relation" in {
    delete(s"/issue_relations/${newIssueRelation.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/issue_relations/${newIssueRelation.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
