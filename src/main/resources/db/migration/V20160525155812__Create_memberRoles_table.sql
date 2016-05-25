/*
-- For H2 Database
create table member_roles (
  id bigserial not null primary key,
  member_id int not null,
  role_id int not null,
  inherited_from int
)

*/