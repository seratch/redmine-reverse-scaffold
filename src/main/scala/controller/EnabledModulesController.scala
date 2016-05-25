package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.EnabledModule

class EnabledModulesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = EnabledModule
  override def resourcesName = "enabledModules"
  override def resourceName = "enabledModule"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("project_id") is numeric & intValue,
    paramKey("name") is required & maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("project_id") is numeric & intValue,
    paramKey("name") is required & maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String
  )

}
