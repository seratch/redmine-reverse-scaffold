package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.MemberRole

class MemberRolesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = MemberRole
  override def resourcesName = "memberRoles"
  override def resourceName = "memberRole"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("member_id") is required & numeric & intValue,
    paramKey("role_id") is required & numeric & intValue,
    paramKey("inherited_from") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "member_id" -> ParamType.Int,
    "role_id" -> ParamType.Int,
    "inherited_from" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("member_id") is required & numeric & intValue,
    paramKey("role_id") is required & numeric & intValue,
    paramKey("inherited_from") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "member_id" -> ParamType.Int,
    "role_id" -> ParamType.Int,
    "inherited_from" -> ParamType.Int
  )

}
