/*
-- For H2 Database
create table wiki_pages (
  id bigserial not null primary key,
  wiki_id int not null,
  title varchar(255) not null,
  created_on timestamp not null,
  protected boolean not null,
  parent_id int
)

*/