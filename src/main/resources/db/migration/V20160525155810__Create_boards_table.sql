/*
-- For H2 Database
create table boards (
  id bigserial not null primary key,
  project_id int not null,
  name varchar(255) not null,
  description varchar(255),
  position int,
  topics_count int not null,
  messages_count int not null,
  last_message_id int,
  parent_id int
)

*/