package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Token

class TokensController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Token
  override def resourcesName = "tokens"
  override def resourceName = "token"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("action") is required & maxLength(30),
    paramKey("value") is required & maxLength(40),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "action" -> ParamType.String,
    "value" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("action") is required & maxLength(30),
    paramKey("value") is required & maxLength(40),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "action" -> ParamType.String,
    "value" -> ParamType.String,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

}
