package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.IssueCategory

class IssueCategoriesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = IssueCategory
  override def resourcesName = "issueCategories"
  override def resourceName = "issueCategory"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(60),
    paramKey("assigned_to_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "assigned_to_id" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(60),
    paramKey("assigned_to_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "assigned_to_id" -> ParamType.Int
  )

}
