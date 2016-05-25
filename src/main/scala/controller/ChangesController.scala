package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Change

class ChangesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Change
  override def resourcesName = "changes"
  override def resourceName = "change"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("changeset_id") is required & numeric & intValue,
    paramKey("action") is required & maxLength(1),
    paramKey("path") is required & maxLength(65535),
    paramKey("from_path") is maxLength(65535),
    paramKey("from_revision") is maxLength(255),
    paramKey("revision") is maxLength(255),
    paramKey("branch") is maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "changeset_id" -> ParamType.Int,
    "action" -> ParamType.String,
    "path" -> ParamType.String,
    "from_path" -> ParamType.String,
    "from_revision" -> ParamType.String,
    "revision" -> ParamType.String,
    "branch" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("changeset_id") is required & numeric & intValue,
    paramKey("action") is required & maxLength(1),
    paramKey("path") is required & maxLength(65535),
    paramKey("from_path") is maxLength(65535),
    paramKey("from_revision") is maxLength(255),
    paramKey("revision") is maxLength(255),
    paramKey("branch") is maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "changeset_id" -> ParamType.Int,
    "action" -> ParamType.String,
    "path" -> ParamType.String,
    "from_path" -> ParamType.String,
    "from_revision" -> ParamType.String,
    "revision" -> ParamType.String,
    "branch" -> ParamType.String
  )

}
