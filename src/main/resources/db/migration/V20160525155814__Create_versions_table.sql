/*
-- For H2 Database
create table versions (
  id bigserial not null primary key,
  project_id int not null,
  name varchar(255) not null,
  description varchar(255),
  effective_date date,
  created_on timestamp,
  updated_on timestamp,
  wiki_page_title varchar(255),
  status varchar(255),
  sharing varchar(255) not null
)

*/