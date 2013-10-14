create database RankingFilmes;
use RankingFilmes;

create table filme (
    fil_id int not null auto_increment,
    fil_nome varchar(50) null,
    primary key(fil_id)
);
create table usuario(
    usu_id int not null auto_increment,
    usu_nome varchar(50) null,
    usu_email varchar(50) null,
    primary key(usu_id)
);
create table voto(
    vo_id int not null auto_increment,
    vo_data Date null,
    vo_hora Time null,
    fil_id int not null,
    usu_id int not null,
    primary key (vo_id),
    constraint fk_voto_fil_id
        foreign key (fil_id)
        references filme (fil_id)
        on delete no action
        on update cascade,
    constraint fk_voto_usu_id
        foreign key (usu_id)
        references usuario (usu_id)
        on delete no action
        on update cascade
);