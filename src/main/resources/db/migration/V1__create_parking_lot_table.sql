create table PARKING_LOT(
   id varchar(64) not null PRIMARY KEY ,
   name varchar(32) UNIQUE ,
   capicity int not null,
   position varchar(64) not null
);

