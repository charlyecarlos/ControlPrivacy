drop table usu;
drop table tipousuario;
create table tipousuario(
 tipo decimal(1) primary key,
 descripcion varchar(15) not null
 );
insert into tipousuario values (1,'Administrador');
insert into tipousuario values (2,'Usuario');
create table usu(
 usuario varchar(15) primary key,
 pwd  varchar(32) not null,
 tipo     decimal(1) not null ,
 email  varchar(50)not null,
 pregunta varchar(50)not null,
 recordatorio varchar(25)not null,
 accesosfallidos number(1) check(accesosfallidos in(1,2,3,0)),
 bloqueado varchar(1) check(bloqueado in('S','N')),
 activo varchar(1) check(activo in('S','N'))
);
alter table usu add constraint fk_tipo foreign key(tipo) references tipousuario(tipo);
insert into usu values('supermiguel','super',1,'angel@podemos.com','¿Tu equipo mega fhasion?','el madrid',0,'N','S');
insert into usu values('puf','puf',2,'puf@podemos.com','¿Tu equipo Supermega fhasion?','SuperReal',0,'N','S');
commit;