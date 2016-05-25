package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.OpenIdAuthenticationAssociation

class OpenIdAuthenticationAssociationsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = OpenIdAuthenticationAssociation
  override def resourcesName = "openIdAuthenticationAssociations"
  override def resourceName = "openIdAuthenticationAssociation"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params)
  override def createForm = validation(createParams,
    paramKey("issued") is numeric & intValue,
    paramKey("lifetime") is numeric & intValue,
    paramKey("handle") is maxLength(255),
    paramKey("assoc_type") is maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "issued" -> ParamType.Int,
    "lifetime" -> ParamType.Int,
    "handle" -> ParamType.String,
    "assoc_type" -> ParamType.String,
    "server_url" -> ParamType.ByteArray,
    "secret" -> ParamType.ByteArray
  )

  override def updateParams = Params(params)
  override def updateForm = validation(updateParams,
    paramKey("issued") is numeric & intValue,
    paramKey("lifetime") is numeric & intValue,
    paramKey("handle") is maxLength(255),
    paramKey("assoc_type") is maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "issued" -> ParamType.Int,
    "lifetime" -> ParamType.Int,
    "handle" -> ParamType.String,
    "assoc_type" -> ParamType.String,
    "server_url" -> ParamType.ByteArray,
    "secret" -> ParamType.ByteArray
  )

}
