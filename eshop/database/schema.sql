create database eshop;
use eshop; 

create table customers(
    customer_id int auto_increment primary key,
    name varchar(32) not null,
    address varchar(128) not null, 
    email varchar(128) not null
);

/* insert into customers (name, address, email)
VALUES 
("fred", "201 Cobblestone Lane", "fredflintstone@bedrock.com"),
("sherlock", "221B Baker Street, London", "sherlock@consultingdetective.org"),
("spongebob", "124 Conch Street, Bikini Bottom", "spongebob@yahoo.com"),
("jessica", "698 Candlewood Land, Cabot Cove", "fletcher@gmail.com"),
("dursley", "4 Privet Drive, Little Whinging, Surrey", "dursley@gmail.com"); */

create table orders(
    order_id char(8) primary key,
    name varchar(32) not null, 
    address varchar(128) not null, 
    email varchar(128) not null, 
    order_date date not null
);

create table lineitems (
    lineitem_id int auto_increment primary key,
    item text not null,
    quantity int not null,
    order_id char(8) not null,
    foreign key (order_id) references orders(order_id)
);

create table order_status(
    order_status_id int auto_increment primary key, 
    order_id char(8) not null,
    delivery_id varchar(128) not null,
    status enum("pending", "dispatched") not null,
    status_update timestamp not null,
    foreign key (order_id) references orders(order_id)
);

