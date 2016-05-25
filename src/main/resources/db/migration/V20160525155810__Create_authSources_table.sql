/*
-- For H2 Database
create table auth_sources (
  id bigserial not null primary key,
  type varchar(30) not null,
  name varchar(60) not null,
  host varchar(60),
  port int,
  account varchar(255),
  account_password varchar(255),
  base_dn varchar(255),
  attr_login varchar(30),
  attr_firstname varchar(30),
  attr_lastname varchar(30),
  attr_mail varchar(30),
  onthefly_register boolean not null,
  tls boolean not null,
  filter varchar(65535),
  timeout int
)

*/