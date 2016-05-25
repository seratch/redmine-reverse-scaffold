package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.WikiRedirect

class WikiRedirectsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = WikiRedirect
  override def resourcesName = "wikiRedirects"
  override def resourceName = "wikiRedirect"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("wiki_id") is required & numeric & intValue,
    paramKey("title") is maxLength(255),
    paramKey("redirects_to") is maxLength(255),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("redirects_to_wiki_id") is required & numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "wiki_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "redirects_to" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "redirects_to_wiki_id" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("wiki_id") is required & numeric & intValue,
    paramKey("title") is maxLength(255),
    paramKey("redirects_to") is maxLength(255),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("redirects_to_wiki_id") is required & numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "wiki_id" -> ParamType.Int,
    "title" -> ParamType.String,
    "redirects_to" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "redirects_to_wiki_id" -> ParamType.Int
  )

}
