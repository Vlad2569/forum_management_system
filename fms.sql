create or replace table tags
(
    tags_id int auto_increment
        primary key,
    name    varchar(42) not null,
    constraint tags_pk2
        unique (name)
);

create or replace table users
(
    user_id     int auto_increment
        primary key,
    first_name  varchar(32)            not null,
    last_name   varchar(32)            not null,
    email       varchar(256)           not null,
    username    varchar(64)            not null,
    password    binary(64)             not null,
    date_joined date default curdate() not null,
    constraint users_pk
        unique (email),
    constraint users_pk2
        unique (username)
);

create or replace table admins
(
    admin_id int auto_increment
        primary key,
    user_id  int not null,
    constraint admins_pk2
        unique (user_id),
    constraint admins_users_fk
        foreign key (user_id) references users (user_id)
);

create or replace table blocked_users
(
    blocked_user_id int auto_increment
        primary key,
    user_id         int                              not null,
    date_blocked    date default current_timestamp() not null,
    constraint blocked_users_pk2
        unique (user_id),
    constraint blocked_users_users_fk
        foreign key (user_id) references users (user_id)
);

create or replace table phone_numbers
(
    phone_number_id int auto_increment
        primary key,
    value           varchar(20) not null,
    admin_id        int         not null,
    constraint phone_numbers_admins_fk
        foreign key (admin_id) references admins (admin_id)
);

create or replace table posts
(
    post_id      int auto_increment
        primary key,
    title        varchar(64)                          not null,
    content      varchar(8192)                        not null,
    date_created datetime default current_timestamp() not null,
    user_id      int                                  not null,
    constraint posts_users_fk
        foreign key (user_id) references users (user_id)
);

create or replace table comments
(
    comment_id  int auto_increment
        primary key,
    post_id     int                                  not null,
    content     varchar(2048)                        not null,
    date_posted datetime default current_timestamp() not null,
    user_id     int                                  not null,
    constraint comments_posts_fk
        foreign key (post_id) references posts (post_id),
    constraint comments_users_fk
        foreign key (user_id) references users (user_id)
);

create or replace table likes
(
    like_id int auto_increment
        primary key,
    post_id int not null,
    user_id int not null,
    constraint likes_posts_fk
        foreign key (post_id) references posts (post_id),
    constraint likes_users_fk
        foreign key (user_id) references users (user_id)
);

create or replace table profile_photos
(
    profile_photo_id int auto_increment
        primary key,
    image            varbinary(256) not null,
    user_id          int            not null,
    constraint profile_photos_pk
        unique (user_id),
    constraint profile_photos_users_fk
        foreign key (user_id) references users (user_id)
);

create or replace table tags_posts
(
    tag_id  int not null,
    post_id int not null,
    constraint tags_posts_posts_fk
        foreign key (post_id) references posts (post_id),
    constraint tags_posts_tags_fk
        foreign key (tag_id) references tags (tags_id)
);

