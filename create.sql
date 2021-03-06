create table dom (id_dom uuid not null, ima_menzu boolean not null, naziv varchar(255), id_grad uuid, primary key (id_dom))
create table grad (id_grad uuid not null, naziv varchar(255), primary key (id_grad))
create table kategorija (id_kategorija uuid not null, ozn_kategorije varchar(255), primary key (id_kategorija))
create table obavijest (id_obavijest uuid not null, procitana boolean not null, tekst varchar(255), vrijeme date, primary key (id_obavijest))
create table oglas (id_oglas uuid not null, naslov varchar(255), objavljen date, opis varchar(255), primary key (id_oglas))
create table paviljon (id_paviljon uuid not null, naziv varchar(255), primary key (id_paviljon))
create table soba (id_soba uuid not null, broj int4 not null, broj_kreveta varchar(255), kat int4 not null, tip_kupaonice varchar(255), primary key (id_soba))
create table student (id_student uuid not null, ime varchar(255), jmbag varchar(10) not null, korisnicko_ime varchar(255) not null, hash_lozinke varchar(255) not null, mail varchar(255) not null, prezime varchar(255), primary key (id_student))
create table studentski_centar (id_sc uuid not null, naziv varchar(255), primary key (id_sc))
alter table if exists dom add constraint FK46be6pxy26lc9b90cpise10u5 foreign key (id_grad) references grad
