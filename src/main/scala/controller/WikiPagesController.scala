package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.WikiPage

class WikiPagesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = WikiPage
  override def resourcesName = "wikiPages"
  override def resourceName = "wikiPage"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("wiki_id") is required & numeric & intValue,
    paramKey("title") is required & maxLength(255),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("parent_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "wiki_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "protected" -> ParamType.Boolean,
    "parent_id" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("wiki_id") is required & numeric & intValue,
    paramKey("title") is required & maxLength(255),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("parent_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "wiki_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "protected" -> ParamType.Boolean,
    "parent_id" -> ParamType.Int
  )

}
