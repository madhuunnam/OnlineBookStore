
CREATE TABLE `DBMSProject7`.`ORDER_CONTAINS` (
	`OrderID` INTEGER,
	`BookID` INTEGER,
	`Book_quan` DECIMAL(10 , 0),
	`Book_price` DECIMAL(10 , 0)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject7`.`AUTHOR` (
	`AUTHORID` INTEGER NOT NULL AUTO_INCREMENT ,
	`AUTHORName` VARCHAR(30),
	PRIMARY KEY (`AUTHORID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject7`.`USER_DETAILS` (
	`UserID` INTEGER NOT NULL AUTO_INCREMENT,
	`UserName` VARCHAR(30) ,
	`Password` VARCHAR(8) ,
	`Role` VARCHAR(1) NOT NULL,
	`FName` VARCHAR(20) ,
	`LName` VARCHAR(20) ,
	PRIMARY KEY (`UserID`)
) ENGINE=InnoDB;


CREATE TABLE `DBMSProject7`.`Genre` (
	`GenreCode` INTEGER NOT NULL AUTO_INCREMENT,
	`GenreDesc` VARCHAR(150),
	PRIMARY KEY (`GenreCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject7`.`WrittenBy` (
	`AUTHORID` INTEGER,
	`BookID` INTEGER,
	PRIMARY KEY (`AUTHORID`,`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject7`.`Book` (
	`BookID` INTEGER NOT NULL AUTO_INCREMENT,
	`Title` VARCHAR(30),
	`Price` DECIMAL(10 , 0),
	`Genrecode` INTEGER,
	`publisher` VARCHAR(30),
	`pub_year` DECIMAL(10 , 0),
	`copies` DECIMAL(10 , 0),
	PRIMARY KEY (`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject7`.`CUSTOMER_ORDER` (
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


CREATE TABLE `DBMSProject7`.`ORDER_DESCRIPTION` (
	`OrderStatusCode` INTEGER NOT NULL AUTO_INCREMENT,
	`OrderDesc` VARCHAR(30),
	PRIMARY KEY (`OrderStatusCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject7`.`WishList` (
	`UserID` INTEGER NOT NULL,
	`BookID` INTEGER, 
	 PRIMARY KEY (`UserID`,`BookID`)
) ENGINE=InnoDB;

CREATE INDEX `BookID` ON `DBMSProject7`.`ORDER_CONTAINS` (`BookID` ASC);



CREATE INDEX `UserID` ON `DBMSProject7`.`CUSTOMER_ORDER` (`UserID` ASC);

CREATE INDEX `AUTHORID` ON `DBMSProject7`.`WrittenBy` (`AUTHORID` ASC);

CREATE INDEX `Genrecode` ON `DBMSProject7`.`Book` (`Genrecode` ASC);

CREATE INDEX `BookID` ON `DBMSProject7`.`WrittenBy` (`BookID` ASC);

CREATE INDEX `OrderID` ON `DBMSProject7`.`ORDER_CONTAINS` (`OrderID` ASC);

CREATE INDEX `OrderStatusCode` ON `DBMSProject7`.`CUSTOMER_ORDER` (`OrderStatusCode` ASC);


ALTER TABLE `DBMSProject7`.`Book` ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Genrecode`)
	REFERENCES `DBMSProject7`.`Genre` (`GenreCode`)  ON DELETE CASCADE;

ALTER TABLE `DBMSProject7`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_2` FOREIGN KEY (`AUTHORID`)
	REFERENCES `DBMSProject7`.`AUTHOR` (`AUTHORID`) ON DELETE CASCADE;

ALTER TABLE `DBMSProject7`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_1` FOREIGN KEY (`BookID`)
	REFERENCES `DBMSProject7`.`Book` (`BookID`)  ON DELETE CASCADE;
	
	
ALTER TABLE `DBMSProject7`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk1` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject7`.`USER_DETAILS`(`UserID`);

ALTER TABLE `DBMSProject7`.`CUSTOMER_ORDER` ADD CONSTRAINT `cusorder_fk2` FOREIGN KEY (`OrderStatusCode`) 
	REFERENCES `DBMSProject7`.`ORDER_DESCRIPTION`(`OrderStatusCode`);
	
	
