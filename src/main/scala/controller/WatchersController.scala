package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Watcher

class WatchersController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Watcher
  override def resourcesName = "watchers"
  override def resourceName = "watcher"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("watchable_type") is required & maxLength(255),
    paramKey("watchable_id") is required & numeric & intValue,
    paramKey("user_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "watchable_type" -> ParamType.String,
    "watchable_id" -> ParamType.Int,
    "user_id" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("watchable_type") is required & maxLength(255),
    paramKey("watchable_id") is required & numeric & intValue,
    paramKey("user_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "watchable_type" -> ParamType.String,
    "watchable_id" -> ParamType.Int,
    "user_id" -> ParamType.Int
  )

}
