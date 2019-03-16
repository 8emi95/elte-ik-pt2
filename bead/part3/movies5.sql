ALTER TABLE Movie DROP CONSTRAINT FK_Movie_Director;
ALTER TABLE Movie DROP CONSTRAINT FK_Movie_Actor;
ALTER TABLE Actor DROP CONSTRAINT FK_Actor_Movie;
ALTER TABLE Director DROP CONSTRAINT FK_Director_Movie;
ALTER TABLE Rental DROP CONSTRAINT FK_Rental_Movie;
ALTER TABLE Rental DROP CONSTRAINT FK_Rental_Person;
ALTER TABLE Person DROP CONSTRAINT FK_Person_Rental;

ALTER TABLE Movie DROP CONSTRAINT PK_Movie;
ALTER TABLE Actor DROP CONSTRAINT PK_Actor;
ALTER TABLE Director DROP CONSTRAINT PK_Director;
ALTER TABLE Rental DROP CONSTRAINT PK_Rental;
ALTER TABLE Person DROP CONSTRAINT PK_Person;

DROP TABLE Movie;
DROP TABLE Actor;
DROP TABLE Director;
DROP TABLE Rental;
DROP TABLE Person;

CREATE TABLE Movie
(
    id INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    director_ID INTEGER NOT NULL,
    actor_ID INTEGER NOT NULL,
    release_year INTEGER NOT NULL,
    length_in_mins INTEGER NOT NULL,
    cover VARCHAR(255) NOT NULL,
    original BOOLEAN NOT NULL,
    rental_number INTEGER,
    available BOOLEAN
);

CREATE TABLE Actor
(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_ID INTEGER NOT NULL
);

CREATE TABLE Director
(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_ID INTEGER NOT NULL
);

CREATE TABLE Rental
(
    id INTEGER NOT NULL,
    movie_ID INTEGER NOT NULL,
    person_ID INTEGER NOT NULL,
    rent_date DATE NOT NULL,
    return_date DATE
);

CREATE TABLE Person
(
    id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    rental_ID INTEGER NOT NULL
);

ALTER TABLE Movie ADD CONSTRAINT PK_Movie PRIMARY KEY (id);
ALTER TABLE Actor ADD CONSTRAINT PK_Actor PRIMARY KEY (id);
ALTER TABLE Director ADD CONSTRAINT PK_Director PRIMARY KEY (id);
ALTER TABLE Rental ADD CONSTRAINT PK_Rental PRIMARY KEY (id);
ALTER TABLE Person ADD CONSTRAINT PK_Person PRIMARY KEY (id);

ALTER TABLE Movie ADD CONSTRAINT FK_Movie_Director FOREIGN KEY (director_ID) REFERENCES Director (id);
ALTER TABLE Movie ADD CONSTRAINT FK_Movie_Actor FOREIGN KEY (actor_ID) REFERENCES Actor (id);
ALTER TABLE Actor ADD CONSTRAINT FK_Actor_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (id);
ALTER TABLE Director ADD CONSTRAINT FK_Director_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (id);
ALTER TABLE Rental ADD CONSTRAINT FK_Rental_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (id);
ALTER TABLE Rental ADD CONSTRAINT FK_Rental_Person FOREIGN KEY (person_ID) REFERENCES Person (id);
ALTER TABLE Person ADD CONSTRAINT FK_Person_Rental FOREIGN KEY (rental_ID) REFERENCES Rental (id);

