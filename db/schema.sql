CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)       NOT NULL unique,
    password     VARCHAR(100)      NOT NULL,
    enabled      boolean default true,
    authority_id int     default 1 not null references authorities (id)
);

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$urJWkobn79fnJOenqM2c8OmFXQ17DfDzo809gmFuWOD7soE1Px5G2',
        (select id from authorities where authority = 'ROLE_ADMIN'));

create table posts
(
    id          serial primary key,
    name        varchar(2000)               not null,
    description text,
    created     timestamp without time zone not null default now()
);

insert into posts (name)
values ('О чем этот форум?');
insert into posts (name)
values ('Правила форума.');
