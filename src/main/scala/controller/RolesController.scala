package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Role

class RolesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Role
  override def resourcesName = "roles"
  override def resourceName = "role"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("builtin") is required & numeric & intValue,
    paramKey("permissions") is maxLength(65535),
    paramKey("issues_visibility") is required & maxLength(30),
    paramKey("users_visibility") is required & maxLength(30),
    paramKey("time_entries_visibility") is required & maxLength(30)
  )
  override def createFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "position" -> ParamType.Int,
    "assignable" -> ParamType.Boolean,
    "builtin" -> ParamType.Int,
    "permissions" -> ParamType.String,
    "issues_visibility" -> ParamType.String,
    "users_visibility" -> ParamType.String,
    "time_entries_visibility" -> ParamType.String,
    "all_roles_managed" -> ParamType.Boolean
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("name") is required & maxLength(30),
    paramKey("position") is numeric & intValue,
    paramKey("builtin") is required & numeric & intValue,
    paramKey("permissions") is maxLength(65535),
    paramKey("issues_visibility") is required & maxLength(30),
    paramKey("users_visibility") is required & maxLength(30),
    paramKey("time_entries_visibility") is required & maxLength(30)
  )
  override def updateFormStrongParameters = Seq(
    "name" -> ParamType.String,
    "position" -> ParamType.Int,
    "assignable" -> ParamType.Boolean,
    "builtin" -> ParamType.Int,
    "permissions" -> ParamType.String,
    "issues_visibility" -> ParamType.String,
    "users_visibility" -> ParamType.String,
    "time_entries_visibility" -> ParamType.String,
    "all_roles_managed" -> ParamType.Boolean
  )

}
