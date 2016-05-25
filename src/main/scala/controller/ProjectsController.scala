package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Project

class ProjectsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Project
  override def resourcesName = "projects"
  override def resourceName = "project"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("homepage") is maxLength(255),
    paramKey("parent_id") is numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("identifier") is maxLength(255),
    paramKey("status") is required & numeric & intValue,
    paramKey("lft") is numeric & intValue,
    paramKey("rgt") is numeric & intValue,
    paramKey("default_version_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "description" -> ParamType.String,
    "homepage" -> ParamType.String,
    "is_public" -> ParamType.Boolean,
    "parent_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "identifier" -> ParamType.String,
    "status" -> ParamType.Int,
    "lft" -> ParamType.Int,
    "rgt" -> ParamType.Int,
    "inherit_members" -> ParamType.Boolean,
    "default_version_id" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("homepage") is maxLength(255),
    paramKey("parent_id") is numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("identifier") is maxLength(255),
    paramKey("status") is required & numeric & intValue,
    paramKey("lft") is numeric & intValue,
    paramKey("rgt") is numeric & intValue,
    paramKey("default_version_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "description" -> ParamType.String,
    "homepage" -> ParamType.String,
    "is_public" -> ParamType.Boolean,
    "parent_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "identifier" -> ParamType.String,
    "status" -> ParamType.Int,
    "lft" -> ParamType.Int,
    "rgt" -> ParamType.Int,
    "inherit_members" -> ParamType.Boolean,
    "default_version_id" -> ParamType.Int
  )

}
