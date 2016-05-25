package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Message

class MessagesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Message
  override def resourcesName = "messages"
  override def resourceName = "message"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("board_id") is required & numeric & intValue,
    paramKey("parent_id") is numeric & intValue,
    paramKey("subject") is required & maxLength(255),
    paramKey("content") is maxLength(65535),
    paramKey("author_id") is numeric & intValue,
    paramKey("replies_count") is required & numeric & intValue,
    paramKey("last_reply_id") is numeric & intValue,
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat,
    paramKey("sticky") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "board_id" -> ParamType.Int,
    "parent_id" -> ParamType.Int,
    "subject" -> ParamType.String,
    "content" -> ParamType.String,
    "author_id" -> ParamType.Int,
    "replies_count" -> ParamType.Int,
    "last_reply_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "locked" -> ParamType.Boolean,
    "sticky" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("board_id") is required & numeric & intValue,
    paramKey("parent_id") is numeric & intValue,
    paramKey("subject") is required & maxLength(255),
    paramKey("content") is maxLength(65535),
    paramKey("author_id") is numeric & intValue,
    paramKey("replies_count") is required & numeric & intValue,
    paramKey("last_reply_id") is numeric & intValue,
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat,
    paramKey("sticky") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "board_id" -> ParamType.Int,
    "parent_id" -> ParamType.Int,
    "subject" -> ParamType.String,
    "content" -> ParamType.String,
    "author_id" -> ParamType.Int,
    "replies_count" -> ParamType.Int,
    "last_reply_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "locked" -> ParamType.Boolean,
    "sticky" -> ParamType.Int
  )

}
