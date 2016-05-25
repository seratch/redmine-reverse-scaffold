/*
-- For H2 Database
create table open_id_authentication_associations (
  id bigserial not null primary key,
  issued int,
  lifetime int,
  handle varchar(255),
  assoc_type varchar(255),
  server_url binary,
  secret binary
)

*/