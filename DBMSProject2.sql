
CREATE TABLE `DBMSProject5`.`ORDER_CONTAINS` (
	`OrderID` INTEGER,
	`BookID` INTEGER,
	`Book_quan` DECIMAL(10 , 0),
	`Book_price` DECIMAL(10 , 0)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`AUTHOR` (
	`AUTHORID` INTEGER NOT NULL AUTO_INCREMENT ,
	`AUTHORName` VARCHAR(30),
	PRIMARY KEY (`AUTHORID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`USER_DETAILS` (
	`UserID` INTEGER NOT NULL,
	`LoginName` VARCHAR(30),
	`FName` VARCHAR(20),
	`LName` VARCHAR(20),
	`StNo` VARCHAR(20),
	`City` VARCHAR(20),
	`State` VARCHAR(20),
	PRIMARY KEY (`UserID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`Login` (
	`LoginName` VARCHAR(30) NOT NULL,
	`Password` VARCHAR(8),
	`Role` VARCHAR(10),
	PRIMARY KEY (`LoginName`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`Genre` (
	`GenreCode` INTEGER NOT NULL AUTO_INCREMENT,
	`GenreDesc` VARCHAR(150),
	PRIMARY KEY (`GenreCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`WrittenBy` (
	`AUTHORID` INTEGER,
	`BookID` INTEGER,
	PRIMARY KEY (`AUTHORID`,`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`Book` (
	`BookID` INTEGER NOT NULL AUTO_INCREMENT,
	`Title` VARCHAR(30),
	`Price` DECIMAL(10 , 0),
	`Genrecode` INTEGER,
	`publisher` VARCHAR(30),
	`pub_year` DECIMAL(10 , 0),
	`copies` DECIMAL(10 , 0),
	PRIMARY KEY (`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`CUSTOMER_ORDER` (
	`OrderID` INTEGER NOT NULL AUTO_INCREMENT,
	`UserID` INTEGER ,
	`OrderQuantity` DECIMAL(10 , 0),
	`OrderPrice` DECIMAL(10 , 0),
	`OrderStatusCode` INTEGER,
	`OrderDate` DATE,
	`ShipAddId` INTEGER,
	`CardNo` DECIMAL(10 , 0),
	PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`SHIPPING_ADDRESS` (
	`ShipAddId` INTEGER NOT NULL AUTO_INCREMENT,
	`StNo` VARCHAR(20),
	`city` VARCHAR(20),
	`state` VARCHAR(20),
	PRIMARY KEY (`ShipAddId`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`ORDER_DESCRIPTION` (
	`OrderStatusCode` INTEGER NOT NULL AUTO_INCREMENT,
	`OrderDesc` VARCHAR(30),
	PRIMARY KEY (`OrderStatusCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject5`.`WishList` (
	`UserID` INTEGER NOT NULL,
	`BookID` INTEGER, 
	 PRIMARY KEY (`UserID`,`BookID`)
) ENGINE=InnoDB;

CREATE INDEX `BookID` ON `DBMSProject5`.`ORDER_CONTAINS` (`BookID` ASC);

CREATE INDEX `ShipAddId` ON `DBMSProject5`.`CUSTOMER_ORDER` (`ShipAddId` ASC);

CREATE INDEX `UserID` ON `DBMSProject5`.`CUSTOMER_ORDER` (`UserID` ASC);

CREATE INDEX `AUTHORID` ON `DBMSProject5`.`WrittenBy` (`AUTHORID` ASC);

CREATE INDEX `Genrecode` ON `DBMSProject5`.`Book` (`Genrecode` ASC);

CREATE INDEX `BookID` ON `DBMSProject5`.`WrittenBy` (`BookID` ASC);

CREATE INDEX `OrderID` ON `DBMSProject5`.`ORDER_CONTAINS` (`OrderID` ASC);

CREATE INDEX `OrderStatusCode` ON `DBMSProject5`.`CUSTOMER_ORDER` (`OrderStatusCode` ASC);

CREATE INDEX `LoginName` ON `DBMSProject5`.`USER_DETAILS` (`LoginName` ASC);

ALTER TABLE `DBMSProject5`.`Book` ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Genrecode`)
	REFERENCES `DBMSProject5`.`Genre` (`GenreCode`);


ALTER TABLE `DBMSProject5`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_2` FOREIGN KEY (`AUTHORID`)
	REFERENCES `DBMSProject5`.`AUTHOR` (`AUTHORID`);

ALTER TABLE `DBMSProject5`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_1` FOREIGN KEY (`BookID`)
	REFERENCES `DBMSProject5`.`Book` (`BookID`);
	
ALTER TABLE `DBMSProject5`.`USER_DETAILS` ADD CONSTRAINT `USER_DETAILS_fk1` FOREIGN KEY (`LoginName`) 
	REFERENCES `DBMSProject5`.`Login`(`LoginName`);
	
ALTER TABLE `DBMSProject5`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk1` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject5`.`USER_DETAILS`(`UserID`);

ALTER TABLE `DBMSProject5`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk2` FOREIGN KEY (`OrderStatusCode`) 
	REFERENCES `DBMSProject5`.`ORDER_DESCRIPTION`(`OrderStatusCode`);

ALTER TABLE `DBMSProject5`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk3` FOREIGN KEY (`ShipAddId`) 
	REFERENCES `DBMSProject5`.`SHIPPING_ADDRESS`(`ShipAddId`);
	
ALTER TABLE `DBMSProject5`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk1` FOREIGN KEY (`OrderID`) 
	REFERENCES `DBMSProject5`.`CUSTOMER_ORDER`(`OrderID`);
	
ALTER TABLE `DBMSProject5`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk2` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject5`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject5`.`WishList` ADD CONSTRAINT `wishlist_fk1` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject5`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject5`.`WishList` ADD CONSTRAINT `wishlist_fk2` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject5`.`USER_DETAILS`(`UserID`);
	
	select * from book;
	delete from book where Title is null;
	select * from Genre
	delete from Genre;
	
select Max(GenreCode) as GenreCode from Genre;
delete from genre where GenreCode != 1 or GenreCode != 2;
select * from Genre;
INSERT INTO `Genre`(`GenreDesc`) VALUES ('THEORY');
INSERT INTO `Genre`(`GenreDesc`) VALUES ('PRACTICAL');
delete from genre where GenreCode in ( 3, 4);
select B.BookID,B.Title,B.Price,B.Copies,G.GenreDesc,B.Publisher,B.Pub_year, A.AuthorName from Book B,Author A, WrittenBy W, Genre G where G.GenreCode= B.Genrecode and A.AuthorID= W.AuthorID and B.BookID = W.BookID;