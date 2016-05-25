package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Changeet

class ChangeetsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Changeet
  override def resourcesName = "changeets"
  override def resourceName = "changeet"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("committed_on").withDate("commit_date")
  override def createForm = validation(createParams,
    paramKey("repository_id") is required & numeric & intValue,
    paramKey("revision") is required & maxLength(255),
    paramKey("committer") is maxLength(255),
    paramKey("committed_on") is required & dateTimeFormat,
    paramKey("comments") is maxLength(2147483647),
    paramKey("commit_date") is dateFormat,
    paramKey("scmid") is maxLength(255),
    paramKey("user_id") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "repository_id" -> ParamType.Int,
    "revision" -> ParamType.String,
    "committer" -> ParamType.String,
    "committed_on" -> ParamType.DateTime,
    "comments" -> ParamType.String,
    "commit_date" -> ParamType.LocalDate,
    "scmid" -> ParamType.String,
    "user_id" -> ParamType.Int
  )

  override def updateParams = Params(params).withDateTime("committed_on").withDate("commit_date")
  override def updateForm = validation(updateParams,
    paramKey("repository_id") is required & numeric & intValue,
    paramKey("revision") is required & maxLength(255),
    paramKey("committer") is maxLength(255),
    paramKey("committed_on") is required & dateTimeFormat,
    paramKey("comments") is maxLength(2147483647),
    paramKey("commit_date") is dateFormat,
    paramKey("scmid") is maxLength(255),
    paramKey("user_id") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "repository_id" -> ParamType.Int,
    "revision" -> ParamType.String,
    "committer" -> ParamType.String,
    "committed_on" -> ParamType.DateTime,
    "comments" -> ParamType.String,
    "commit_date" -> ParamType.LocalDate,
    "scmid" -> ParamType.String,
    "user_id" -> ParamType.Int
  )

}
