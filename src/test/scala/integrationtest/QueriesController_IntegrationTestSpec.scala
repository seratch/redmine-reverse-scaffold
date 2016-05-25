package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class QueriesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.queries, "/*")

  override def afterAll() {
    super.afterAll()
    Query.deleteAll()
  }

  def newQuery = FactoryGirl(Query).create()

  it should "show queries" in {
    get("/queries") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/queries/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/queries.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/queries.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a query in detail" in {
    get(s"/queries/${newQuery.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/queries/${newQuery.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/queries/${newQuery.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/queries/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a query" in {
    post(s"/queries",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "filters" -> "dummy",
      "user_id" -> Int.MaxValue.toString(),
      "column_names" -> "dummy",
      "sort_criteria" -> "dummy",
      "group_by" -> "dummy",
      "type" -> "dummy",
      "visibility" -> Int.MaxValue.toString(),
      "options" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/queries",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "filters" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "column_names" -> "dummy",
        "sort_criteria" -> "dummy",
        "group_by" -> "dummy",
        "type" -> "dummy",
        "visibility" -> Int.MaxValue.toString(),
        "options" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Query.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/queries/${newQuery.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a query" in {
    put(s"/queries/${newQuery.id}",
      "project_id" -> Int.MaxValue.toString(),
      "name" -> "dummy",
      "filters" -> "dummy",
      "user_id" -> Int.MaxValue.toString(),
      "column_names" -> "dummy",
      "sort_criteria" -> "dummy",
      "group_by" -> "dummy",
      "type" -> "dummy",
      "visibility" -> Int.MaxValue.toString(),
      "options" -> "dummy") {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/queries/${newQuery.id}",
        "project_id" -> Int.MaxValue.toString(),
        "name" -> "dummy",
        "filters" -> "dummy",
        "user_id" -> Int.MaxValue.toString(),
        "column_names" -> "dummy",
        "sort_criteria" -> "dummy",
        "group_by" -> "dummy",
        "type" -> "dummy",
        "visibility" -> Int.MaxValue.toString(),
        "options" -> "dummy",
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a query" in {
    delete(s"/queries/${newQuery.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/queries/${newQuery.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
