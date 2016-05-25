package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Repository

class RepositoriesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Repository
  override def resourcesName = "repositories"
  override def resourceName = "repository"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("url") is required & maxLength(255),
    paramKey("login") is maxLength(60),
    paramKey("password") is maxLength(255),
    paramKey("root_url") is maxLength(255),
    paramKey("type") is maxLength(255),
    paramKey("path_encoding") is maxLength(64),
    paramKey("log_encoding") is maxLength(64),
    paramKey("extra_info") is maxLength(65535),
    paramKey("identifier") is maxLength(255),
    paramKey("created_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "url" -> ParamType.String,
    "login" -> ParamType.String,
    "password" -> ParamType.String,
    "root_url" -> ParamType.String,
    "type" -> ParamType.String,
    "path_encoding" -> ParamType.String,
    "log_encoding" -> ParamType.String,
    "extra_info" -> ParamType.String,
    "identifier" -> ParamType.String,
    "is_default" -> ParamType.Boolean,
    "created_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("url") is required & maxLength(255),
    paramKey("login") is maxLength(60),
    paramKey("password") is maxLength(255),
    paramKey("root_url") is maxLength(255),
    paramKey("type") is maxLength(255),
    paramKey("path_encoding") is maxLength(64),
    paramKey("log_encoding") is maxLength(64),
    paramKey("extra_info") is maxLength(65535),
    paramKey("identifier") is maxLength(255),
    paramKey("created_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "url" -> ParamType.String,
    "login" -> ParamType.String,
    "password" -> ParamType.String,
    "root_url" -> ParamType.String,
    "type" -> ParamType.String,
    "path_encoding" -> ParamType.String,
    "log_encoding" -> ParamType.String,
    "extra_info" -> ParamType.String,
    "identifier" -> ParamType.String,
    "is_default" -> ParamType.Boolean,
    "created_on" -> ParamType.DateTime
  )

}
