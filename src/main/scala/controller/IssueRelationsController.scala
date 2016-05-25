package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.IssueRelation

class IssueRelationsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = IssueRelation
  override def resourcesName = "issueRelations"
  override def resourceName = "issueRelation"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("issue_from_id") is required & numeric & intValue,
    paramKey("issue_to_id") is required & numeric & intValue,
    paramKey("relation_type") is required & maxLength(255),
    paramKey("delay") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "issue_from_id" -> ParamType.Int,
    "issue_to_id" -> ParamType.Int,
    "relation_type" -> ParamType.String,
    "delay" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("issue_from_id") is required & numeric & intValue,
    paramKey("issue_to_id") is required & numeric & intValue,
    paramKey("relation_type") is required & maxLength(255),
    paramKey("delay") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "issue_from_id" -> ParamType.Int,
    "issue_to_id" -> ParamType.Int,
    "relation_type" -> ParamType.String,
    "delay" -> ParamType.Int
  )

}
