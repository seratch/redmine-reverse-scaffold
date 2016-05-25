package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Version

class VersionsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Version
  override def resourcesName = "versions"
  override def resourceName = "version"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDate("effective_date").withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("description") is maxLength(255),
    paramKey("effective_date") is dateFormat,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("wiki_page_title") is maxLength(255),
    paramKey("status") is maxLength(255),
    paramKey("sharing") is required & maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "description" -> ParamType.String,
    "effective_date" -> ParamType.LocalDate,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "wiki_page_title" -> ParamType.String,
    "status" -> ParamType.String,
    "sharing" -> ParamType.String
  )

  override def updateParams = Params(params).withDate("effective_date").withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("description") is maxLength(255),
    paramKey("effective_date") is dateFormat,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("wiki_page_title") is maxLength(255),
    paramKey("status") is maxLength(255),
    paramKey("sharing") is required & maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "description" -> ParamType.String,
    "effective_date" -> ParamType.LocalDate,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "wiki_page_title" -> ParamType.String,
    "status" -> ParamType.String,
    "sharing" -> ParamType.String
  )

}
