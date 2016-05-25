package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Document

class DocumentsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Document
  override def resourcesName = "documents"
  override def resourceName = "document"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("category_id") is required & numeric & intValue,
    paramKey("title") is required & maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("created_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "category_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "description" -> ParamType.String,
    "created_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("category_id") is required & numeric & intValue,
    paramKey("title") is required & maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("created_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "category_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "description" -> ParamType.String,
    "created_on" -> ParamType.DateTime
  )

}
