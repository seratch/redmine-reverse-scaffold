package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.WikiContent

class WikiContentsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = WikiContent
  override def resourcesName = "wikiContents"
  override def resourceName = "wikiContent"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("page_id") is required & numeric & intValue,
    paramKey("author_id") is numeric & intValue,
    paramKey("text") is maxLength(2147483647),
    paramKey("comments") is maxLength(1024),
    paramKey("updated_on") is required & dateTimeFormat,
    paramKey("version") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "page_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "text" -> ParamType.String,
    "comments" -> ParamType.String,
    "updated_on" -> ParamType.DateTime,
    "version" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("page_id") is required & numeric & intValue,
    paramKey("author_id") is numeric & intValue,
    paramKey("text") is maxLength(2147483647),
    paramKey("comments") is maxLength(1024),
    paramKey("updated_on") is required & dateTimeFormat,
    paramKey("version") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "page_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "text" -> ParamType.String,
    "comments" -> ParamType.String,
    "updated_on" -> ParamType.DateTime,
    "version" -> ParamType.Int
  )

}
