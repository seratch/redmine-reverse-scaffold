package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Setting

class SettingsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Setting
  override def resourcesName = "settings"
  override def resourceName = "setting"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(255),
    paramKey("value") is maxLength(65535),
    paramKey("updated_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "value" -> ParamType.String,
    "updated_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(255),
    paramKey("value") is maxLength(65535),
    paramKey("updated_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "value" -> ParamType.String,
    "updated_on" -> ParamType.DateTime
  )

}
