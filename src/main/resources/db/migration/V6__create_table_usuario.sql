create table usuario(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(255) not null,
    constraint pk_id_usuario primary key(id)
);