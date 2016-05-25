/*
-- For H2 Database
create table projects (
  id bigserial not null primary key,
  name varchar(255) not null,
  description varchar(65535),
  homepage varchar(255),
  is_public boolean not null,
  parent_id int,
  created_on timestamp,
  updated_on timestamp,
  identifier varchar(255),
  status int not null,
  lft int,
  rgt int,
  inherit_members boolean not null,
  default_version_id int
)

*/