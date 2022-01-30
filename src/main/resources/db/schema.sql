CREATE TABLE if not exists authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE if not exists users
(
    id           serial primary key,
    username     VARCHAR(50)       NOT NULL unique,
    password     VARCHAR(100)      NOT NULL,
    enabled      boolean default true,
    authority_id int     default 1 not null references authorities (id)
);

create table if not exists posts
(
    id                serial primary key,
    topic             varchar(2000)                             not null,
    comment           text,
    created           timestamp without time zone default now() not null,
    order_of_addition int                                       not null,
    user_id           int                                       not null references users (id)
);

ALTER TABLE posts
    DROP CONSTRAINT IF EXISTS UQ_TOPIC_ORDER;

ALTER TABLE posts
    ADD CONSTRAINT UQ_TOPIC_ORDER UNIQUE (topic, order_of_addition);

