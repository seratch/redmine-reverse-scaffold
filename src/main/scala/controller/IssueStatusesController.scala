package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.IssueStatus

class IssueStatusesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = IssueStatus
  override def resourcesName = "issueStatuses"
  override def resourceName = "issueStatus"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("default_done_ratio") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "is_closed" -> ParamType.Boolean,
    "position" -> ParamType.Int,
    "default_done_ratio" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("default_done_ratio") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "is_closed" -> ParamType.Boolean,
    "position" -> ParamType.Int,
    "default_done_ratio" -> ParamType.Int
  )

}
