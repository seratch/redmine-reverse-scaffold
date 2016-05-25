package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.CustomFieldEnumeration

class CustomFieldEnumerationsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = CustomFieldEnumeration
  override def resourcesName = "customFieldEnumerations"
  override def resourceName = "customFieldEnumeration"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("custom_field_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("position") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "custom_field_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "active" -> ParamType.Boolean,
    "position" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("custom_field_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("position") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "custom_field_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "active" -> ParamType.Boolean,
    "position" -> ParamType.Int
  )

}
