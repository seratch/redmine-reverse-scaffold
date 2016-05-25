package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.CustomValue

class CustomValuesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = CustomValue
  override def resourcesName = "customValues"
  override def resourceName = "customValue"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("customized_type") is required & maxLength(30),
    paramKey("customized_id") is required & numeric & intValue,
    paramKey("custom_field_id") is required & numeric & intValue,
    paramKey("value") is maxLength(65535)
  )
  override def createFormStrongParameters = Seq(
    "customized_type" -> ParamType.String,
    "customized_id" -> ParamType.Int,
    "custom_field_id" -> ParamType.Int,
    "value" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("customized_type") is required & maxLength(30),
    paramKey("customized_id") is required & numeric & intValue,
    paramKey("custom_field_id") is required & numeric & intValue,
    paramKey("value") is maxLength(65535)
  )
  override def updateFormStrongParameters = Seq(
    "customized_type" -> ParamType.String,
    "customized_id" -> ParamType.Int,
    "custom_field_id" -> ParamType.Int,
    "value" -> ParamType.String
  )

}
