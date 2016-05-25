package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class ProjectsController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.projects, "/*")

  override def afterAll() {
    super.afterAll()
    Project.deleteAll()
  }

  def newProject = FactoryGirl(Project).create()

  it should "show projects" in {
    get("/projects") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/projects/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/projects.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/projects.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a project in detail" in {
    get(s"/projects/${newProject.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/projects/${newProject.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/projects/${newProject.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/projects/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a project" in {
    post(s"/projects",
      "name" -> "dummy",
      "description" -> "dummy",
      "homepage" -> "dummy",
      "is_public" -> "true",
      "parent_id" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "identifier" -> "dummy",
      "status" -> Int.MaxValue.toString(),
      "lft" -> Int.MaxValue.toString(),
      "rgt" -> Int.MaxValue.toString(),
      "inherit_members" -> "true",
      "default_version_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/projects",
        "name" -> "dummy",
        "description" -> "dummy",
        "homepage" -> "dummy",
        "is_public" -> "true",
        "parent_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "identifier" -> "dummy",
        "status" -> Int.MaxValue.toString(),
        "lft" -> Int.MaxValue.toString(),
        "rgt" -> Int.MaxValue.toString(),
        "inherit_members" -> "true",
        "default_version_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Project.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/projects/${newProject.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a project" in {
    put(s"/projects/${newProject.id}",
      "name" -> "dummy",
      "description" -> "dummy",
      "homepage" -> "dummy",
      "is_public" -> "true",
      "parent_id" -> Int.MaxValue.toString(),
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
      "identifier" -> "dummy",
      "status" -> Int.MaxValue.toString(),
      "lft" -> Int.MaxValue.toString(),
      "rgt" -> Int.MaxValue.toString(),
      "inherit_members" -> "true",
      "default_version_id" -> Int.MaxValue.toString()) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/projects/${newProject.id}",
        "name" -> "dummy",
        "description" -> "dummy",
        "homepage" -> "dummy",
        "is_public" -> "true",
        "parent_id" -> Int.MaxValue.toString(),
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "updated_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "identifier" -> "dummy",
        "status" -> Int.MaxValue.toString(),
        "lft" -> Int.MaxValue.toString(),
        "rgt" -> Int.MaxValue.toString(),
        "inherit_members" -> "true",
        "default_version_id" -> Int.MaxValue.toString(),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a project" in {
    delete(s"/projects/${newProject.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/projects/${newProject.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
