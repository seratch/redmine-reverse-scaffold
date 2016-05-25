package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Member

class MembersController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Member
  override def resourcesName = "members"
  override def resourceName = "member"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "project_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "mail_notification" -> ParamType.Boolean
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "project_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "mail_notification" -> ParamType.Boolean
  )

}
