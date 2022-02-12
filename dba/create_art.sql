drop table pieces;

create table pieces
(
    id   int not null auto_increment,
    alt_text    varchar(45),
    location varchar(254),
    category varchar(20),
    primary key (id)
) engine=InnoDB;

insert into pieces (alt_text, location, category)
    VALUE ('California Hills 2020', 'https://cdn.discordapp.com/attachments/941527220722225212/941527506199126116/california_hills_2020.png', 'painting');

insert into pieces (alt_text, location, category)
    VALUE ('Ship on stormy seas', 'https://cdn.discordapp.com/attachments/941527220722225212/941528614137110618/ship-on-the-water-doodle.jpg', 'painting');

insert into pieces (alt_text, location, category)
    VALUE ('Baseball pitch', 'https://cdn.discordapp.com/attachments/941527220722225212/941530173969408040/baseball.gif', 'animation');

insert into pieces (alt_text, location, category)
    VALUE ('Bouncing ball', 'https://cdn.discordapp.com/attachments/941527220722225212/941530460847235082/bouncy-boy.gif', 'animation');

insert into pieces (alt_text, location, category)
    VALUE ('Magnetic glove', 'https://cdn.discordapp.com/attachments/941527220722225212/941530613708632114/mag-glove.jpg', 'sketch');

insert into pieces (alt_text, location, category)
    VALUE ('Taser glove', 'https://cdn.discordapp.com/attachments/941527220722225212/941530797708554260/taser-gauntlet.jpg', 'sketch');

insert into pieces (alt_text, location, category)
    VALUE ('Futuristic cryopod', 'https://cdn.discordapp.com/attachments/941527220722225212/941531072565489784/cryopod.jpg', 'sketch');

insert into pieces (alt_text, location, category)
    VALUE ('Necronomicon from Evil Dead', 'https://cdn.discordapp.com/attachments/941527220722225212/941531336747929621/necronomicon-doodle.jpg', 'sketch');

insert into pieces (alt_text, location, category)
    VALUE ('Mountain cranes at sunset', 'https://cdn.discordapp.com/attachments/941527220722225212/941531788721926224/crane-v2.png', 'sketch');
