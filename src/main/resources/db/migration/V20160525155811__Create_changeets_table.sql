/*
-- For H2 Database
create table changesets (
  id bigserial not null primary key,
  repository_id int not null,
  revision varchar(255) not null,
  committer varchar(255),
  committed_on timestamp not null,
  comments varchar(2147483647),
  commit_date date,
  scmid varchar(255),
  user_id int
)

*/