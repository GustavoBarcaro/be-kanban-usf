CREATE EXTENSION pgcrypto;

create table user_account (
    id uuid primary key DEFAULT gen_random_uuid(),
    username text not null,
    password text not null,
    is_admin boolean default false
);

create table task (
    id uuid primary key DEFAULT gen_random_uuid(),
    name text not null,
    id_user uuid references user_account(id)
);

create table task_detail (
    id uuid primary key DEFAULT gen_random_uuid(),
    name text not null,
    time timestamp,
    due_time timestamp,
    description text,
    status text,
    row text,
    id_task uuid references task(id),
    id_user uuid references user_account(id)
);

create table user_token (
    id uuid primary key DEFAULT gen_random_uuid(),
    id_user uuid references user_account(id),
    token text
);

insert into user_account (id, username, password, is_admin)
values ('35cb41dd-3edb-4483-a1fe-fc315243d2f8', 'gustavo', 'fa6ad6d4525528338ba5b0ccd695bfd94c5f1b7bb1cf8257d7dd80f010c20c97', true);
