/*
-- For H2 Database
create table wiki_contents (
  id bigserial not null primary key,
  page_id int not null,
  author_id int,
  text varchar(2147483647),
  comments varchar(1024),
  updated_on timestamp not null,
  version int not null
)

*/