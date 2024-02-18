create table if not exists usuario(
          id bigint not null auto_increment,
          nome varchar(70) not null,
          cargo varchar(70) not null,
          email varchar(70) not null,
          senha varchar(70) not null,
          role varchar(70) not null,
          primary key(id)
);  

