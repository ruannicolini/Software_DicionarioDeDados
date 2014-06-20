CREATE DATABASE dicionario;
use dicionario;
create table bd (idBd int primary key auto_increment, 
				nome varchar(45),
				descricao text
);

create table tabela(idTabela int primary key auto_increment, 
					nome varchar(45),
					descricao text,
					tipo varchar(30),
					idBd int,
					foreign key(idBd) references bd(idBd)
);

create table campo(idCampo int primary key auto_increment, idTabela int,
					nome varchar(45),
					tipo varchar(45),
					descricao text,
					foreign key(idTabela) references tabela(idTabela)
);


show table status;

describe campo;

use dicionario;
select *from BD;
select *from Tabela;
select * from campo;