package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.WikiContentVersion

class WikiContentVersionsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = WikiContentVersion
  override def resourcesName = "wikiContentVersions"
  override def resourceName = "wikiContentVersion"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("wiki_content_id") is required & numeric & intValue,
    paramKey("page_id") is required & numeric & intValue,
    paramKey("author_id") is numeric & intValue,
    paramKey("compression") is maxLength(6),
    paramKey("comments") is maxLength(1024),
    paramKey("updated_on") is required & dateTimeFormat,
    paramKey("version") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "wiki_content_id" -> ParamType.Int,
    "page_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "data" -> ParamType.ByteArray,
    "compression" -> ParamType.String,
    "comments" -> ParamType.String,
    "updated_on" -> ParamType.DateTime,
    "version" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("wiki_content_id") is required & numeric & intValue,
    paramKey("page_id") is required & numeric & intValue,
    paramKey("author_id") is numeric & intValue,
    paramKey("compression") is maxLength(6),
    paramKey("comments") is maxLength(1024),
    paramKey("updated_on") is required & dateTimeFormat,
    paramKey("version") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "wiki_content_id" -> ParamType.Int,
    "page_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "data" -> ParamType.ByteArray,
    "compression" -> ParamType.String,
    "comments" -> ParamType.String,
    "updated_on" -> ParamType.DateTime,
    "version" -> ParamType.Int
  )

}
