/*
-- For H2 Database
create table journal_details (
  id bigserial not null primary key,
  journal_id int not null,
  property varchar(30) not null,
  prop_key varchar(30) not null,
  old_value varchar(65535),
  value varchar(65535)
)

*/