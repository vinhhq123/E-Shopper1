create database OnlineShop1;

use OnlineShop1;

create table Setting(
settingId int AUTO_INCREMENT primary key,
settingType int,
settingValue varchar(200),
settingOrder int, 
settingStatus bit --   ACTIVE (1) / INACTIVE (0)
);

create table User(
  uid int AUTO_INCREMENT primary key,
  email varchar(200) ,
  fullname varchar(200),
  password varchar(200) ,
  title varchar(50), -- MR , MRS , MS
  gender bit , 
  phone varchar(10) ,
  address varchar(200),
  avatar blob ,
  accountStatus int references Setting(settingID),
  role int references Setting(settingID)
);

-- BANG SETTING TYPE TAM THOI CU DE DAY
create table SettingType(
settingTypeId int AUTO_INCREMENT primary key,
typeName varchar(200)  
);

INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('User Role');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Account Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Post Category');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Post Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Product Category');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Product Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Feedback Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Order Status');


-- ITERATION 2 BAT DAU TU DAY
CREATE TABLE Post (
  postId int AUTO_INCREMENT primary key,
  thumbnail text,
  postTitle varchar(200) ,
  postAuthor int references User(uid) ,
  postCategory int references Setting(settingId) ,
  featured varchar(200),
  status int references Setting(settingId),
  updatedDate date 
);

create table PostDetails(
postDetailID int AUTO_INCREMENT primary key,
postId int references Post(postId),
breifInformation text,
postContent text,
postDate date
);

CREATE TABLE Product (
  productId int AUTO_INCREMENT primary key,
  thumbnail longtext,
  title varchar(45),
  list_price float,
  sale_price float ,
  featured varchar(45),
  productStatus int references Setting(settingId),
  categoryId int references Setting(settingId),
  breif_information text,
  quantity int,
  updatedDate date,
  -- ratedStar int,
  salesId int references User(uid)
);

-- ITER SAU NAY

-- CREATE TABLE Cart (
-- cartid int AUTO_INCREMENT primary key,
--  cart_total_price float,
--  uid int references User(uid)
--  );


-- CREATE TABLE CartDetail (
--  cart_id int AUTO_INCREMENT primary key,
--  pid int references Product(productId),
--  quantity int,
--  toalPriceOfProduct float,
--  cartid int references Cart(cartid)
-- )  ;

-- create table Feedback(
-- feedbackId int AUTO_INCREMENT primary key,
-- customerId int references User(uid),
-- ratedStar int, -- max 5
-- productId int references Product(productId),
-- image blob,
-- feedbackStatus int references Setting(settingId),
-- feedbackContent text,
-- updatedDate datetime
-- );

-- create table Orders(
-- orderId int AUTO_INCREMENT primary key,
-- customerId int references User(uid),
-- totalCost float, -- of this Order
-- salesId int references User(uid),
-- orderStatus int references Setting(settingId)
-- );

-- create table OrderDetails(
-- orderDetailId int AUTO_INCREMENT primary key,
-- orderId int references Orders(orderid),
-- productId int references Product(productId),
-- quantity int,
-- totalCost float, -- of this product
-- orderDate datetime,
-- lastUpdated datetime
-- );

alter table User ADD CONSTRAINT fk_idCA2 FOREIGN KEY(role) references Setting(settingID) ;
alter table User ADD CONSTRAINT fk_idCA3 FOREIGN KEY(accountStatus) references Setting(settingID) ;
-- HET ITER 1

alter table Post ADD CONSTRAINT fk_idCA5 FOREIGN KEY(postAuthor) references User(uid) ;
alter table Post ADD CONSTRAINT fk_idCA6 FOREIGN KEY(postCategory) references Setting(settingId) ;
alter table Post ADD CONSTRAINT fk_idCA7 FOREIGN KEY(status) references Setting(settingId) ;
alter table PostDetails ADD CONSTRAINT fk_idCA8 FOREIGN KEY(postId) references Post(postId) ;
alter table Product ADD CONSTRAINT fk_idCA10 FOREIGN KEY(productStatus) references Setting(settingId) ;
alter table Product ADD CONSTRAINT fk_idCA11 FOREIGN KEY(categoryId) references Setting(settingId) ;
alter table Product ADD CONSTRAINT fk_idCA12 FOREIGN KEY(salesId) references User(uid) ;
-- HET ITER 2

