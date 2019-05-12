create schema lifebank_db;

create table lifebank_db.usuario (
   usr_id               serial                 not null,
   usr_usuario                 VARCHAR(25)        not null,
   usr_password				VARCHAR(500)		not  null,
   usr_nombre  VARCHAR(50)		not  null,
   usr_apellido VARCHAR(50)		not  null,
   usr_correo VARCHAR(50)		not  null,
   usr_estado CHAR	(1)        not null,
   usr_conteo_fallido int DEFAULT 0,
   usr_CREATED_BY varchar(25) NOT NULL  DEFAULT USER,
   usr_CREATED_DATE timestamp NOT NULL DEFAULT NOW(),
   usr_MODIFIED_BY varchar(25) NULL,
   usr_MODIFIED_DATE timestamp NULL,
   constraint PK_USUARIO primary key (usr_id)
);

COMMENT ON TABLE lifebank_db.usuario IS 'Informacion de las crendenciales de los usuarios' ;

INSERT INTO lifebank_db.usuario (usr_id, usr_usuario, usr_password, usr_nombre, usr_apellido, usr_correo,urs_estado,urs_conteo_fallido, usr_created_by, usr_created_date, usr_modified_by, usr_modified_date) VALUES(1, 'wcenteno', '04e85978d5ed597ecfaf4ed222aaab05ca4c32b2898532ce0fe7cdf2cfc1dd5f502e40fdf0a205f47e22d422e5146b2f893c5817ef5e3da0ace6c72941f84040', 'Wilfredo', 'Centeno', 'wilfredo@gmail.com','A',0, 'postgres', '2019-05-09 17:16:48.878', NULL, NULL);

create table lifebank_db.usuario_token(
tkn_id  serial not null,
tkn_token VARCHAR(500) not null,
tkn_usr_id int not null,
tkn_date_expiration timestamp NOT NULL,
tkn_created_date timestamp not null default now(),
constraint PK_USUARIO_TOKEN primary key (tkn_id),
constraint FK_USUARIO foreign key(tkn_usr_id) references lifebank_db.usuario (usr_id)
);

COMMENT ON TABLE lifebank_db.usuario_token IS 'Informacion de las sessiones de los usuarios' ;

create table lifebank_db.tipo_producto(
tip_id serial  not null,
tip_nombre varchar(100) not null,
constraint PK_TIPO_CUENTAS primary key (tip_id)
);

COMMENT ON TABLE lifebank_db.tipo_producto IS 'Tipo de cuentas en bancos';

INSERT into lifebank_db.tipo_producto(tip_id,tip_nombre) values (1,'Cuentas Personales Ahorro');
INSERT into lifebank_db.tipo_producto(tip_id,tip_nombre) values (2,'Tarjeta de Creditos');
INSERT into lifebank_db.tipo_producto(tip_id,tip_nombre) values (3,'Prestamos Personal');

create table lifebank_db.cuentas_bancarias(
cub_id varchar(50) not null,
cub_nombre varchar(100) not null,
cub_tip_id int not null,
cub_usr_id int not null,
cub_saldo  decimal(18,2),
cub_estado char(1) default 'A',
cub_created_date timestamp DEFAULT now(),
constraint PK_CUENTA primary key (cub_id),
constraint FK_CUE_TIPO_CUENTA foreign key(cub_tip_id) references lifebank_db.tipo_producto (tip_id),
constraint FK_CUE_USUARIO foreign key(cub_usr_id) references lifebank_db.usuario (usr_id)
);

insert into lifebank_db.cuentas_bancarias (cub_id,cub_nombre,cub_tip_id,cub_usr_id,cub_saldo) values ('001','Cuenta de Ahorro 1',1,1,200);
insert into lifebank_db.cuentas_bancarias (cub_id,cub_nombre,cub_tip_id,cub_usr_id,cub_saldo) values ('002','Cuenta de Ahorro 2',1,1,500);

create table lifebank_db.prestamos_bancarias(
prb_id varchar(50) not null,
prb_nombre varchar(100) not null,
prb_tip_id int not null,
prb_usr_id int not null,
prb_saldo  decimal(18,2),
prb_interes_mensual decimal(18,2),
prb_total_interes decimal(18,2),
prb_periodo_meses int not null,
prb_estado char(1) default 'A',
prb_fecha_inicio timestamp,
prb_created_date timestamp DEFAULT now(),
constraint PK_PREST_BANCA primary key (prb_id),
constraint FK__PRB_TIPO_CUENTA foreign key(prb_tip_id) references lifebank_db.tipo_producto (tip_id),
constraint FK_PRB_USUARIO foreign key(prb_usr_id) references lifebank_db.usuario (usr_id)
);

insert into lifebank_db.prestamos_bancarias (prb_id,prb_nombre,prb_tip_id,prb_usr_id,prb_saldo,prb_periodo_meses,prb_interes_mensual,prb_total_interes,prb_fecha_inicio) values ('001','Prestamos Personal 1',3,1,1500,36,2.5,550,'2019-05-09');

create table lifebank_db.tarjeta_creditos(
tc_id varchar(50) not null,
tc_nombre varchar(100) not null,
tc_tip_id int not null,
tc_usr_id int not null,
tc_limite decimal(18,2),
tc_saldo  decimal(18,2),
tc_costo_membresia int not null,
tc_interes_mensual decimal(18,2),
tc_total_interes decimal(18,2),
tc_estado char(1) DEFAULT 'A',
tc_fecha_corte int,
tc_fecha_pago int,
tc_fecha_cancelacion timestamp,
tc_created_date timestamp DEFAULT now(),
constraint PK_TARJETA_CREDITO primary key (tc_id),
constraint FK_TC_TIPO_CUENTA foreign key(tc_tip_id) references lifebank_db.tipo_producto (tip_id),
constraint FK_TC_USUARIO foreign key(tc_usr_id) references lifebank_db.usuario (usr_id)
);

insert into lifebank_db.tarjeta_creditos(tc_id,tc_nombre,tc_tip_id,tc_usr_id,tc_limite,tc_saldo,tc_costo_membresia,tc_interes_mensual,tc_total_interes,tc_fecha_corte,tc_fecha_pago) values ('001','Tarjeta de Cretito clsica',2,1,700,700,25,3.5,0,4,15);

create table lifebank_db.tipo_transaccion(
trs_id serial not null,
trs_nombre varchar(50) not null,
constraint PK_TIPO_TRANS primary key (trs_id)
);

insert into lifebank_db.tipo_transaccion (trs_id, trs_nombre) values(1,'Abono');
insert into lifebank_db.tipo_transaccion (trs_id, trs_nombre) values(2,'Cargo');

create table lifebank_db.transacciones(
tra_id serial not null,
tra_id_cuenta varchar(50) not null,
tra_cantidad  decimal(18,2) not null,
tra_fecha  timestamp not null,
tra_tipo   int not null,
tra_referencia varchar(50) not null,
tra_fecha_creacion timestamp default now(),
constraint PK_TRANS primary key (tra_id)
);