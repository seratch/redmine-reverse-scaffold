/*
-- For H2 Database
create table email_addresses (
  id bigserial not null primary key,
  user_id int not null,
  address varchar(255) not null,
  is_default boolean not null,
  notify boolean not null,
  created_on timestamp not null,
  updated_on timestamp not null
)

*/