-- alter table CartDetail ADD CONSTRAINT fk_idCA13 FOREIGN KEY(pid) references  Product(productId) ;
-- alter table Feedback ADD CONSTRAINT fk_idCA14 FOREIGN KEY(customerId) references  User(uid) ;
-- alter table Feedback ADD CONSTRAINT fk_idCA15 FOREIGN KEY(productId) references  Product(productId) ;
-- alter table Feedback ADD CONSTRAINT fk_idCA16 FOREIGN KEY(feedbackStatus) references  Setting(settingId) ;
-- alter table Orders ADD CONSTRAINT fk_idCA17 FOREIGN KEY(customerId) references User(uid) ;
-- alter table Orders ADD CONSTRAINT fk_idCA18 FOREIGN KEY(salesId) references User(uid) ;
-- alter table Orders ADD CONSTRAINT fk_idCA19 FOREIGN KEY(orderStatus) references  Setting(settingId) ;
-- alter table OrderDetails ADD CONSTRAINT fk_idCA20 FOREIGN KEY(orderId) references  Orders(orderid) ;
-- alter table OrderDetails ADD CONSTRAINT fk_idCA21 FOREIGN KEY(productId) references  Product(productId) ;


-- INSERI INTO SETTING
-- SETTING TYPE USER ROLE SETTING TYPE ID = 1
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'admin','1',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'manager','1',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'sales','1',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'marketing','1',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'customer','1',1);

-- SETTING TYPE ACCOUNT STATUS SETTING TYPE ID = 2
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(2,'registered','2',0);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(2,'verified','2',0);

-- SETTING TYPE POST CATEGORY SETTING TYPE ID = 3
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(3,'Marketing','3',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(3,'Promoted','3',1);

-- SETTING TYPE POST STATUS SETTING TYPE ID = 4
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(4,'Published','4',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(4,'Draft','4',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(4,'Deleted','4',0);

-- SETTING TYPE PRODUCT CATEGORY SETTING TYPE ID = 5
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(5,'Shoes','5',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(5,'Clothes','5',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(5,'Bags','5',1);

-- SETTING TYPE PRODUCT STATUS SETTING TYPE ID = 6
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(6,'Empty','6',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(6,'Stocking','6',1);

-- SETTING TYPE FEEDBACK STATUS SETTING TYPE ID = 7
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(7,'Published','7',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(7,'Deleted','7',0);

-- SETTING TYPE ORDER STATUS SETTING TYPE ID = 8
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Delivered','8',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Transporting','8',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Canceled','8',0);

-- INSERT EXTRA SETTING
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(2,'verified','2',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(2,'registered','2',1);
-- INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
-- VALUES(1,'marketing','None',0);

-- INSERT INTO USER
INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('vinh@gmail.com','Quang Vinh','Mr','admin',1,'0123456789','Hanoi',23,1);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('manager1@gmail.com','Lionel Messi','Mr','123',1,'0141233289','Paris',23,2);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('manager2@gmail.com','Cristiano Ronaldo','Mr','123',1,'0141233212','Manchester',23,2);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('sales1@gmail.com','Kylian Mbappe','Mr','123',1,'0141443289','Paris',23,3);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('kylie@fs.com','Kendall Jenner','Ms','123',0,'0141233200','Los Angeles',23,3);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('kanye@west.com','Kanye West','Mr','123',1,'0141233201','Los Angeles',7,4);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('kadashian@kk.com','Kim Kadashian','Ms','123',0,'0141233202','Los Angeles',23,4);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('harlaand@bvb.com','Erling Haaland','Mr','123',1,'0141231100','Dortmund',23,5);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('salad@eg.com','Mohamed Salah','Mr','123',1,'0231233200','Liverpool',23,5);

