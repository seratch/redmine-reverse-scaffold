/*
-- For H2 Database
create table users (
  id bigserial not null primary key,
  login varchar(255) not null,
  hashed_password varchar(40) not null,
  firstname varchar(30) not null,
  lastname varchar(255) not null,
  admin boolean not null,
  status int not null,
  last_login_on timestamp,
  language varchar(5),
  auth_source_id int,
  created_on timestamp,
  updated_on timestamp,
  type varchar(255),
  identity_url varchar(255),
  mail_notification varchar(255) not null,
  salt varchar(64),
  must_change_passwd boolean not null,
  passwd_changed_on timestamp
)

*/