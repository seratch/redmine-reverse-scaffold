package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Issue

class IssuesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Issue
  override def resourcesName = "issues"
  override def resourceName = "issue"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDate("due_date").withDateTime("created_on").withDateTime("updated_on").withDate("start_date").withDateTime("closed_on")
  override def createForm = validation(createParams,
    paramKey("tracker_id") is required & numeric & intValue,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("subject") is required & maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("due_date") is dateFormat,
    paramKey("category_id") is numeric & intValue,
    paramKey("status_id") is required & numeric & intValue,
    paramKey("assigned_to_id") is numeric & intValue,
    paramKey("priority_id") is required & numeric & intValue,
    paramKey("fixed_version_id") is numeric & intValue,
    paramKey("author_id") is required & numeric & intValue,
    paramKey("lock_version") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("start_date") is dateFormat,
    paramKey("done_ratio") is required & numeric & intValue,
    paramKey("estimated_hours") is floatValue,
    paramKey("parent_id") is numeric & intValue,
    paramKey("root_id") is numeric & intValue,
    paramKey("lft") is numeric & intValue,
    paramKey("rgt") is numeric & intValue,
    paramKey("closed_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "tracker_id" -> ParamType.Int,
    "project_id" -> ParamType.Int,
    "subject" -> ParamType.String,
    "description" -> ParamType.String,
    "due_date" -> ParamType.LocalDate,
    "category_id" -> ParamType.Int,
    "status_id" -> ParamType.Int,
    "assigned_to_id" -> ParamType.Int,
    "priority_id" -> ParamType.Int,
    "fixed_version_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "lock_version" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "start_date" -> ParamType.LocalDate,
    "done_ratio" -> ParamType.Int,
    "estimated_hours" -> ParamType.Float,
    "parent_id" -> ParamType.Int,
    "root_id" -> ParamType.Int,
    "lft" -> ParamType.Int,
    "rgt" -> ParamType.Int,
    "is_private" -> ParamType.Boolean,
    "closed_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDate("due_date").withDateTime("created_on").withDateTime("updated_on").withDate("start_date").withDateTime("closed_on")
  override def updateForm = validation(updateParams,
    paramKey("tracker_id") is required & numeric & intValue,
    paramKey("project_id") is required & numeric & intValue,
    paramKey("subject") is required & maxLength(255),
    paramKey("description") is maxLength(65535),
    paramKey("due_date") is dateFormat,
    paramKey("category_id") is numeric & intValue,
    paramKey("status_id") is required & numeric & intValue,
    paramKey("assigned_to_id") is numeric & intValue,
    paramKey("priority_id") is required & numeric & intValue,
    paramKey("fixed_version_id") is numeric & intValue,
    paramKey("author_id") is required & numeric & intValue,
    paramKey("lock_version") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("start_date") is dateFormat,
    paramKey("done_ratio") is required & numeric & intValue,
    paramKey("estimated_hours") is floatValue,
    paramKey("parent_id") is numeric & intValue,
    paramKey("root_id") is numeric & intValue,
    paramKey("lft") is numeric & intValue,
    paramKey("rgt") is numeric & intValue,
    paramKey("closed_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "tracker_id" -> ParamType.Int,
    "project_id" -> ParamType.Int,
    "subject" -> ParamType.String,
    "description" -> ParamType.String,
    "due_date" -> ParamType.LocalDate,
    "category_id" -> ParamType.Int,
    "status_id" -> ParamType.Int,
    "assigned_to_id" -> ParamType.Int,
    "priority_id" -> ParamType.Int,
    "fixed_version_id" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "lock_version" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "start_date" -> ParamType.LocalDate,
    "done_ratio" -> ParamType.Int,
    "estimated_hours" -> ParamType.Float,
    "parent_id" -> ParamType.Int,
    "root_id" -> ParamType.Int,
    "lft" -> ParamType.Int,
    "rgt" -> ParamType.Int,
    "is_private" -> ParamType.Boolean,
    "closed_on" -> ParamType.DateTime
  )

}
