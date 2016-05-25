package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.UserPreference

class UserPreferencesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = UserPreference
  override def resourcesName = "userPreferences"
  override def resourceName = "userPreference"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("others") is maxLength(65535),
    paramKey("time_zone") is maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "others" -> ParamType.String,
    "hide_mail" -> ParamType.Boolean,
    "time_zone" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("others") is maxLength(65535),
    paramKey("time_zone") is maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "others" -> ParamType.String,
    "hide_mail" -> ParamType.Boolean,
    "time_zone" -> ParamType.String
  )

}
