use projectsdb;

drop table pieces;
drop table categories;
drop table users;

create table users
(
    id                 int auto_increment,
    email              varchar(254)          not null,
    password           varchar(254)          not null,
    view_permission    boolean default true  not null,
    archive_permission boolean default false not null,
    remove_permission  boolean default false not null,
    publish_permission boolean default false not null,
    constraint users_pk
        primary key (id)
);

create unique index users_id_uindex
    on users (id);
create unique index users_email_uindex
    on users (email);

insert into users (email, password) values ('qtgiebel@gmail.com', 'password');
insert into users (email, password) values ('qgiebel@madisoncollege.edu', 'password');

create table categories
(
    id   int auto_increment,
    name varchar(20) not null,
    constraint categories_pk
        primary key (id)
);

insert into categories (name) value ('other');
insert into categories (name) value ('painting');
insert into categories (name) value ('animation');
insert into categories (name) value ('sketch');

create table pieces
(
    id          int auto_increment
        primary key,
    title       varchar(45)          not null,
    location    varchar(254)         not null,
    is_archived tinyint(1) default 0 not null,
    category_id int        default 1 not null,
    constraint category_fk
        foreign key (category_id) references categories (id)
            on delete cascade
);

insert into pieces (title, location, category_id)
    VALUE ('California Hills 2020', 'https://cdn.discordapp.com/attachments/941527220722225212/941527506199126116/california_hills_2020.png', 2);

insert into pieces (title, location, category_id)
    VALUE ('Ship on stormy seas', 'https://cdn.discordapp.com/attachments/941527220722225212/941528614137110618/ship-on-the-water-doodle.jpg', 2);

insert into pieces (title, location, category_id)
    VALUE ('Baseball pitch', 'https://cdn.discordapp.com/attachments/941527220722225212/941530173969408040/baseball.gif', 3);

insert into pieces (title, location, category_id)
    VALUE ('Bouncing ball', 'https://cdn.discordapp.com/attachments/941527220722225212/941530460847235082/bouncy-boy.gif', 3);

insert into pieces (title, location, category_id)
    VALUE ('Magnetic glove', 'https://cdn.discordapp.com/attachments/941527220722225212/941530613708632114/mag-glove.jpg', 4);

insert into pieces (title, location, category_id)
    VALUE ('Taser glove', 'https://cdn.discordapp.com/attachments/941527220722225212/941530797708554260/taser-gauntlet.jpg', 4);

insert into pieces (title, location, category_id)
    VALUE ('Futuristic cryopod', 'https://cdn.discordapp.com/attachments/941527220722225212/941531072565489784/cryopod.jpg', 4);

insert into pieces (title, location, category_id, is_archived)
    VALUE ('Necronomicon from Evil Dead', 'https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg', 4, true);

insert into pieces (title, location, category_id)
    VALUE ('Mountain cranes at sunset', 'https://cdn.discordapp.com/attachments/941527220722225212/941531788721926224/crane-v2.png', 2);
