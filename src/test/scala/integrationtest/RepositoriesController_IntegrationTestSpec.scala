package integrationtest

import org.scalatest._
import skinny._
import skinny.test._
import org.joda.time._
import _root_.controller.Controllers
import model._

class RepositoriesController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport with BeforeAndAfterAll with DBSettings {
  addFilter(Controllers.repositories, "/*")

  override def afterAll() {
    super.afterAll()
    Repository.deleteAll()
  }

  def newRepository = FactoryGirl(Repository).create()

  it should "show repositories" in {
    get("/repositories") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/repositories/") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/repositories.json") {
      logBodyUnless(200)
      status should equal(200)
    }
    get("/repositories.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show a repository in detail" in {
    get(s"/repositories/${newRepository.id}") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/repositories/${newRepository.id}.xml") {
      logBodyUnless(200)
      status should equal(200)
    }
    get(s"/repositories/${newRepository.id}.json") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "show new entry form" in {
    get(s"/repositories/new") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "create a repository" in {
    post(s"/repositories",
      "project_id" -> Int.MaxValue.toString(),
      "url" -> "dummy",
      "login" -> "dummy",
      "password" -> "dummy",
      "root_url" -> "dummy",
      "type" -> "dummy",
      "path_encoding" -> "dummy",
      "log_encoding" -> "dummy",
      "extra_info" -> "dummy",
      "identifier" -> "dummy",
      "is_default" -> "true",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      post(s"/repositories",
        "project_id" -> Int.MaxValue.toString(),
        "url" -> "dummy",
        "login" -> "dummy",
        "password" -> "dummy",
        "root_url" -> "dummy",
        "type" -> "dummy",
        "path_encoding" -> "dummy",
        "log_encoding" -> "dummy",
        "extra_info" -> "dummy",
        "identifier" -> "dummy",
        "is_default" -> "true",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
        val id = header("Location").split("/").last.toLong
        Repository.findById(id).isDefined should equal(true)
      }
    }
  }

  it should "show the edit form" in {
    get(s"/repositories/${newRepository.id}/edit") {
      logBodyUnless(200)
      status should equal(200)
    }
  }

  it should "update a repository" in {
    put(s"/repositories/${newRepository.id}",
      "project_id" -> Int.MaxValue.toString(),
      "url" -> "dummy",
      "login" -> "dummy",
      "password" -> "dummy",
      "root_url" -> "dummy",
      "type" -> "dummy",
      "path_encoding" -> "dummy",
      "log_encoding" -> "dummy",
      "extra_info" -> "dummy",
      "identifier" -> "dummy",
      "is_default" -> "true",
      "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime())) {
      logBodyUnless(403)
      status should equal(403)
    }

    withSession("csrf-token" -> "valid_token") {
      put(s"/repositories/${newRepository.id}",
        "project_id" -> Int.MaxValue.toString(),
        "url" -> "dummy",
        "login" -> "dummy",
        "password" -> "dummy",
        "root_url" -> "dummy",
        "type" -> "dummy",
        "path_encoding" -> "dummy",
        "log_encoding" -> "dummy",
        "extra_info" -> "dummy",
        "identifier" -> "dummy",
        "is_default" -> "true",
        "created_on" -> skinny.util.DateTimeUtil.toString(new DateTime()),
        "csrf-token" -> "valid_token") {
        logBodyUnless(302)
        status should equal(302)
      }
    }
  }

  it should "delete a repository" in {
    delete(s"/repositories/${newRepository.id}") {
      logBodyUnless(403)
      status should equal(403)
    }
    withSession("csrf-token" -> "valid_token") {
      delete(s"/repositories/${newRepository.id}?csrf-token=valid_token") {
        logBodyUnless(200)
        status should equal(200)
      }
    }
  }

}
