/*
-- For H2 Database
create table issues (
  id bigserial not null primary key,
  tracker_id int not null,
  project_id int not null,
  subject varchar(255) not null,
  description varchar(65535),
  due_date date,
  category_id int,
  status_id int not null,
  assigned_to_id int,
  priority_id int not null,
  fixed_version_id int,
  author_id int not null,
  lock_version int not null,
  created_on timestamp,
  updated_on timestamp,
  start_date date,
  done_ratio int not null,
  estimated_hours float,
  parent_id int,
  root_id int,
  lft int,
  rgt int,
  is_private boolean not null,
  closed_on timestamp
)

*/