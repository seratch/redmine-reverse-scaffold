package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Tracker

class TrackersController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Tracker
  override def resourcesName = "trackers"
  override def resourceName = "tracker"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("fields_bits") is numeric & intValue,
    paramKey("default_status_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "is_in_chlog" -> ParamType.Boolean,
    "position" -> ParamType.Int,
    "is_in_roadmap" -> ParamType.Boolean,
    "fields_bits" -> ParamType.Int,
    "default_status_id" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("fields_bits") is numeric & intValue,
    paramKey("default_status_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "is_in_chlog" -> ParamType.Boolean,
    "position" -> ParamType.Int,
    "is_in_roadmap" -> ParamType.Boolean,
    "fields_bits" -> ParamType.Int,
    "default_status_id" -> ParamType.Int
  )

}
