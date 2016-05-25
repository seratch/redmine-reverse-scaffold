package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Query

class QueriesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Query
  override def resourcesName = "queries"
  override def resourceName = "query"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("project_id") is numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("filters") is maxLength(65535),
    paramKey("user_id") is required & numeric & intValue,
    paramKey("column_names") is maxLength(65535),
    paramKey("sort_criteria") is maxLength(65535),
    paramKey("group_by") is maxLength(255),
    paramKey("type") is maxLength(255),
    paramKey("visibility") is numeric & intValue,
    paramKey("options") is maxLength(65535)
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "filters" -> ParamType.String,
    "user_id" -> ParamType.Int,
    "column_names" -> ParamType.String,
    "sort_criteria" -> ParamType.String,
    "group_by" -> ParamType.String,
    "type" -> ParamType.String,
    "visibility" -> ParamType.Int,
    "options" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("project_id") is numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("filters") is maxLength(65535),
    paramKey("user_id") is required & numeric & intValue,
    paramKey("column_names") is maxLength(65535),
    paramKey("sort_criteria") is maxLength(65535),
    paramKey("group_by") is maxLength(255),
    paramKey("type") is maxLength(255),
    paramKey("visibility") is numeric & intValue,
    paramKey("options") is maxLength(65535)
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "filters" -> ParamType.String,
    "user_id" -> ParamType.Int,
    "column_names" -> ParamType.String,
    "sort_criteria" -> ParamType.String,
    "group_by" -> ParamType.String,
    "type" -> ParamType.String,
    "visibility" -> ParamType.Int,
    "options" -> ParamType.String
  )

}
