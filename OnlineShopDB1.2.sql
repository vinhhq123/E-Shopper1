
use OnlineShop1;

create table SettingType(
settingTypeId int AUTO_INCREMENT primary key,
typeName varchar(200)  
);

create table Setting(
settingId int AUTO_INCREMENT primary key,
settingType int references SettingType(settingTypeId),
settingValue varchar(200),
settingOrder varchar(200), 
settingStatus bit --   ACTIVE (1) / INACTIVE (0)
);
alter table Setting ADD CONSTRAINT fk_idCA1 FOREIGN KEY(settingType) references SettingType(settingTypeId) ;
alter table Setting ADD CONSTRAINT fk_idCA1 FOREIGN KEY(settingType) references SettingType(settingTypeId) ;

-- INSERT INTO SETTING TYPE
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('User Role');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Account Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Post Category');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Post Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Product Category');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Product Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Feedback Status');
INSERT INTO `onlineshop1`.`settingtype`(`typeName`) VALUES('Order Status');

-- INSERI INTO SETTING
-- SETTING TYPE USER ROLE SETTING TYPE ID = 1
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'admin','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'manager','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'sales','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'marketing','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'customer','None',1);

-- SETTING TYPE ACCOUNT STATUS SETTING TYPE ID = 2
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(2,'registered','None',0);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(1,'verified','None',1);

-- SETTING TYPE POST CATEGORY SETTING TYPE ID = 3
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(3,'Marketing','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(3,'Promoted','None',1);

-- SETTING TYPE POST STATUS SETTING TYPE ID = 4
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(4,'Published','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(4,'Draft','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(4,'Deleted','None',0);

-- SETTING TYPE PRODUCT CATEGORY SETTING TYPE ID = 5
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(5,'Shoes','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(5,'Clothes','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(5,'Bags','None',1);

-- SETTING TYPE PRODUCT STATUS SETTING TYPE ID = 6
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(6,'Empty','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(6,'Stocking','None',1);

-- SETTING TYPE FEEDBACK STATUS SETTING TYPE ID = 7
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(7,'Published','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(7,'Deleted','None',0);

-- SETTING TYPE ORDER STATUS SETTING TYPE ID = 8
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Delivered','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Transporting','None',1);
INSERT INTO `onlineshop1`.`setting`(`settingType`,`settingValue`,`settingOrder`,`settingStatus`)
VALUES(8,'Canceled','None',0);


CREATE TABLE Account (
  aid int AUTO_INCREMENT primary key,
  email varchar(200) ,
  password varchar(200) ,
  accountStatus int references Setting(settingID),
  role int references Setting(settingID)
);
alter table Account ADD CONSTRAINT fk_idCA2 FOREIGN KEY(role) references Setting(settingID) ;
alter table Account ADD CONSTRAINT fk_idCA3 FOREIGN KEY(accountStatus) references Setting(settingID) ;
create table User(
  uid int AUTO_INCREMENT primary key,
  fullname varchar(200),
  title varchar(50), -- MR , MRS , MS
  -- email varchar(200) ,
  gender bit , 
  phone varchar(10) ,
  address varchar(200),
  aid int references Account(aid),
  avatar text 
);
alter table User ADD CONSTRAINT fk_idCA4 FOREIGN KEY(aid) references Account(aid) ;

CREATE TABLE Post (
  postId int AUTO_INCREMENT primary key,
  thumbnail text,
  postTitle varchar(200) ,
  postAuthor int references Account(aid) ,
  postCategory int references Setting(settingId) ,
  featured varchar(200),
  status int references Setting(settingId),
  updatedDate date 
);
alter table Post ADD CONSTRAINT fk_idCA5 FOREIGN KEY(postAuthor) references Account(aid) ;
alter table Post ADD CONSTRAINT fk_idCA6 FOREIGN KEY(postCategory) references Setting(settingId) ;
alter table Post ADD CONSTRAINT fk_idCA7 FOREIGN KEY(status) references Setting(settingId) ;

create table PostDetails(
postDetailID int AUTO_INCREMENT primary key,
postId int references Post(postId),
breifInformation text,
postContent text,
postDate date
);
alter table PostDetails ADD CONSTRAINT fk_idCA8 FOREIGN KEY(postId) references Post(postId) ;




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
  salesId int references Account(aid)
);
alter table Product ADD CONSTRAINT fk_idCA10 FOREIGN KEY(productStatus) references Setting(settingId) ;
alter table Product ADD CONSTRAINT fk_idCA11 FOREIGN KEY(categoryId) references Setting(settingId) ;
alter table Product ADD CONSTRAINT fk_idCA12 FOREIGN KEY(salesId) references Account(aid) ;


CREATE TABLE CartDetail (
  cart_id int AUTO_INCREMENT primary key,
  pid int references Product(productId),
  quantity int,
  toalPriceOfProduct float ,
  cartid int references Cart(cartid)
)  ;
alter table CartDetail ADD CONSTRAINT fk_idCA13 FOREIGN KEY(pid) references  Product(productId) ;
CREATE TABLE Cart (
  cartid int AUTO_INCREMENT primary key,
  cart_total_price float,
  aid int references Account(aid)
  );
  alter table Cart ADD CONSTRAINT fk_idCA9 FOREIGN KEY(aid) references Account(aid) ;
  alter table CartDetail ADD CONSTRAINT fk_idCA25 FOREIGN KEY(cartid) references Cart(cartid) ;


create table Feedback(
feedbackId int AUTO_INCREMENT primary key,
customerId int references Account(aid),
ratedStar int, -- max 5
productId int references Product(productId),
image text,
feedbackStatus int references Setting(settingId),
feedbackContent text,
updatedDate datetime
);

alter table Feedback ADD CONSTRAINT fk_idCA14 FOREIGN KEY(customerId) references  Account(aid) ;
alter table Feedback ADD CONSTRAINT fk_idCA15 FOREIGN KEY(productId) references  Product(productId) ;
alter table Feedback ADD CONSTRAINT fk_idCA16 FOREIGN KEY(feedbackStatus) references  Setting(settingId) ;


create table Orders(
orderId int AUTO_INCREMENT primary key,
customerId int references Account(aid),
totalCost float, -- of this Order
salesId int references Account(aid),
orderStatus int references Setting(settingId)
);
alter table Orders ADD CONSTRAINT fk_idCA17 FOREIGN KEY(customerId) references  Account(aid) ;
alter table Orders ADD CONSTRAINT fk_idCA18 FOREIGN KEY(salesId) references  Account(aid) ;
alter table Orders ADD CONSTRAINT fk_idCA19 FOREIGN KEY(orderStatus) references  Setting(settingId) ;
create table OrderDetails(
orderDetailId int AUTO_INCREMENT primary key,
orderId int references Orders(orderid),
productId int references Product(productId),
quantity int,
totalCost float, -- of this product
orderDate datetime,
lastUpdated datetime
);
alter table OrderDetails ADD CONSTRAINT fk_idCA20 FOREIGN KEY(orderId) references  Orders(orderid) ;
alter table OrderDetails ADD CONSTRAINT fk_idCA21 FOREIGN KEY(productId) references  Product(productId) ;


