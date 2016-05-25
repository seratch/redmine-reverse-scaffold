package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.News

class NewsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = News
  override def resourcesName = "news"
  override def resourceName = "news"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("project_id") is numeric & intValue,
    paramKey("title") is required & maxLength(60),
    paramKey("summary") is maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("author_id") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("comments_count") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "summary" -> ParamType.String,
    "description" -> ParamType.String,
    "author_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "comments_count" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("project_id") is numeric & intValue,
    paramKey("title") is required & maxLength(60),
    paramKey("summary") is maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("author_id") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("comments_count") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "summary" -> ParamType.String,
    "description" -> ParamType.String,
    "author_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "comments_count" -> ParamType.Int
  )

}
