package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Enumeration

class EnumerationsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Enumeration
  override def resourcesName = "enumerations"
  override def resourceName = "enumeration"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("type") is maxLength(255),
    paramKey("project_id") is numeric & intValue,
    paramKey("parent_id") is numeric & intValue,
    paramKey("position_name") is maxLength(30)
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "position" -> ParamType.Int,
    "is_default" -> ParamType.Boolean,
    "type" -> ParamType.String,
    "active" -> ParamType.Boolean,
    "project_id" -> ParamType.Int,
    "parent_id" -> ParamType.Int,
    "position_name" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("type") is maxLength(255),
    paramKey("project_id") is numeric & intValue,
    paramKey("parent_id") is numeric & intValue,
    paramKey("position_name") is maxLength(30)
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "position" -> ParamType.Int,
    "is_default" -> ParamType.Boolean,
    "type" -> ParamType.String,
    "active" -> ParamType.Boolean,
    "project_id" -> ParamType.Int,
    "parent_id" -> ParamType.Int,
    "position_name" -> ParamType.String
  )

}
