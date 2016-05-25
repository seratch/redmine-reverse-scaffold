package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.AuthSource

class AuthSourcesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = AuthSource
  override def resourcesName = "authSources"
  override def resourceName = "authSource"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("type") is required & maxLength(30),
    paramKey("name") is required & maxLength(60),
    paramKey("host") is maxLength(60),
    paramKey("port") is numeric & intValue,
    paramKey("account") is maxLength(255),
    paramKey("account_password") is maxLength(255),
    paramKey("base_dn") is maxLength(255),
    paramKey("attr_login") is maxLength(30),
    paramKey("attr_firstname") is maxLength(30),
    paramKey("attr_lastname") is maxLength(30),
    paramKey("attr_mail") is maxLength(30),
    paramKey("filter") is maxLength(65535),
    paramKey("timeout") is numeric & intValue
  )
  override def createFormStrongParameters = Seq(
    "type" -> ParamType.String,
    "name" -> ParamType.String,
    "host" -> ParamType.String,
    "port" -> ParamType.Int,
    "account" -> ParamType.String,
    "account_password" -> ParamType.String,
    "base_dn" -> ParamType.String,
    "attr_login" -> ParamType.String,
    "attr_firstname" -> ParamType.String,
    "attr_lastname" -> ParamType.String,
    "attr_mail" -> ParamType.String,
    "onthefly_register" -> ParamType.Boolean,
    "tls" -> ParamType.Boolean,
    "filter" -> ParamType.String,
    "timeout" -> ParamType.Int
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("type") is required & maxLength(30),
    paramKey("name") is required & maxLength(60),
    paramKey("host") is maxLength(60),
    paramKey("port") is numeric & intValue,
    paramKey("account") is maxLength(255),
    paramKey("account_password") is maxLength(255),
    paramKey("base_dn") is maxLength(255),
    paramKey("attr_login") is maxLength(30),
    paramKey("attr_firstname") is maxLength(30),
    paramKey("attr_lastname") is maxLength(30),
    paramKey("attr_mail") is maxLength(30),
    paramKey("filter") is maxLength(65535),
    paramKey("timeout") is numeric & intValue
  )
  override def updateFormStrongParameters = Seq(
    "type" -> ParamType.String,
    "name" -> ParamType.String,
    "host" -> ParamType.String,
    "port" -> ParamType.Int,
    "account" -> ParamType.String,
    "account_password" -> ParamType.String,
    "base_dn" -> ParamType.String,
    "attr_login" -> ParamType.String,
    "attr_firstname" -> ParamType.String,
    "attr_lastname" -> ParamType.String,
    "attr_mail" -> ParamType.String,
    "onthefly_register" -> ParamType.Boolean,
    "tls" -> ParamType.Boolean,
    "filter" -> ParamType.String,
    "timeout" -> ParamType.Int
  )

}
