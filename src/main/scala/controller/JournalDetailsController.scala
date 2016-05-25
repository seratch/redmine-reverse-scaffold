package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.JournalDetail

class JournalDetailsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = JournalDetail
  override def resourcesName = "journalDetails"
  override def resourceName = "journalDetail"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("journal_id") is required & numeric & intValue,
    paramKey("property") is required & maxLength(30),
    paramKey("prop_key") is required & maxLength(30),
    paramKey("old_value") is maxLength(65535),
    paramKey("value") is maxLength(65535)
  )
  override def createFormStrongParameters = Seq(
    "journal_id" -> ParamType.Int,
    "property" -> ParamType.String,
    "prop_key" -> ParamType.String,
    "old_value" -> ParamType.String,
    "value" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("journal_id") is required & numeric & intValue,
    paramKey("property") is required & maxLength(30),
    paramKey("prop_key") is required & maxLength(30),
    paramKey("old_value") is maxLength(65535),
    paramKey("value") is maxLength(65535)
  )
  override def updateFormStrongParameters = Seq(
    "journal_id" -> ParamType.Int,
    "property" -> ParamType.String,
    "prop_key" -> ParamType.String,
    "old_value" -> ParamType.String,
    "value" -> ParamType.String
  )

}
