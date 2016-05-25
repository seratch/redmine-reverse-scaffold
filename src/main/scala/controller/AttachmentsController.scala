package controller

import skinny._
import skinny.validator._
import _root_.controller._
import model.Attachment

class AttachmentsController extends SkinnyResource with ApplicationController {
  protectFromForgery()

  override def model = Attachment
  override def resourcesName = "attachments"
  override def resourceName = "attachment"

  override def resourcesBasePath = s"/${toSnakeCase(resourcesName)}"
  override def useSnakeCasedParamKeys = true

  override def viewsDirectoryPath = s"/${resourcesName}"

  override def createParams = Params(params).withDateTime("created_on")
  override def createForm = validation(createParams,
    paramKey("container_id") is numeric & intValue,
    paramKey("container_type") is maxLength(30),
    paramKey("filename") is required & maxLength(255),
    paramKey("disk_filename") is required & maxLength(255),
    paramKey("filesize") is required & numeric & longValue,
    paramKey("content_type") is maxLength(255),
    paramKey("digest") is required & maxLength(40),
    paramKey("downloads") is required & numeric & intValue,
    paramKey("author_id") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("description") is maxLength(255),
    paramKey("disk_directory") is maxLength(255)
  )
  override def createFormStrongParameters = Seq(
    "container_id" -> ParamType.Int,
    "container_type" -> ParamType.String,
    "filename" -> ParamType.String,
    "disk_filename" -> ParamType.String,
    "filesize" -> ParamType.Long,
    "content_type" -> ParamType.String,
    "digest" -> ParamType.String,
    "downloads" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "description" -> ParamType.String,
    "disk_directory" -> ParamType.String
  )

  override def updateParams = Params(params).withDateTime("created_on")
  override def updateForm = validation(updateParams,
    paramKey("container_id") is numeric & intValue,
    paramKey("container_type") is maxLength(30),
    paramKey("filename") is required & maxLength(255),
    paramKey("disk_filename") is required & maxLength(255),
    paramKey("filesize") is required & numeric & longValue,
    paramKey("content_type") is maxLength(255),
    paramKey("digest") is required & maxLength(40),
    paramKey("downloads") is required & numeric & intValue,
    paramKey("author_id") is required & numeric & intValue,
    paramKey("created_on") is dateTimeFormat,
    paramKey("description") is maxLength(255),
    paramKey("disk_directory") is maxLength(255)
  )
  override def updateFormStrongParameters = Seq(
    "container_id" -> ParamType.Int,
    "container_type" -> ParamType.String,
    "filename" -> ParamType.String,
    "disk_filename" -> ParamType.String,
    "filesize" -> ParamType.Long,
    "content_type" -> ParamType.String,
    "digest" -> ParamType.String,
    "downloads" -> ParamType.Int,
    "author_id" -> ParamType.Int,
    "created_on" -> ParamType.DateTime,
    "description" -> ParamType.String,
    "disk_directory" -> ParamType.String
  )

}
