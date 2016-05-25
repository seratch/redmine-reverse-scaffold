/*
-- For H2 Database
create table attachments (
  id bigserial not null primary key,
  container_id int,
  container_type varchar(30),
  filename varchar(255) not null,
  disk_filename varchar(255) not null,
  filesize bigint not null,
  content_type varchar(255),
  digest varchar(40) not null,
  downloads int not null,
  author_id int not null,
  created_on timestamp,
  description varchar(255),
  disk_directory varchar(255)
)

*/