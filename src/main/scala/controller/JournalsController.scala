package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Journal

class JournalsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Journal
  override def resourcesName = "journals"
  override def resourceName = "journal"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("journalized_id") is required & numeric & intValue,
    paramKey("journalized_type") is required & maxLength(30),
    paramKey("user_id") is required & numeric & intValue,
    paramKey("notes") is maxLength(65535),
    paramKey("created_on") is required & dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "journalized_id" -> ParamType.Int,
    "journalized_type" -> ParamType.String,
    "user_id" -> ParamType.Int,
    "notes" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "private_notes" -> ParamType.Boolean
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("journalized_id") is required & numeric & intValue,
    paramKey("journalized_type") is required & maxLength(30),
    paramKey("user_id") is required & numeric & intValue,
    paramKey("notes") is maxLength(65535),
    paramKey("created_on") is required & dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "journalized_id" -> ParamType.Int,
    "journalized_type" -> ParamType.String,
    "user_id" -> ParamType.Int,
    "notes" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "private_notes" -> ParamType.Boolean
  )

}