/*
The Hunger Games
Gary Ross
Jennifer Lawrence
2012
142
https://ia.media-imdb.com/images/M/MV5BMjA4NDg3NzYxMF5BMl5BanBnXkFtZTcwNTgyNzkyNw@@._V1_SY1000_CR0,0,674,1000_AL_.jpg

White Chicks
Keenen Ivory Wayans
Marlon Wayans, Shawn Wayans
2004
109
https://ia.media-imdb.com/images/M/MV5BMTY3OTg2OTM3OV5BMl5BanBnXkFtZTYwNzY5OTA3._V1_.jpg

Lissi und der wilde Kaiser
Michael Herbig
Lotte Ledl, Waldemar Kobus
2007
82
https://ia.media-imdb.com/images/M/MV5BMTUxMjc5NzQ2M15BMl5BanBnXkFtZTcwODQ3NzU4MQ@@._V1_.jpg

Deadpool
Tim Miller
Ryan Reynolds
2016
108
https://ia.media-imdb.com/images/M/MV5BYzE5MjY1ZDgtMTkyNC00MTMyLThhMjAtZGI5OTE1NzFlZGJjXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SY1000_CR0,0,666,1000_AL_.jpg

Bad Moms
Jon Lucas
Mila Kunis, Kathryn Hahn, Kristen Bell, Lilly Singh
2016
100
https://ia.media-imdb.com/images/M/MV5BMjIwNzE5MTgwNl5BMl5BanBnXkFtZTgwNjM4OTA0OTE@._V1_SY1000_CR0,0,675,1000_AL_.jpg

Dixie Normous
Evely Sackrider
Diana Dbag
Destinee Hooker
Ben Dover
Mike Litoris
Moe Lester
Anurag Dikshit
*/

-- https://stackoverflow.com/questions/1997998/insert-data-into-tables-linked-by-foreign-key

-- INSERT INTO Movie (id, title, director_ID, actor_ID, release_year, length_in_mins, cover, original, rental_number, available) VALUES
-- (1, 'Bad Moms', 1, 1, 2016, 100, 'url', true, 0, false),
-- (1, 'Bad Moms', 1, 2, 2016, 100, 'url', true, 0, false),
-- (1, 'Bad Moms', 1, 3, 2016, 100, 'url', true, 0, false),
-- (1, 'Bad Moms', 1, 4, 2016, 100, 'url', true, 0, false),
-- (2, 'Deadpool', 2, 5, 2016, 108, 'url', true, 0, false),
-- (3, 'White Chicks', 3, 6, 2004, 109, 'url', true, 0, false),
-- (3, 'White Chicks', 3, 7, 2004, 109, 'url', true, 0, false);

-- INSERT INTO Actor (id, name, movie_ID) VALUES
-- (1, 'Mila Kunis', 1),
-- (2, 'Kathryn Hahn', 1),
-- (3, 'Kristen Bell', 1),
-- (4, 'Lilly Singh', 1),
-- (5, 'Ryan Reynolds', 2),
-- (6, 'Marlon Wayans', 3),
-- (7, 'Shawn Wayans', 3);

-- INSERT INTO Director (id, name, movie_ID) VALUES
-- (1, 'Jon Lucas', 1),
-- (2, 'Tim Miller', 2),
-- (3, 'Keenen Ivory Wayans', 3);

-- INSERT INTO Rental (id, movie_ID, person_ID, rent_date, return_date) VALUES
-- (1, 1, 1, '2018-05-12', '2018-06-12'),
-- (2, 2, 2, '2018-05-13', '2018-06-13'),
-- (3, 2, 3, '2018-05-14', NULL),
-- (4, 1, 1, '2018-05-15', '2018-06-15'),
-- (5, 3, 5, '2018-05-16', '2018-06-16'),
-- (6, 1, 6, '2018-05-17', '2018-06-17'),
-- (7, 2, 7, '2018-05-18', NULL),
-- (8, 2, 8, '2018-05-19', NULL);

-- INSERT INTO Person (id, name, rental_ID) VALUES
-- (1, 'Dixie Normous', 1),
-- (2, 'Evely Sackrider', 1),
-- (3, 'Diana Dbag', 1),
-- (1, 'Destinee Hooker', 1),
-- (5, 'Ben Dover', 1),
-- (6, 'Mike Litoris', 1),
-- (7, 'Moe Lester', 1),
-- (8, 'Anurag Dikshit', 2);