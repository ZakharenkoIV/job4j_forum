insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$urJWkobn79fnJOenqM2c8OmFXQ17DfDzo809gmFuWOD7soE1Px5G2',
        (select id from authorities where authority = 'ROLE_ADMIN'));

insert into posts (topic, user_id, order_of_addition)
values ('О чем этот форум?', 1, 1);
insert into posts (topic, user_id, order_of_addition)
values ('Правила форума.', 1, 1);
