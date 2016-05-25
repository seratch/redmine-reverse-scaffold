/*
-- For H2 Database
create table custom_values (
  id bigserial not null primary key,
  customized_type varchar(30) not null,
  customized_id int not null,
  custom_field_id int not null,
  value varchar(65535)
)

*/