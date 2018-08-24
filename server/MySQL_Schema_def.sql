create table Family (
	ID INT(5) NOT NULL AUTO_INCREMENT,
	CONSTRAINT PK_FAMILY PRIMARY KEY (ID)
);

create table Father (
	ID INT(5) NOT NULL,
    BirthDate DATE NOT NULL,
    FirstName VARCHAR(32) NOT NULL,
    SecondName VARCHAR(32) NOT NULL,
    PESEL CHAR(11) NOT NULL,
    
	CONSTRAINT PK_FATHER PRIMARY KEY (ID),
	CONSTRAINT FK_FATHER_FAMILY FOREIGN KEY (ID) REFERENCES Family (ID) ON DELETE CASCADE
);

create table Child (
	ID INT(5) NOT NULL,
    BirthDate DATE NOT NULL,
    FirstName VARCHAR(32) NOT NULL,
    SecondName VARCHAR(32) NOT NULL,
    PESEL CHAR(11) NOT NULL,
    Sex VARCHAR(1) NOT NULL DEFAULT "F",
    
	CONSTRAINT PK_CHILD PRIMARY KEY (PESEL),
	CONSTRAINT FK_CHILD_FAMILY FOREIGN KEY (ID) REFERENCES Family (ID) ON DELETE CASCADE
);

ALTER TABLE Family AUTO_INCREMENT = 1;
