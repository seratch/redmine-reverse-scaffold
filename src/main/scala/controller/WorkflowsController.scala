package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Workflow

class WorkflowsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Workflow
  override def resourcesName = "workflows"
  override def resourceName = "workflow"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("tracker_id") is required & numeric & intValue,
    paramKey("old_status_id") is required & numeric & intValue,
    paramKey("new_status_id") is required & numeric & intValue,
    paramKey("role_id") is required & numeric & intValue,
    paramKey("type") is maxLength(30),
    paramKey("field_name") is maxLength(30),
    paramKey("rule") is maxLength(30)
  )
  override def createFormStrongParameters = Seq(
    "tracker_id" -> ParamType.Int,
    "old_status_id" -> ParamType.Int,
    "new_status_id" -> ParamType.Int,
    "role_id" -> ParamType.Int,
    "assignee" -> ParamType.Boolean,
    "author" -> ParamType.Boolean,
    "type" -> ParamType.String,
    "field_name" -> ParamType.String,
    "rule" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("tracker_id") is required & numeric & intValue,
    paramKey("old_status_id") is required & numeric & intValue,
    paramKey("new_status_id") is required & numeric & intValue,
    paramKey("role_id") is required & numeric & intValue,
    paramKey("type") is maxLength(30),
    paramKey("field_name") is maxLength(30),
    paramKey("rule") is maxLength(30)
  )
  override def updateFormStrongParameters = Seq(
    "tracker_id" -> ParamType.Int,
    "old_status_id" -> ParamType.Int,
    "new_status_id" -> ParamType.Int,
    "role_id" -> ParamType.Int,
    "assignee" -> ParamType.Boolean,
    "author" -> ParamType.Boolean,
    "type" -> ParamType.String,
    "field_name" -> ParamType.String,
    "rule" -> ParamType.String
  )

}
