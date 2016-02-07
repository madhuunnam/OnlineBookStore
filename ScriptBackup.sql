--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `DBMSProject`.`Book` DROP FOREIGN KEY `book_ibfk_1`;

ALTER TABLE `DBMSProject`.`WrittenBy` DROP FOREIGN KEY `writtenby_ibfk_2`;

ALTER TABLE `DBMSProject`.`WrittenBy` DROP FOREIGN KEY `writtenby_ibfk_1`;

ALTER TABLE `DBMSProject`.`Order_desc` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Login` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`User_details` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Author` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Ship_Add` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Genre` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Book` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Cus_Order` DROP PRIMARY KEY;

ALTER TABLE `DBMSProject`.`Order_cont` DROP INDEX `DBMSProject`.`BookID`;

ALTER TABLE `DBMSProject`.`Cus_Order` DROP INDEX `DBMSProject`.`SID`;

ALTER TABLE `DBMSProject`.`Cus_Order` DROP INDEX `DBMSProject`.`UserID`;

ALTER TABLE `DBMSProject`.`WrittenBy` DROP INDEX `DBMSProject`.`AuthorID`;

ALTER TABLE `DBMSProject`.`Book` DROP INDEX `DBMSProject`.`Genrecode`;

ALTER TABLE `DBMSProject`.`WrittenBy` DROP INDEX `DBMSProject`.`BookID`;

ALTER TABLE `DBMSProject`.`Order_cont` DROP INDEX `DBMSProject`.`OID`;

ALTER TABLE `DBMSProject`.`Cus_Order` DROP INDEX `DBMSProject`.`O_status`;

ALTER TABLE `DBMSProject`.`User_details` DROP INDEX `DBMSProject`.`LoginName`;

DROP TABLE `DBMSProject`.`Order_cont`;

DROP TABLE `DBMSProject`.`Author`;

DROP TABLE `DBMSProject`.`User_details`;

DROP TABLE `DBMSProject`.`Login`;

DROP TABLE `DBMSProject`.`Genre`;

DROP TABLE `DBMSProject`.`WrittenBy`;

DROP TABLE `DBMSProject`.`Book`;

DROP TABLE `DBMSProject`.`Cus_Order`;

DROP TABLE `DBMSProject`.`Ship_Add`;

DROP TABLE `DBMSProject`.`Order_desc`;

CREATE TABLE `DBMSProject`.`Order_cont` (
	`OID` VARCHAR(5),
	`BookID` VARCHAR(5),
	`Book_quan` DECIMAL(10 , 0),
	`Book_price` DECIMAL(10 , 0)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Author` (
	`AuthorID` VARCHAR(5) NOT NULL,
	`AuthorName` VARCHAR(30),
	PRIMARY KEY (`AuthorID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`User_details` (
	`UserID` VARCHAR(5) NOT NULL,
	`LoginName` VARCHAR(30),
	`FName` VARCHAR(20),
	`LName` VARCHAR(20),
	`StNo` VARCHAR(20),
	`City` VARCHAR(20),
	`State` VARCHAR(20),
	PRIMARY KEY (`UserID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Login` (
	`LoginName` VARCHAR(30) NOT NULL,
	`Password` VARCHAR(8),
	`Role` VARCHAR(10),
	PRIMARY KEY (`LoginName`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Genre` (
	`GenreCode` VARCHAR(5) NOT NULL,
	`GenreDesc` VARCHAR(150),
	PRIMARY KEY (`GenreCode`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`WrittenBy` (
	`AuthorID` VARCHAR(5),
	`BookID` VARCHAR(5)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Book` (
	`BookID` VARCHAR(5) NOT NULL,
	`Title` VARCHAR(30),
	`Price` DECIMAL(10 , 0),
	`Genrecode` VARCHAR(5),
	`publisher` VARCHAR(30),
	`pub_year` DECIMAL(10 , 0),
	`copies` DECIMAL(10 , 0),
	PRIMARY KEY (`BookID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Cus_Order` (
	`OID` VARCHAR(5) NOT NULL,
	`UserID` VARCHAR(5),
	`O_quan` DECIMAL(10 , 0),
	`O_price` DECIMAL(10 , 0),
	`O_status` VARCHAR(15),
	`O_date` DATE,
	`SID` VARCHAR(5),
	`CardNo` DECIMAL(10 , 0),
	PRIMARY KEY (`OID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Ship_Add` (
	`SID` VARCHAR(5) NOT NULL,
	`StNo` VARCHAR(20),
	`city` VARCHAR(20),
	`state` VARCHAR(20),
	PRIMARY KEY (`SID`)
) ENGINE=InnoDB;

CREATE TABLE `DBMSProject`.`Order_desc` (
	`O_status` VARCHAR(15) NOT NULL,
	`Order_Desc` VARCHAR(30),
	PRIMARY KEY (`O_status`)
) ENGINE=InnoDB;

CREATE INDEX `BookID` ON `DBMSProject`.`Order_cont` (`BookID` ASC);

CREATE INDEX `SID` ON `DBMSProject`.`Cus_Order` (`SID` ASC);

CREATE INDEX `UserID` ON `DBMSProject`.`Cus_Order` (`UserID` ASC);

CREATE INDEX `AuthorID` ON `DBMSProject`.`WrittenBy` (`AuthorID` ASC);

CREATE INDEX `Genrecode` ON `DBMSProject`.`Book` (`Genrecode` ASC);

CREATE INDEX `BookID` ON `DBMSProject`.`WrittenBy` (`BookID` ASC);

CREATE INDEX `OID` ON `DBMSProject`.`Order_cont` (`OID` ASC);

CREATE INDEX `O_status` ON `DBMSProject`.`Cus_Order` (`O_status` ASC);

CREATE INDEX `LoginName` ON `DBMSProject`.`User_details` (`LoginName` ASC);

ALTER TABLE `DBMSProject`.`Book` ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Genrecode`)
	REFERENCES `DBMSProject`.`Genre` (`GenreCode`);

ALTER TABLE `DBMSProject`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_2` FOREIGN KEY (`AuthorID`)
	REFERENCES `DBMSProject`.`Author` (`AuthorID`);

ALTER TABLE `DBMSProject`.`WrittenBy` ADD CONSTRAINT `writtenby_ibfk_1` FOREIGN KEY (`BookID`)
	REFERENCES `DBMSProject`.`Book` (`BookID`);

