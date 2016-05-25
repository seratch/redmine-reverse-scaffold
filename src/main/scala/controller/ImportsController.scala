package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Import

class ImportsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Import
  override def resourcesName = "imports"
  override def resourceName = "import"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_at").withDateTime("updated_at")
  override def createForm = validation(createParams,
    paramKey("type") is maxLength(255),
    paramKey("user_id") is required & numeric & intValue,
    paramKey("filename") is maxLength(255),
    paramKey("settings") is maxLength(65535),
    paramKey("total_items") is numeric & intValue,
    paramKey("created_at") is required & dateTimeFormat,
    paramKey("updated_at") is required & dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "type" -> ParamType.String,
    "user_id" -> ParamType.Int,
    "filename" -> ParamType.String,
    "settings" -> ParamType.String,
    "total_items" -> ParamType.Int,
    "finished" -> ParamType.Boolean,
    "created_at" -> ParamType.DateTime,
    "updated_at" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("created_at").withDateTime("updated_at")
  override def updateForm = validation(updateParams,
    paramKey("type") is maxLength(255),
    paramKey("user_id") is required & numeric & intValue,
    paramKey("filename") is maxLength(255),
    paramKey("settings") is maxLength(65535),
    paramKey("total_items") is numeric & intValue,
    paramKey("created_at") is required & dateTimeFormat,
    paramKey("updated_at") is required & dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "type" -> ParamType.String,
    "user_id" -> ParamType.Int,
    "filename" -> ParamType.String,
    "settings" -> ParamType.String,
    "total_items" -> ParamType.Int,
    "finished" -> ParamType.Boolean,
    "created_at" -> ParamType.DateTime,
    "updated_at" -> ParamType.DateTime
  )

}
