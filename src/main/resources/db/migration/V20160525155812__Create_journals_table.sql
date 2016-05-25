/*
-- For H2 Database
create table journals (
  id bigserial not null primary key,
  journalized_id int not null,
  journalized_type varchar(30) not null,
  user_id int not null,
  notes varchar(65535),
  created_on timestamp not null,
  private_notes boolean not null
)

*/