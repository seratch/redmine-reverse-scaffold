package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.TimeEntry

class TimeEntriesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = TimeEntry
  override def resourcesName = "timeEntries"
  override def resourceName = "timeEntry"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDate("spent_on").withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("issue_id") is numeric & intValue,
    paramKey("hours") is required & floatValue,
    paramKey("comments") is maxLength(1024),
    paramKey("activity_id") is required & numeric & intValue,
    paramKey("spent_on") is required & dateFormat,
    paramKey("tyear") is required & numeric & intValue,
    paramKey("tmonth") is required & numeric & intValue,
    paramKey("tweek") is required & numeric & intValue,
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "user_id" -> ParamType.Int,
    "issue_id" -> ParamType.Int,
    "hours" -> ParamType.Float,
    "comments" -> ParamType.String,
    "activity_id" -> ParamType.Int,
    "spent_on" -> ParamType.LocalDate,
    "tyear" -> ParamType.Int,
    "tmonth" -> ParamType.Int,
    "tweek" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDate("spent_on").withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("issue_id") is numeric & intValue,
    paramKey("hours") is required & floatValue,
    paramKey("comments") is maxLength(1024),
    paramKey("activity_id") is required & numeric & intValue,
    paramKey("spent_on") is required & dateFormat,
    paramKey("tyear") is required & numeric & intValue,
    paramKey("tmonth") is required & numeric & intValue,
    paramKey("tweek") is required & numeric & intValue,
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "project_id" -> ParamType.Int,
    "user_id" -> ParamType.Int,
    "issue_id" -> ParamType.Int,
    "hours" -> ParamType.Float,
    "comments" -> ParamType.String,
    "activity_id" -> ParamType.Int,
    "spent_on" -> ParamType.LocalDate,
    "tyear" -> ParamType.Int,
    "tmonth" -> ParamType.Int,
    "tweek" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

}
