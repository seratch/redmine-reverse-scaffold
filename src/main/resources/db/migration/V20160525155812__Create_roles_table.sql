/*
-- For H2 Database
create table roles (
  id bigserial not null primary key,
  name varchar(30) not null,
  position int,
  assignable boolean,
  builtin int not null,
  permissions varchar(65535),
  issues_visibility varchar(30) not null,
  users_visibility varchar(30) not null,
  time_entries_visibility varchar(30) not null,
  all_roles_managed boolean not null
)

*/