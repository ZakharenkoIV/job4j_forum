insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$urJWkobn79fnJOenqM2c8OmFXQ17DfDzo809gmFuWOD7soE1Px5G2',
        (select id from authorities where authority = 'ROLE_ADMIN'));

insert into posts (topic, comment, user_id, order_of_addition)
values ('О чем этот форум?', 'Этот форум создан в учебных целях. Проект реализован по шаблону MVS и использует spring framework (boot, mvs, data, security). В качестве базы данных postgresql. Пользователи разделены по правам на "user" и "admin".

В проекте реализован функционал:
Регистрация;
Авторизация;
Добавление новой темы любым авторизованным пользователем;
Редактирование темы либо пользователем, добавившим её ранее, либо админом;
Удаление темы либо пользователем, добавившим её ранее, либо админом;
Добавление комментария к теме;
Редактирование/удаление своего комментария;
Выход из системы авторизованного пользователя.
Доступ ко всем страницам, кроме регистрации и авторизации, неавторизованным пользователям запрещён.', 1, 1);
insert into posts (topic, comment, user_id, order_of_addition)
values ('Правила форума.', 'В разработке...', 1, 1);
