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
  title varchar(50), -- MR , MRS , MS load tu setting , chuyen thanh int
  gender bit , 
  phone varchar(10) ,
  address varchar(200),
  avatar longblob ,
  accountStatus int references Setting(settingID),
  role int references Setting(settingID)
);

-- ITERATION 2 BAT DAU TU DAY
CREATE TABLE Post (
  postId int AUTO_INCREMENT primary key,
  thumbnail longblob,
  postTitle varchar(200) ,
  breifInformation text,
  postContent text,
  postAuthor int references User(uid) ,
  postCategory int references Setting(settingId) ,
  featured varchar(200),
  status int references Setting(settingId),
  postdDate date,
  updatedDate date,
  featuredPost int
  -- views int
);

CREATE TABLE Product (
  productId int AUTO_INCREMENT primary key,
  thumbnail longblob,
  title varchar(45),
  list_price float,
  sale_price float ,
  featured varchar(45), -- doi thanh bit : 1 = on, 0 = off
  productStatus int references Setting(settingId),
  categoryId int references Setting(settingId),
  breif_information text,
  quantity int,
  updatedDate date,
  -- ratedStar int,
  salesId int references User(uid),
  featuredProduct bit
  -- views int
);

 -- ITER 3 TU DAY
create table Orders(
orderId int AUTO_INCREMENT primary key,
customerId int references User(uid),
totalCost float, -- of this Order
salesId int references User(uid),
orderStatus int references Setting(settingId),
orderDate date,
salesNote text,
lastUpdated date
);

create table OrderDetails(
orderDetailId int AUTO_INCREMENT primary key,
orderId int references Orders(orderid),
productId int references Product(productId),
quantity int,
subCost float, -- of this product
lastUpdated datetime
);

create table Feedback(
feedbackId int AUTO_INCREMENT primary key,
customerId int references User(uid),
ratedStar int, -- max 5
productId int references Product(productId),
image blob,
feedbackStatus int references Setting(settingId),
feedbackContent text,
updatedDate datetime,
title varchar(200),
note varchar(200)
);

CREATE TABLE slider (
  slider_id INT NOT NULL AUTO_INCREMENT,
  s_title NVARCHAR(200) NULL,
  s_imgage LONGBLOB NULL,
  s_backlink NVARCHAR(200) NULL,
  s_status INT NULL,
  s_notes NVARCHAR(200) NULL,
  PRIMARY KEY (slider_id));
  

-- ITER SAU NAY

 CREATE TABLE Cart (
cartid int AUTO_INCREMENT primary key,
cart_total_price float,
uid int references User(uid)
);


 CREATE TABLE CartDetail (
cartDetailId int AUTO_INCREMENT primary key,
pid int references Product(productId),
quantity int,
toalPriceOfProduct float,
cartid int references Cart(cartid)
)  ;





alter table User ADD CONSTRAINT fk_idCA2 FOREIGN KEY(role) references Setting(settingID) ;
alter table User ADD CONSTRAINT fk_idCA3 FOREIGN KEY(accountStatus) references Setting(settingID) ;
-- HET ITER 1

alter table Post ADD CONSTRAINT fk_idCA5 FOREIGN KEY(postAuthor) references User(uid) ;
alter table Post ADD CONSTRAINT fk_idCA6 FOREIGN KEY(postCategory) references Setting(settingId) ;
alter table Post ADD CONSTRAINT fk_idCA7 FOREIGN KEY(status) references Setting(settingId) ;
-- alter table PostDetails ADD CONSTRAINT fk_idCA8 FOREIGN KEY(postId) references Post(postId) ;
alter table Product ADD CONSTRAINT fk_idCA10 FOREIGN KEY(productStatus) references Setting(settingId) ;
alter table Product ADD CONSTRAINT fk_idCA11 FOREIGN KEY(categoryId) references Setting(settingId) ;
alter table Product ADD CONSTRAINT fk_idCA12 FOREIGN KEY(salesId) references User(uid) ;
-- alter table User ADD CONSTRAINT fk_idCA21 FOREIGN KEY(title) references  Setting(settingType) ;
-- HET ITER 2

