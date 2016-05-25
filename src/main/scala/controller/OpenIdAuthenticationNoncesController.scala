package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.OpenIdAuthenticationNonce

class OpenIdAuthenticationNoncesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = OpenIdAuthenticationNonce
  override def resourcesName = "openIdAuthenticationNonces"
  override def resourceName = "openIdAuthenticationNonce"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("timestamp") is required & numeric & intValue,
    paramKey("server_url") is maxLength(255),
    paramKey("salt") is required & maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "timestamp" -> ParamType.Int,
    "server_url" -> ParamType.String,
    "salt" -> ParamType.String
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("timestamp") is required & numeric & intValue,
    paramKey("server_url") is maxLength(255),
    paramKey("salt") is required & maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "timestamp" -> ParamType.Int,
    "server_url" -> ParamType.String,
    "salt" -> ParamType.String
  )

}
