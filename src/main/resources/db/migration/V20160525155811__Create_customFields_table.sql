/*
-- For H2 Database
create table custom_fields (
  id bigserial not null primary key,
  type varchar(30) not null,
  name varchar(30) not null,
  field_format varchar(30) not null,
  possible_values varchar(65535),
  regexp varchar(255),
  min_length int,
  max_length int,
  is_required boolean not null,
  is_for_all boolean not null,
  is_filter boolean not null,
  position int,
  searchable boolean,
  default_value varchar(65535),
  editable boolean,
  visible boolean not null,
  multiple boolean,
  format_store varchar(65535),
  description varchar(65535)
)

*/