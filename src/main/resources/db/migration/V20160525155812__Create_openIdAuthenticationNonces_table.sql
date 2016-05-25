/*
-- For H2 Database
create table open_id_authentication_nonces (
  id bigserial not null primary key,
  timestamp int not null,
  server_url varchar(255),
  salt varchar(255) not null
)

*/