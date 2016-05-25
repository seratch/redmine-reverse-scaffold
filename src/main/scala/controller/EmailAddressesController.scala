package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.EmailAddress

class EmailAddressesController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = EmailAddress
  override def resourcesName = "emailAddresses"
  override def resourceName = "emailAddress"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def createForm = validation(createParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("address") is required & maxLength(255),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "address" -> ParamType.String,
    "is_default" -> ParamType.Boolean,
    "notify" -> ParamType.Boolean,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("created_on").withDateTime("updated_on")
  override def updateForm = validation(updateParams,
    paramKey("user_id") is required & numeric & intValue,
    paramKey("address") is required & maxLength(255),
    paramKey("created_on") is required & dateTimeFormat,
    paramKey("updated_on") is required & dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "user_id" -> ParamType.Int,
    "address" -> ParamType.String,
    "is_default" -> ParamType.Boolean,
    "notify" -> ParamType.Boolean,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime
  )

}