alter table Feedback ADD CONSTRAINT fk_idCA14 FOREIGN KEY(customerId) references  User(uid) ;
alter table Feedback ADD CONSTRAINT fk_idCA15 FOREIGN KEY(productId) references  Product(productId) ;
alter table Feedback ADD CONSTRAINT fk_idCA16 FOREIGN KEY(feedbackStatus) references  Setting(settingId) ;
alter table Orders ADD CONSTRAINT fk_idCA17 FOREIGN KEY(customerId) references User(uid) ;
alter table Orders ADD CONSTRAINT fk_idCA18 FOREIGN KEY(salesId) references User(uid) ;
alter table Orders ADD CONSTRAINT fk_idCA19 FOREIGN KEY(orderStatus) references  Setting(settingId) ;
alter table OrderDetails ADD CONSTRAINT fk_idCA20 FOREIGN KEY(orderId) references  Orders(orderid) ;
-- alter table Slider ADD CONSTRAINT fk_idCA22 FOREIGN KEY(s_status) references Setting(settingId) ;
alter table CartDetail ADD CONSTRAINT fk_idCA13 FOREIGN KEY(pid) references  Product(productId) ;
alter table CartDetail ADD CONSTRAINT fk_idCA29 FOREIGN KEY(cartid) references  Cart(cartid) ;
-- HET ITER 3

-- ITER SAU NAY


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
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Ordered','8',1);
-- SETTING TYPE USER TITLE SETTING TYPE ID = 9
-- ID : 26 : MR
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(9,'Mr','9',1);
-- ID : 27: MRS
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(9,'Mrs','9',1);
-- ID : 28 : Ms
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(9,'Ms','9',1);

-- SETTING TYPE SLIDER STATUS SETTING TYPE ID = 10
-- ID : 29 : Published
-- INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
-- VALUES(10,'Published','10',1);
-- ID : 30 : Draft
-- INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
-- VALUES(10,'Draft','10',1);
-- ID : 31 : Published
-- INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
-- VALUES(10,'Hidden','10',1);

-- INSERT INTO USER
INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('vinh@gmail.com','Quang Vinh','Mr','21232f297a57a5a743894a0e4a801fc3',1,'0123456789','Hanoi',23,1);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('manager1@gmail.com','Lionel Messi','Mr','202cb962ac59075b964b07152d234b70',1,'0141233289','Paris',23,2);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('manager2@gmail.com','Cristiano Ronaldo','Mr','202cb962ac59075b964b07152d234b70',1,'0141233212','Manchester',23,2);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('sales1@gmail.com','Kylian Mbappe','Mr','202cb962ac59075b964b07152d234b70',1,'0141443289','Paris',23,3);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('kylie@fs.com','Kendall Jenner','Ms','202cb962ac59075b964b07152d234b70',0,'0141233200','Los Angeles',23,3);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('kanye@west.com','Kanye West','Mr','202cb962ac59075b964b07152d234b70',1,'0141233201','Los Angeles',7,4);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('kadashian@kk.com','Kim Kadashian','Ms','202cb962ac59075b964b07152d234b70',0,'0141233202','Los Angeles',23,4);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('harlaand@bvb.com','Erling Haaland','Mr','202cb962ac59075b964b07152d234b70',1,'0141231100','Dortmund',23,5);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('salad@eg.com','Mohamed Salah','Mr','202cb962ac59075b964b07152d234b70',1,'0231233200','Liverpool',23,5);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('firmino@eg.com','Roberto Firmino','Mr','202cb962ac59075b964b07152d234b70',1,'0231233201','Liverpool',23,5);

INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('mane@eg.com','Sadio Mane','Mr','202cb962ac59075b964b07152d234b70',1,'0231233202','Liverpool',23,5);
-- 12
INSERT INTO `onlineshop1`.`user`(`email`,`fullname`,`title`,`password`,`gender`,`phone`,`address`,`accountStatus`,`role`)
VALUES('jurgen@eg.com','Jurgen Klopp','Mr','202cb962ac59075b964b07152d234b70',1,'0231233202','Liverpool',23,3);

-- INSERT INTO ORDERS 
-- Ordered : 25, Delivered : 20, Transporting : 21 , Canceled : 22
-- SQL> INSERT INTO t(dob) VALUES(TO_DATE('17/12/2015', 'DD/MM/YYYY'));
-- with date datatype we can insert values in simple format: 'yyyy-mm-dd'
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(9,1100000,4,25,'2022-02-02','this is sales 1','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(9,1300000,5,20,'2022-02-03','this is sales 2','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(8,1500000,5,20,'2022-01-27','this is sales 3','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(10,1700000,4,21,'2022-01-25','this is sales 4','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(11,1350000,5,22,'2022-01-26','this is sales 5','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(10,1300000,12,20,'2022-01-28','this is sales 6','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(11,1350000,12,21,'2022-02-14','this is sales 7','2022-03-03');
INSERT INTO `onlineshop1`.`orders`(`customerId`,`totalCost`,`salesId`,`orderStatus`,`orderDate`,`salesNote`,`lastUpdated`)
VALUES(11,1100000,12,22,'2022-02-01','this is sales 8','2022-03-03');

-- INSERT INTO PRODUCT
-- PRODUCT CATEGORY : 13 : Shoes , 14 : Clothes ;15 : Bags
-- PRODUCT STATUS : 16 : Empty , 17 : Stocking
INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike AF1',450000,500000,'None',17,13,'Nike AF1 , Shoes',101,'2022-02-20',4,1);
INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike SB Dunk Travis',550000,600000,'None',17,13,'Nike SB Dunk Travis, Shoes',101,'2022-02-20',4,1);

INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike SB Low Ben Jerrys',650000,700000,'None',17,13,'Nike SB Low Ben Jerrys, Shoes',99,'2022-02-20',5,1);

INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike SB Low Chicago',750000,800000,'None',17,13,'Nike SB Low Chicago, Shoes',97,'2022-02-20',5,1);

INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike Heritage',800000,900000,'None',17,15,'Nike Heritage, Bags',24,'2022-02-20',4,1);

INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike Sportwear Futura 360',400000,450000,'None',17,15,'Nike Sportwear Futura 360, Bags',24,'2022-02-20',4,1);

INSERT INTO `onlineshop1`.`product`
(`title`,`list_price`,`sale_price`,`featured`,`productStatus`,`categoryId`,
`breif_information`,`quantity`,`updatedDate`,`salesId`,`featuredProduct`)
VALUES
('Nike Backpack L21',600000,850000,'None',17,15,'Nike Backpack L21, Bags',22,'2022-02-20',4,0);


-- INSERT INTO ORDER DETAILS
-- ORDER NO 1
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(1,1,1,500000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(1,2,1,600000,CURRENT_DATE());

-- Order NO 2
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(2,2,1,600000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(2,3,1,700000,CURRENT_DATE());

-- Order NO 3
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(3,3,1,700000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(3,4,1,800000,CURRENT_DATE());

-- Order NO 4
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(4,4,1,800000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(4,5,1,900000,CURRENT_DATE());

-- Order NO 5
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(5,5,1,900000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(5,6,1,450000,CURRENT_DATE());

-- Order NO 6
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(6,6,1,450000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(6,7,1,850000,CURRENT_DATE());

-- Order NO 7
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(7,7,1,850000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(7,1,1,500000,CURRENT_DATE());

-- Order NO 8
INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(8,1,1,500000,CURRENT_DATE());

INSERT INTO `onlineshop1`.`orderdetails`
(`orderId`,`productId`,`quantity`,`subCost`,`lastUpdated`)
VALUES(8,2,1,600000,CURRENT_DATE());

-- INSERT POST DATA
-- POST SE CO SETTING TYPE ID =3 
-- 8 : MARKETING
-- 9 : PROMOTED
-- MARKETING ID : 6,7
-- POST STATUS SE CO SETTING TYPE ID = 4 
-- 10 : PUBLIC
-- 11 : DRAFT
-- 12 : DELETED
INSERT INTO `onlineshop1`.`post`
(`postTitle`,`breifInformation`,`postContent`,`postAuthor`,`postCategory`,
`featured`,`status`,`postdDate`,`updatedDate`,`featuredPost`)
VALUES
('Post number one','This is the first post','Just a test bro',
6,8,'None',10,curdate(),curdate(),0);

INSERT INTO `onlineshop1`.`post`
(`postTitle`,`breifInformation`,`postContent`,`postAuthor`,`postCategory`,
`featured`,`status`,`postdDate`,`updatedDate`,`featuredPost`)
VALUES
('Promoted Adidas','Promoting adidas products','Just another test bro',
7,9,'None',10,curdate(),curdate(),1);

INSERT INTO `onlineshop1`.`post`
(`postTitle`,`breifInformation`,`postContent`,`postAuthor`,`postCategory`,
`featured`,`status`,`postdDate`,`updatedDate`,`featuredPost`)
VALUES
('New Nike arrival','This is the third one','Test subject number 3',
6,8,'None',10,curdate(),curdate(),1);

INSERT INTO `onlineshop1`.`post`
(`postTitle`,`breifInformation`,`postContent`,`postAuthor`,`postCategory`,
`featured`,`status`,`postdDate`,`updatedDate`,`featuredPost`)
VALUES
('Hats !!! New hats baby !!!','This is the first post about hats','Just about hats bro',
7,9,'None',10,curdate(),curdate(),1);


-- INSERT INTO SLIDER TEMPORARY
INSERT INTO `onlineshop1`.`slider`
(`s_title`,`s_notes`,`s_status`,`s_backlink`)
VALUES
('Slider No 1','Slider No 1',1,'goods/goodsList');

INSERT INTO `onlineshop1`.`slider`
(`s_title`,`s_notes`,`s_status`,`s_backlink`)
VALUES
('Slider No 2','Slider No 2',1,'goods/goodsList');

INSERT INTO `onlineshop1`.`slider`
(`s_title`,`s_notes`,`s_status`,`s_backlink`)
VALUES
('Slider No 3','Slider No 3',1,'goods/goodsList');

-- INSERT INTO FEEDBACK
INSERT INTO `onlineshop1`.`feedback` (`customerId`, `ratedStar`, `productId`, 
`feedbackStatus`, `feedbackContent`, `updatedDate`, `note`) 
VALUES ('8', '4', '1', '18', 'This is feedback no 1', '2022-03-15 00:00:00', 'Checked');
INSERT INTO `onlineshop1`.`feedback` (`customerId`, `ratedStar`, `productId`, 
`feedbackStatus`, `feedbackContent`, `updatedDate`, `note`) 
VALUES ('9', '5', '2', '18', 'This is feedback no 2', '2022-03-15 00:00:00', 'Checked');
INSERT INTO `onlineshop1`.`feedback` (`customerId`, `ratedStar`, `productId`, 
`feedbackStatus`, `feedbackContent`, `updatedDate`, `note`) 
VALUES ('10', '3', '3', '18', 'This is feedback no 3', '2022-03-15 00:00:00', 'Checked');
INSERT INTO `onlineshop1`.`feedback` (`customerId`, `ratedStar`, `productId`,
 `feedbackStatus`, `feedbackContent`, `updatedDate`, `note`) 
 VALUES ('11', '1', '5', '18', 'This is feedback no 4', '2022-03-15 00:00:00', 'Checked');
 