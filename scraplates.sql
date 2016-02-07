
CREATE TABLE `DBMSProject6`.`ORDER_CONTAINS` (
	`OrderID` INTEGER,
	`BookID` INTEGER,
	`Book_quan` DECIMAL(10 , 0),
	`Book_price` DECIMAL(10 , 0)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`AUTHOR` (
	`AUTHORID` INTEGER NOT NULL AUTO_INCREMENT ,
	`AUTHORName` VARCHAR(30),
	PRIMARY KEY (`AUTHORID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`USER_DETAILS` (
	`UserID` INTEGER NOT NULL AUTO_INCREMENT,
	`UserName` VARCHAR(30),
	`Password` VARCHAR(8),
	`Role` VARCHAR(1),
	`FName` VARCHAR(20),
	`LName` VARCHAR(20),
	PRIMARY KEY (`UserID`)
) ENGINE=InnoDB;


CREATE TABLE `DBMSProject6`.`Genre` (
	`GenreCode` INTEGER NOT NULL AUTO_INCREMENT,
	`GenreDesc` VARCHAR(150),
	PRIMARY KEY (`GenreCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`WrittenBy` (
	`AUTHORID` INTEGER,
	`BookID` INTEGER,
	PRIMARY KEY (`AUTHORID`,`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`Book` (
	`BookID` INTEGER NOT NULL AUTO_INCREMENT,
	`Title` VARCHAR(30),
	`Price` DECIMAL(10 , 0),
	`Genrecode` INTEGER,
	`publisher` VARCHAR(30),
	`pub_year` DECIMAL(10 , 0),
	`copies` DECIMAL(10 , 0),
	PRIMARY KEY (`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`CUSTOMER_ORDER` (
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

CREATE TABLE `DBMSProject6`.`SHIPPING_ADDRESS` (
	`ShipAddId` INTEGER NOT NULL AUTO_INCREMENT,
	`StNo` VARCHAR(20),
	`city` VARCHAR(20),
	`state` VARCHAR(20),
	PRIMARY KEY (`ShipAddId`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`ORDER_DESCRIPTION` (
	`OrderStatusCode` INTEGER NOT NULL AUTO_INCREMENT,
	`OrderDesc` VARCHAR(30),
	PRIMARY KEY (`OrderStatusCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject6`.`WishList` (
	`UserID` INTEGER NOT NULL,
	`BookID` INTEGER, 
	 PRIMARY KEY (`UserID`,`BookID`)
) ENGINE=InnoDB;

CREATE INDEX `BookID` ON `DBMSProject6`.`ORDER_CONTAINS` (`BookID` ASC);

CREATE INDEX `ShipAddId` ON `DBMSProject6`.`CUSTOMER_ORDER` (`ShipAddId` ASC);

CREATE INDEX `UserID` ON `DBMSProject6`.`CUSTOMER_ORDER` (`UserID` ASC);

CREATE INDEX `AUTHORID` ON `DBMSProject6`.`WrittenBy` (`AUTHORID` ASC);

CREATE INDEX `Genrecode` ON `DBMSProject6`.`Book` (`Genrecode` ASC);

CREATE INDEX `BookID` ON `DBMSProject6`.`WrittenBy` (`BookID` ASC);

CREATE INDEX `OrderID` ON `DBMSProject6`.`ORDER_CONTAINS` (`OrderID` ASC);

CREATE INDEX `OrderStatusCode` ON `DBMSProject6`.`CUSTOMER_ORDER` (`OrderStatusCode` ASC);


ALTER TABLE `DBMSProject6`.`Book` ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Genrecode`)
	REFERENCES `DBMSProject6`.`Genre` (`GenreCode`);

ALTER TABLE `DBMSProject6`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_2` FOREIGN KEY (`AUTHORID`)
	REFERENCES `DBMSProject6`.`AUTHOR` (`AUTHORID`);

ALTER TABLE `DBMSProject6`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_1` FOREIGN KEY (`BookID`)
	REFERENCES `DBMSProject6`.`Book` (`BookID`);
	
	
ALTER TABLE `DBMSProject6`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk1` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject6`.`USER_DETAILS`(`UserID`);

ALTER TABLE `DBMSProject6`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk2` FOREIGN KEY (`OrderStatusCode`) 
	REFERENCES `DBMSProject6`.`ORDER_DESCRIPTION`(`OrderStatusCode`);

ALTER TABLE `DBMSProject6`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk3` FOREIGN KEY (`ShipAddId`) 
	REFERENCES `DBMSProject6`.`SHIPPING_ADDRESS`(`ShipAddId`);
	
ALTER TABLE `DBMSProject6`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk1` FOREIGN KEY (`OrderID`) 
	REFERENCES `DBMSProject6`.`CUSTOMER_ORDER`(`OrderID`);
	
ALTER TABLE `DBMSProject6`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk2` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject6`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject6`.`WishList` ADD CONSTRAINT `wishlist_fk1` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject6`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject6`.`WishList` ADD CONSTRAINT `wishlist_fk2` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject6`.`USER_DETAILS`(`UserID`);
	
	
INSERT INTO `Author`(`AuthorName`) VALUES ('Abraham');
INSERT INTO `Author`( `AuthorName`) VALUES ('Rivero');
INSERT INTO `Author`( `AuthorName`) VALUES ('Michael');
INSERT INTO `Author`(`AuthorName`) VALUES ('Allen');
INSERT INTO `Author`( `AuthorName`) VALUES ('Kim');

INSERT INTO `Genre`(`GenreDesc`) VALUES ('THEORY');
INSERT INTO `Genre`(`GenreDesc`) VALUES ('PRACTICAL');

INSERT INTO `BOOK`( `Title`, `Price`, `Genrecode`, `publisher`, `pub_year`, `copies`) VALUES ('DB Sys Concepts',10,1,'Anitha',1980,12);
INSERT INTO `BOOK`( `Title`, `Price`, `Genrecode`, `publisher`, `pub_year`, `copies`) VALUES ('SQL',100,2,'Samy',1990,20);
INSERT INTO `BOOK`( `Title`, `Price`, `Genrecode`, `publisher`, `pub_year`, `copies`) VALUES ('SQLite',30,2,'Priya',1900,5);
INSERT INTO `BOOK`( `Title`, `Price`, `Genrecode`, `publisher`, `pub_year`, `copies`) VALUES ('Adv SQL',20,2,'Ram',1995,9);
INSERT INTO `BOOK`( `Title`, `Price`, `Genrecode`, `publisher`, `pub_year`, `copies`) VALUES ('Db Design',90,1,'Anitha',1900,5);

INSERT INTO `WrittenBy`(`AuthorID`, `BookID`) VALUES (1,1);
INSERT INTO `WrittenBy`(`AuthorID`, `BookID`) VALUES (1,6);
INSERT INTO `WrittenBy`(`AuthorID`, `BookID`) VALUES (2,3);
INSERT INTO `WrittenBy`(`AuthorID`, `BookID`) VALUES (3,2);
INSERT INTO `WrittenBy`(`AuthorID`, `BookID`) VALUES (4,4);
INSERT INTO `WrittenBy`(`AuthorID`, `BookID`) VALUES (5,4);




INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Madhu','12345','Unnam','Madhavi','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Sindhu','54321','Tejo','Sindhu','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Chitra','12345','Nikam','Chitra','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Admin1','12345','Sam','Jose','1');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Admin2','54321','Scott','Jim','1');



select * from Book;
select * from Genre;
select * from Author;
select * from WrittenBy;
Select B.BookID,B.Title,B.Copies,B.publisher,B.pub_year,B.Price,G.GenreDesc,A.AUTHORName from WishList WL,Book B,Genre G,AUTHOR A, WrittenBy WB 
						where A.AUTHORID = WB.AUTHORID and B.Genrecode = G.GenreCode and B.BookID = WB.BookID and WL.BookID = B.BookID 
					and WL.UserID = 2
					
					select * from WishList where UserID = 1
					
					select b.BookID, b.Title, B.Price from Book B where B.BookID = 5