/*
-- For H2 Database
create table messages (
  id bigserial not null primary key,
  board_id int not null,
  parent_id int,
  subject varchar(255) not null,
  content varchar(65535),
  author_id int,
  replies_count int not null,
  last_reply_id int,
  created_on timestamp not null,
  updated_on timestamp not null,
  locked boolean,
  sticky int
)

*/