
CREATE TABLE `DBMSProject8`.`ORDER_CONTAINS` (
	`OrderID` INTEGER,
	`BookID` INTEGER,
	`Book_quan` DECIMAL(10 , 0),
	`Book_price` DECIMAL(10 , 0)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject8`.`AUTHOR` (
	`AUTHORID` INTEGER NOT NULL AUTO_INCREMENT ,
	`AUTHORName` VARCHAR(30),
	PRIMARY KEY (`AUTHORID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject8`.`USER_DETAILS` (
	`UserID` INTEGER NOT NULL AUTO_INCREMENT,
	`UserName` VARCHAR(30) ,
	`Password` VARCHAR(8) ,
	`Role` VARCHAR(1) NOT NULL,
	`FName` VARCHAR(20) ,
	`LName` VARCHAR(20) ,
	PRIMARY KEY (`UserID`)
) ENGINE=InnoDB;


CREATE TABLE `DBMSProject8`.`Genre` (
	`GenreCode` INTEGER NOT NULL AUTO_INCREMENT,
	`GenreDesc` VARCHAR(150),
	PRIMARY KEY (`GenreCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject8`.`WrittenBy` (
	`AUTHORID` INTEGER,
	`BookID` INTEGER,
	PRIMARY KEY (`AUTHORID`,`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject8`.`Book` (
	`BookID` INTEGER NOT NULL AUTO_INCREMENT,
	`Title` VARCHAR(30),
	`Price` DECIMAL(10 , 0),
	`Genrecode` INTEGER,
	`publisher` VARCHAR(30),
	`pub_year` DECIMAL(10 , 0),
	`copies` DECIMAL(10 , 0),
	PRIMARY KEY (`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject8`.`CUSTOMER_ORDER` (
	`OrderID` INTEGER NOT NULL AUTO_INCREMENT,
	`UserID` INTEGER ,
	`OrderQuantity` DECIMAL(10 , 0),
	`OrderPrice` DECIMAL(10 , 0),
	`OrderStatusCode` INTEGER,
	`OrderDate` DATE,
	`CardNo` DECIMAL(10 , 0),
	`StNo` VARCHAR(20),
	`city` VARCHAR(20),
	`state` VARCHAR(20),
	PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB;


CREATE TABLE `DBMSProject8`.`ORDER_DESCRIPTION` (
	`OrderStatusCode` INTEGER NOT NULL AUTO_INCREMENT,
	`OrderDesc` VARCHAR(30),
	PRIMARY KEY (`OrderStatusCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject8`.`WishList` (
	`UserID` INTEGER NOT NULL,
	`BookID` INTEGER, 
	 PRIMARY KEY (`UserID`,`BookID`)
) ENGINE=InnoDB;

CREATE INDEX `BookID` ON `DBMSProject8`.`ORDER_CONTAINS` (`BookID` ASC);



CREATE INDEX `UserID` ON `DBMSProject8`.`CUSTOMER_ORDER` (`UserID` ASC);

CREATE INDEX `AUTHORID` ON `DBMSProject8`.`WrittenBy` (`AUTHORID` ASC);

CREATE INDEX `Genrecode` ON `DBMSProject8`.`Book` (`Genrecode` ASC);

CREATE INDEX `BookID` ON `DBMSProject8`.`WrittenBy` (`BookID` ASC);

CREATE INDEX `OrderID` ON `DBMSProject8`.`ORDER_CONTAINS` (`OrderID` ASC);

CREATE INDEX `OrderStatusCode` ON `DBMSProject8`.`CUSTOMER_ORDER` (`OrderStatusCode` ASC);


ALTER TABLE `DBMSProject8`.`Book` ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Genrecode`)
	REFERENCES `DBMSProject8`.`Genre` (`GenreCode`)  ON DELETE CASCADE;

ALTER TABLE `DBMSProject8`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_2` FOREIGN KEY (`AUTHORID`)
	REFERENCES `DBMSProject8`.`AUTHOR` (`AUTHORID`) ON DELETE CASCADE;

ALTER TABLE `DBMSProject8`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_1` FOREIGN KEY (`BookID`)
	REFERENCES `DBMSProject8`.`Book` (`BookID`)  ON DELETE CASCADE;
	
	
ALTER TABLE `DBMSProject8`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk1` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject8`.`USER_DETAILS`(`UserID`);

ALTER TABLE `DBMSProject8`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk2` FOREIGN KEY (`OrderStatusCode`) 
	REFERENCES `DBMSProject8`.`ORDER_DESCRIPTION`(`OrderStatusCode`);
	
	
ALTER TABLE `DBMSProject8`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk1` FOREIGN KEY (`OrderID`) 
	REFERENCES `DBMSProject8`.`CUSTOMER_ORDER`(`OrderID`) ON DELETE CASCADE;
	
ALTER TABLE `DBMSProject8`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk2` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject8`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject8`.`WishList` ADD CONSTRAINT `wishlist_fk1` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject8`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject8`.`WishList` ADD CONSTRAINT `wishlist_fk2` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject8`.`USER_DETAILS`(`UserID`);
	
	
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Madhu','12345','Unnam','Madhavi','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Sindhu','54321','Tejo','Sindhu','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Chitra','12345','Nikam','Chitra','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Admin1','12345','Sam','Jose','1');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Admin2','54321','Scott','Jim','1');

INSERT INTO ORDER_DESCRIPTION(`OrderDesc`,`OrderStatusCode`) values ('Placed',1);
INSERT INTO ORDER_DESCRIPTION(`OrderDesc`,`OrderStatusCode`) values ('Processed',2);
INSERT INTO ORDER_DESCRIPTION(`OrderDesc`,`OrderStatusCode`) values ('Cancelled',3);

select * from USER_DETAILS;
select * from Book;
select * from AUTHOR;
select * from WrittenBy;
select * from Genre;
select * from CUSTOMER_ORDER;
select * from ORDER_CONTAINS;
select * from ORDER_DESCRIPTION;
select * from WishList;

select  B.Title from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, ORDER_CONTAINS O, Book B  
						where OD.OrderStatusCode = C.OrderStatusCode and O.OrderID = C.OrderID and B.BookID = O.BookID  
						and C.OrderStatusCode = 2 and C.OrderDate BETWEEN '2015-04-07' and '2015-04-14' and B.Copies > 90 order by C.OrderID; 

select  B.Publisher from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, ORDER_CONTAINS O, Book B  
						where OD.OrderStatusCode = C.OrderStatusCode and O.OrderID = C.OrderID and B.BookID = O.BookID  
						and C.OrderStatusCode = 2 and C.OrderDate BETWEEN '2015-04-07' and '2015-04-14' and B.Copies < 50 order by C.OrderID; 
						