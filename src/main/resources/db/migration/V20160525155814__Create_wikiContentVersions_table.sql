/*
-- For H2 Database
create table wiki_content_versions (
  id bigserial not null primary key,
  wiki_content_id int not null,
  page_id int not null,
  author_id int,
  data binary,
  compression varchar(6),
  comments varchar(1024),
  updated_on timestamp not null,
  version int not null
)

*/