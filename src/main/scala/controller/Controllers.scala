package controller

import skinny._
import skinny.controller.AssetsController

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    workflows.mount(ctx)
    wikis.mount(ctx)
    wikiRedirects.mount(ctx)
    wikiPages.mount(ctx)
    wikiContents.mount(ctx)
    wikiContentVersions.mount(ctx)
    watchers.mount(ctx)
    versions.mount(ctx)
    users.mount(ctx)
    userPreferences.mount(ctx)
    trackers.mount(ctx)
    tokens.mount(ctx)
    timeEntries.mount(ctx)
    settings.mount(ctx)
    roles.mount(ctx)
    repositories.mount(ctx)
    queries.mount(ctx)
    projects.mount(ctx)
    openIdAuthenticationNonces.mount(ctx)
    openIdAuthenticationAssociations.mount(ctx)
    news.mount(ctx)
    messages.mount(ctx)
    members.mount(ctx)
    memberRoles.mount(ctx)
    journals.mount(ctx)
    journalDetails.mount(ctx)
    issues.mount(ctx)
    issueStatuses.mount(ctx)
    issueRelations.mount(ctx)
    issueCategories.mount(ctx)
    imports.mount(ctx)
    importItems.mount(ctx)
    enumerations.mount(ctx)
    enabledModules.mount(ctx)
    emailAddresses.mount(ctx)
    documents.mount(ctx)
    customValues.mount(ctx)
    customFields.mount(ctx)
    customFieldEnumerations.mount(ctx)
    comments.mount(ctx)
    changeets.mount(ctx)
    changes.mount(ctx)
    boards.mount(ctx)
    authSources.mount(ctx)
    attachments.mount(ctx)
    root.mount(ctx)
    AssetsController.mount(ctx)
  }

  object root extends RootController with Routes {
    val indexUrl = get("/?")(index).as('index)
  }

  object attachments extends _root_.controller.AttachmentsController with Routes {
  }

  object authSources extends _root_.controller.AuthSourcesController with Routes {
  }

  object boards extends _root_.controller.BoardsController with Routes {
  }

  object changes extends _root_.controller.ChangesController with Routes {
  }

  object changeets extends _root_.controller.ChangeetsController with Routes {
  }

  object comments extends _root_.controller.CommentsController with Routes {
  }

  object customFieldEnumerations extends _root_.controller.CustomFieldEnumerationsController with Routes {
  }

  object customFields extends _root_.controller.CustomFieldsController with Routes {
  }

  object customValues extends _root_.controller.CustomValuesController with Routes {
  }

  object documents extends _root_.controller.DocumentsController with Routes {
  }

  object emailAddresses extends _root_.controller.EmailAddressesController with Routes {
  }

  object enabledModules extends _root_.controller.EnabledModulesController with Routes {
  }

  object enumerations extends _root_.controller.EnumerationsController with Routes {
  }

  object importItems extends _root_.controller.ImportItemsController with Routes {
  }

  object imports extends _root_.controller.ImportsController with Routes {
  }

  object issueCategories extends _root_.controller.IssueCategoriesController with Routes {
  }

  object issueRelations extends _root_.controller.IssueRelationsController with Routes {
  }

  object issueStatuses extends _root_.controller.IssueStatusesController with Routes {
  }

  object issues extends _root_.controller.IssuesController with Routes {
  }

  object journalDetails extends _root_.controller.JournalDetailsController with Routes {
  }

  object journals extends _root_.controller.JournalsController with Routes {
  }

  object memberRoles extends _root_.controller.MemberRolesController with Routes {
  }

  object members extends _root_.controller.MembersController with Routes {
  }

  object messages extends _root_.controller.MessagesController with Routes {
  }

  object news extends _root_.controller.NewsController with Routes {
  }

  object openIdAuthenticationAssociations extends _root_.controller.OpenIdAuthenticationAssociationsController with Routes {
  }

  object openIdAuthenticationNonces extends _root_.controller.OpenIdAuthenticationNoncesController with Routes {
  }

  object projects extends _root_.controller.ProjectsController with Routes {
  }

  object queries extends _root_.controller.QueriesController with Routes {
  }

  object repositories extends _root_.controller.RepositoriesController with Routes {
  }

  object roles extends _root_.controller.RolesController with Routes {
  }

  object settings extends _root_.controller.SettingsController with Routes {
  }

  object timeEntries extends _root_.controller.TimeEntriesController with Routes {
  }

  object tokens extends _root_.controller.TokensController with Routes {
  }

  object trackers extends _root_.controller.TrackersController with Routes {
  }

  object userPreferences extends _root_.controller.UserPreferencesController with Routes {
  }

  object users extends _root_.controller.UsersController with Routes {
  }

  object versions extends _root_.controller.VersionsController with Routes {
  }

  object watchers extends _root_.controller.WatchersController with Routes {
  }

  object wikiContentVersions extends _root_.controller.WikiContentVersionsController with Routes {
  }

  object wikiContents extends _root_.controller.WikiContentsController with Routes {
  }

  object wikiPages extends _root_.controller.WikiPagesController with Routes {
  }

  object wikiRedirects extends _root_.controller.WikiRedirectsController with Routes {
  }

  object wikis extends _root_.controller.WikisController with Routes {
  }

  object workflows extends _root_.controller.WorkflowsController with Routes {
  }

}
