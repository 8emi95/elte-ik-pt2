DROP TABLE authorISBN;
DROP TABLE titles;
DROP TABLE authors;

CREATE TABLE authors (
   authorID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   firstName varchar (20) NOT NULL,
   lastName varchar (30) NOT NULL,
   PRIMARY KEY (authorID)
);

CREATE TABLE titles (
   ISBN varchar (20) NOT NULL,
   title varchar (100) NOT NULL,
   editionNumber INT NOT NULL,
   copyright varchar (4) NOT NULL,
   PRIMARY KEY (ISBN)
);

CREATE TABLE authorISBN (
   authorID INT NOT NULL,
   ISBN varchar (20) NOT NULL,
   FOREIGN KEY (authorID) REFERENCES authors (authorID), 
   FOREIGN KEY (ISBN) REFERENCES titles (ISBN)
);

INSERT INTO authors (firstName, lastName)
VALUES 
   ('Aladár','Kiss'), 
   ('Béla','Nagy'),
   ('Katalin','Molnár');

INSERT INTO titles (ISBN,title,editionNumber,copyright)
VALUES
   ('11111','Java',1,'2016'),
   ('22222','C++',1,'2015'),
   ('33333','Adatbázis-kezelés',2,'2010'),
   ('44444','Szoftverfejlesztés',3,'2000'); 
   
INSERT INTO authorISBN (authorID,isbn)
VALUES
   (1,'11111'),
   (1,'22222'),
   (1,'33333'),
   (2,'33333'),
   (3,'33333'),
   (2,'44444');
