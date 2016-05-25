/*
-- For H2 Database
create table wiki_redirects (
  id bigserial not null primary key,
  wiki_id int not null,
  title varchar(255),
  redirects_to varchar(255),
  created_on timestamp not null,
  redirects_to_wiki_id int not null
)

*/