ALTER TABLE `DBMSProject7`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk1` FOREIGN KEY (`OrderID`) 
	REFERENCES `DBMSProject7`.`CUSTOMER_ORDER`(`OrderID`) ON DELETE CASCADE;
	
ALTER TABLE `DBMSProject7`.`ORDER_CONTAINS` ADD CONSTRAINT `ordercont_fk2` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject7`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject7`.`WishList` ADD CONSTRAINT `wishlist_fk1` FOREIGN KEY (`BookID`) 
	REFERENCES `DBMSProject7`.`Book`(`BookID`);
	
ALTER TABLE `DBMSProject7`.`WishList` ADD CONSTRAINT `wishlist_fk2` FOREIGN KEY (`UserID`) 
	REFERENCES `DBMSProject7`.`USER_DETAILS`(`UserID`);
	
	
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Madhu','12345','Unnam','Madhavi','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Sindhu','54321','Tejo','Sindhu','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Chitra','12345','Nikam','Chitra','0');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Admin1','12345','Sam','Jose','1');
INSERT INTO `USER_DETAILS`(`UserName`, `Password`,`FName`, `LName`, `Role`) VALUES ('Admin2','54321','Scott','Jim','1');

INSERT INTO ORDER_DESCRIPTION(`OrderDesc`,`OrderStatusCode`) values ('Placed',1);
INSERT INTO ORDER_DESCRIPTION(`OrderDesc`,`OrderStatusCode`) values ('Processed',2);
INSERT INTO ORDER_DESCRIPTION(`OrderDesc`,`OrderStatusCode`) values ('Cancelled',3);



select * from genre;
select * from writtenby;
select * from author;
delete from CUSTOMER_ORDER;
select * from ORDER_CONTAINS;
select * from CUSTOMER_ORDER;

delete from genre where GenreCode =1;
delete from writtenby where BookID = 2;
delete from wishlist where BookID = 6;


select B.Title, C.OrderID, OD.OrderDesc from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, 
		Book B, ORDER_CONTAINS O where 
		C.OrderStatusCode=OD.OrderStatusCode and O.BookID = B.BookID and O.OrderID = C.OrderID and  C.UserID = 1;
		
	select C.OrderID,B.BookID, C.UserID, B.Title,B.Copies,O.Book_price,O.Book_quan,OD.OrderDesc from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, ORDER_CONTAINS O, Book B  
					 where OD.OrderStatusCode = C.OrderStatusCode and O.OrderID = C.OrderID and B.BookID = O.BookID;
				
		
		select * from ORDER_DESCRIPTION;
		update ORDER_DESCRIPTION set OrderDesc = "" where OrderStatusCode = 3;
		
update CUSTOMER_ORDER set OrderStatusCode = 1;
select * from CUSTOMER_ORDER;

select B.BookID , B.Title, C.OrderPrice from Book B,CUSTOMER_ORDER C, ORDER_CONTAINS O  where B.BookID = O.BookID and C.OrderID = O.OrderID  and C.OrderDate BETWEEN '2015-03-11' and '2015-04-10';

select sum(OrderPrice)	from CUSTOMER_ORDER where OrderDate between '2015-04' and '2015-04';
select * from Book;
select * from writtenby;
delete from Book where BookID in (2,3);

delete from USER_DETAILS where UserID in (6,7,8,9) ;
select * from USER_DETAILS;
select * from Book;
update book set copies = 200 where bookID in ( 4, 5,6) ;

select C.OrderID , C.UserID, C.OrderQuantity,C.OrderPrice  from CUSTOMER_ORDER C  
						where   C.OrderStatusCode = 1 
						and C.OrderDate BETWEEN '2015-03-11' and '2015-04-12' ;
						
						Select B.BookID, B.Title,B.Copies,B.Price,B.publisher,B.pub_year,G.GenreDesc,A.AUTHORName from WishList WL, Book B, Genre G, AUTHOR A, WrittenBy WB 
					where A.AUTHORID = WB.AUTHORID and B.Genrecode = G.GenreCode and B.BookID = WB.BookID and WL.BookID = B.BookID 
					and WL.UserID = 1  order by B.BookID;
					
					select * from genre;
					select * from writtenby;
					select * from author;
					select * from book;
		delete from author where authorID = 5;		
		
		
		select C.OrderID, B.BookID, C.UserID, B.Title,O.Book_price,O.Book_quan,OD.OrderDesc from CUSTOMER_ORDER C, ORDER_DESCRIPTION OD, ORDER_CONTAINS O, Book B  
						where OD.OrderStatusCode = C.OrderStatusCode and O.OrderID = C.OrderID and B.BookID = O.BookID  
						and C.OrderStatusCode = 2 and C.OrderDate BETWEEN '2015-03-11' and '2015-04-13' order by C.OrderID ;
			