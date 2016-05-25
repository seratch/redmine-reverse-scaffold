/*
-- For H2 Database
create table custom_field_enumerations (
  id bigserial not null primary key,
  custom_field_id int not null,
  name varchar(255) not null,
  active boolean not null,
  position int not null
)

*/