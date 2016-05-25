package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.ImportItem

class ImportItemsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = ImportItem
  override def resourcesName = "importItems"
  override def resourceName = "importItem"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("import_id") is required & numeric & intValue,
    paramKey("position") is required & numeric & intValue,
    paramKey("obj_id") is numeric & intValue,
    paramKey("message") is maxLength(65535)
  )
  override def createFormStrongParameters = Seq(
    "import_id" -> ParamType.Int,
    "position" -> ParamType.Int,
    "obj_id" -> ParamType.Int,
    "message" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("import_id") is required & numeric & intValue,
    paramKey("position") is required & numeric & intValue,
    paramKey("obj_id") is numeric & intValue,
    paramKey("message") is maxLength(65535)
  )
  override def updateFormStrongParameters = Seq(
    "import_id" -> ParamType.Int,
    "position" -> ParamType.Int,
    "obj_id" -> ParamType.Int,
    "message" -> ParamType.String
  )

}
