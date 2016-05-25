package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Wiki

class WikisController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Wiki
  override def resourcesName = "wikis"
  override def resourceName = "wiki"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("start_page") is required & maxLength(255),
    paramKey("status") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "start_page" -> ParamType.String,
    "status" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("start_page") is required & maxLength(255),
    paramKey("status") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "start_page" -> ParamType.String,
    "status" -> ParamType.Int
  )

}
