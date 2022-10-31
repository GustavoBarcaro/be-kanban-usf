create table user_account (
    id serial primary key,
    username text not null,
    password text not null,
    is_admin boolean default false
);

create table task (
    id serial primary key,
    name text not null,
    time timestamp,
    due_time timestamp,
    description text,
    status text,
    row text,
    creator_id integer references user_account(id)
);

create table user_token (
    id serial primary key,
    id_user integer references user_account(id),
    token text
);
