package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.User

class UsersController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = User
  override def resourcesName = "users"
  override def resourceName = "user"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("last_login_on").withDateTime("created_on").withDateTime("updated_on").withDateTime("passwd_changed_on")
  override def createForm = validation(createParams,
    paramKey("login") is required & maxLength(255),
    paramKey("hashed_password") is required & maxLength(40),
    paramKey("firstname") is required & maxLength(30),
    paramKey("lastname") is required & maxLength(255),
    paramKey("status") is required & numeric & intValue,
    paramKey("last_login_on") is dateTimeFormat,
    paramKey("language") is maxLength(5),
    paramKey("auth_source_id") is numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("type") is maxLength(255),
    paramKey("identity_url") is maxLength(255),
    paramKey("mail_notification") is required & maxLength(255),
    paramKey("salt") is maxLength(64),
    paramKey("passwd_changed_on") is dateTimeFormat
  )
  override def createFormStrongParameters = Seq(
    "login" -> ParamType.String,
    "hashed_password" -> ParamType.String,
    "firstname" -> ParamType.String,
    "lastname" -> ParamType.String,
    "admin" -> ParamType.Boolean,
    "status" -> ParamType.Int,
    "last_login_on" -> ParamType.DateTime,
    "language" -> ParamType.String,
    "auth_source_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "type" -> ParamType.String,
    "identity_url" -> ParamType.String,
    "mail_notification" -> ParamType.String,
    "salt" -> ParamType.String,
    "must_change_passwd" -> ParamType.Boolean,
    "passwd_changed_on" -> ParamType.DateTime
  )

  override def updateParams = Params(params).withDateTime("last_login_on").withDateTime("created_on").withDateTime("updated_on").withDateTime("passwd_changed_on")
  override def updateForm = validation(updateParams,
    paramKey("login") is required & maxLength(255),
    paramKey("hashed_password") is required & maxLength(40),
    paramKey("firstname") is required & maxLength(30),
    paramKey("lastname") is required & maxLength(255),
    paramKey("status") is required & numeric & intValue,
    paramKey("last_login_on") is dateTimeFormat,
    paramKey("language") is maxLength(5),
    paramKey("auth_source_id") is numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("updated_on") is dateTimeFormat,
    paramKey("type") is maxLength(255),
    paramKey("identity_url") is maxLength(255),
    paramKey("mail_notification") is required & maxLength(255),
    paramKey("salt") is maxLength(64),
    paramKey("passwd_changed_on") is dateTimeFormat
  )
  override def updateFormStrongParameters = Seq(
    "login" -> ParamType.String,
    "hashed_password" -> ParamType.String,
    "firstname" -> ParamType.String,
    "lastname" -> ParamType.String,
    "admin" -> ParamType.Boolean,
    "status" -> ParamType.Int,
    "last_login_on" -> ParamType.DateTime,
    "language" -> ParamType.String,
    "auth_source_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "updated_on" -> ParamType.DateTime,
    "type" -> ParamType.String,
    "identity_url" -> ParamType.String,
    "mail_notification" -> ParamType.String,
    "salt" -> ParamType.String,
    "must_change_passwd" -> ParamType.Boolean,
    "passwd_changed_on" -> ParamType.DateTime
  )

}
