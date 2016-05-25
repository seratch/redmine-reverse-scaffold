package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class WorkflowsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.workflows, "/*")

  override def afterAll() {
    super.afterAll()
    Workflow.deleteAll()
  }

  def newWorkflow = FactoryGirl(Workflow).create()

  it should "show workflows" in {
    get("/workflows") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/workflows/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/workflows.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/workflows.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a workflow in detail" in {
    get(s"/workflows/${newWorkflow.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/workflows/${newWorkflow.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/workflows/${newWorkflow.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/workflows/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a workflow" in {
    post(s"/workflows",
      "tracker_id" -> Int.MaxValue.toString(),
      "old_status_id" -> Int.MaxValue.toString(),
      "new_status_id" -> Int.MaxValue.toString(),
      "role_id" -> Int.MaxValue.toString(),
      "assignee" -> "true",
      "author" -> "true",
      "type" -> "dummy",
      "field_name" -> "dummy",
      "rule" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/workflows",
        "tracker_id" -> Int.MaxValue.toString(),
        "old_status_id" -> Int.MaxValue.toString(),
        "new_status_id" -> Int.MaxValue.toString(),
        "role_id" -> Int.MaxValue.toString(),
        "assignee" -> "true",
        "author" -> "true",
        "type" -> "dummy",
        "field_name" -> "dummy",
        "rule" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Workflow.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/workflows/${newWorkflow.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a workflow" in {
    put(s"/workflows/${newWorkflow.id}",
      "tracker_id" -> Int.MaxValue.toString(),
      "old_status_id" -> Int.MaxValue.toString(),
      "new_status_id" -> Int.MaxValue.toString(),
      "role_id" -> Int.MaxValue.toString(),
      "assignee" -> "true",
      "author" -> "true",
      "type" -> "dummy",
      "field_name" -> "dummy",
      "rule" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/workflows/${newWorkflow.id}",
        "tracker_id" -> Int.MaxValue.toString(),
        "old_status_id" -> Int.MaxValue.toString(),
        "new_status_id" -> Int.MaxValue.toString(),
        "role_id" -> Int.MaxValue.toString(),
        "assignee" -> "true",
        "author" -> "true",
        "type" -> "dummy",
        "field_name" -> "dummy",
        "rule" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a workflow" in {
    delete(s"/workflows/${newWorkflow.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/workflows/${newWorkflow.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
