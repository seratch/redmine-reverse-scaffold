package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Comment

class CommentsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Comment
  override def resourcesName = "comments"
  override def resourceName = "comment"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("commented_type") is required & maxLength(30),
    paramKey("commented_id") is required & numeric & intValue,
    paramKey("author_id") is required & numeric & intValue,
    paramKey("comments") is maxLength(65535),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "commented_type" -> ParamType.String,
    "commented_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "comments" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("commented_type") is required & maxLength(30),
    paramKey("commented_id") is required & numeric & intValue,
    paramKey("author_id") is required & numeric & intValue,
    paramKey("comments") is maxLength(65535),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "commented_type" -> ParamType.String,
    "commented_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "comments" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

}
