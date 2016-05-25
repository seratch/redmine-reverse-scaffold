package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.CustomField

class CustomFieldsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = CustomField
  override def resourcesName = "customFields"
  override def resourceName = "customField"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("type") is required & maxLength(30),
    paramKey("name") is required & maxLength(30),
    paramKey("field_format") is required & maxLength(30),
    paramKey("possible_values") is maxLength(65535),
    paramKey("regexp") is maxLength(255),
    paramKey("min_length") is numeric & intValue,
    paramKey("max_length") is numeric & intValue,
    paramKey("position") is numeric & intValue,
    paramKey("default_value") is maxLength(65535),
    paramKey("format_store") is maxLength(65535),
    paramKey("description") is maxLength(65535)
  )
  override def createFormStrongParameters = Seq(
    "type" -> ParamType.String,
    "name" -> ParamType.String,
    "field_format" -> ParamType.String,
    "possible_values" -> ParamType.String,
    "regexp" -> ParamType.String,
    "min_length" -> ParamType.Int,
    "max_length" -> ParamType.Int,
    "is_required" -> ParamType.Boolean,
    "is_for_all" -> ParamType.Boolean,
    "is_filter" -> ParamType.Boolean,
    "position" -> ParamType.Int,
    "searchable" -> ParamType.Boolean,
    "default_value" -> ParamType.String,
    "editable" -> ParamType.Boolean,
    "visible" -> ParamType.Boolean,
    "multiple" -> ParamType.Boolean,
    "format_store" -> ParamType.String,
    "description" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("type") is required & maxLength(30),
    paramKey("name") is required & maxLength(30),
    paramKey("field_format") is required & maxLength(30),
    paramKey("possible_values") is maxLength(65535),
    paramKey("regexp") is maxLength(255),
    paramKey("min_length") is numeric & intValue,
    paramKey("max_length") is numeric & intValue,
    paramKey("position") is numeric & intValue,
    paramKey("default_value") is maxLength(65535),
    paramKey("format_store") is maxLength(65535),
    paramKey("description") is maxLength(65535)
  )
  override def updateFormStrongParameters = Seq(
    "type" -> ParamType.String,
    "name" -> ParamType.String,
    "field_format" -> ParamType.String,
    "possible_values" -> ParamType.String,
    "regexp" -> ParamType.String,
    "min_length" -> ParamType.Int,
    "max_length" -> ParamType.Int,
    "is_required" -> ParamType.Boolean,
    "is_for_all" -> ParamType.Boolean,
    "is_filter" -> ParamType.Boolean,
    "position" -> ParamType.Int,
    "searchable" -> ParamType.Boolean,
    "default_value" -> ParamType.String,
    "editable" -> ParamType.Boolean,
    "visible" -> ParamType.Boolean,
    "multiple" -> ParamType.Boolean,
    "format_store" -> ParamType.String,
    "description" -> ParamType.String
  )

}
