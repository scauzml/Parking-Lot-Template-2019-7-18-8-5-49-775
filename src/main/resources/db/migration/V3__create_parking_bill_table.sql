create table parking_bill(
   bill_id varchar(64) not null PRIMARY KEY ,
   parking_lot_name varchar(32) UNIQUE ,
   car_number varchar(32) not null,
   start_time datetime not null,
   end_time datetime,
   status tinyint not null
);

