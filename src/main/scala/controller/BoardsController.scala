package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Board

class BoardsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Board
  override def resourcesName = "boards"
  override def resourceName = "board"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("description") is maxLength(255),
    paramKey("position") is numeric & intValue,
    paramKey("topics_count") is required & numeric & intValue,
    paramKey("messages_count") is required & numeric & intValue,
    paramKey("last_message_id") is numeric & intValue,
    paramKey("parent_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "description" -> ParamType.String,
    "position" -> ParamType.Int,
    "topics_count" -> ParamType.Int,
    "messages_count" -> ParamType.Int,
    "last_message_id" -> ParamType.Int,
    "parent_id" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("name") is required & maxLength(255),
    paramKey("description") is maxLength(255),
    paramKey("position") is numeric & intValue,
    paramKey("topics_count") is required & numeric & intValue,
    paramKey("messages_count") is required & numeric & intValue,
    paramKey("last_message_id") is numeric & intValue,
    paramKey("parent_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "name" -> ParamType.String,
    "description" -> ParamType.String,
    "position" -> ParamType.Int,
    "topics_count" -> ParamType.Int,
    "messages_count" -> ParamType.Int,
    "last_message_id" -> ParamType.Int,
    "parent_id" -> ParamType.Int
  )

}
