use testdb;

drop table pieces;
drop table categories;

create table categories
(
    id          int not null auto_increment,
    category    varchar(20) not null,
    primary key (id)
);

create unique index categories_id_uindex
    on categories (id);
create unique index categories_category_uindex
    on categories (category);

insert into categories (category) value ('painting');
insert into categories (category) value ('animation');
insert into categories (category) value ('sketch');

create table pieces
(
    id          int not null auto_increment,
    title       varchar(45) not null,
    location    varchar(254) not null,
    category_id    int not null,
    is_archived boolean not null default false,
    primary key (id),
    foreign key (category_id) references categories(id)
);

create unique index pieces_id_uindex
    on pieces (id);

insert into pieces (title, location, category_id)
    VALUE ('California Hills 2020', 'https://cdn.discordapp.com/attachments/941527220722225212/941527506199126116/california_hills_2020.png', 1);

insert into pieces (title, location, category_id)
    VALUE ('Ship on stormy seas', 'https://cdn.discordapp.com/attachments/941527220722225212/941528614137110618/ship-on-the-water-doodle.jpg', 1);

insert into pieces (title, location, category_id)
    VALUE ('Baseball pitch', 'https://cdn.discordapp.com/attachments/941527220722225212/941530173969408040/baseball.gif', 2);

insert into pieces (title, location, category_id)
    VALUE ('Bouncing ball', 'https://cdn.discordapp.com/attachments/941527220722225212/941530460847235082/bouncy-boy.gif', 2);

insert into pieces (title, location, category_id)
    VALUE ('Magnetic glove', 'https://cdn.discordapp.com/attachments/941527220722225212/941530613708632114/mag-glove.jpg', 3);

insert into pieces (title, location, category_id)
    VALUE ('Taser glove', 'https://cdn.discordapp.com/attachments/941527220722225212/941530797708554260/taser-gauntlet.jpg', 3);

insert into pieces (title, location, category_id)
    VALUE ('Futuristic cryopod', 'https://cdn.discordapp.com/attachments/941527220722225212/941531072565489784/cryopod.jpg', 3);

insert into pieces (title, location, category_id, is_archived)
    VALUE ('Necronomicon from Evil Dead', 'https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg', 3, true);

insert into pieces (title, location, category_id)
    VALUE ('Mountain cranes at sunset', 'https://cdn.discordapp.com/attachments/941527220722225212/941531788721926224/crane-v2.png', 3);