-- ALTER TABLE Movie DROP CONSTRAINT SQL180512135759040;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512135759100;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512135759150;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512135955580;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512135955670;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512135955710;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141503490;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141503520;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141503560;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141704070;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141704120;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141704160;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141750630;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141750680;
-- ALTER TABLE Movie DROP CONSTRAINT SQL180512141750720;
-- ALTER TABLE Movie DROP CONSTRAINT FFFFFFF;
-- ALTER TABLE Actor DROP CONSTRAINT SQL180512135759000;
-- ALTER TABLE Actor DROP CONSTRAINT SQL180512135955550;
-- ALTER TABLE Actor DROP CONSTRAINT SQL180512141503460;
-- ALTER TABLE Actor DROP CONSTRAINT SQL180512141704030;
-- ALTER TABLE Actor DROP CONSTRAINT SQL180512141750600;
-- ALTER TABLE Actor DROP CONSTRAINT DDDDDDDD;
-- ALTER TABLE Director DROP CONSTRAINT SQL180512135758860;
-- ALTER TABLE Director DROP CONSTRAINT SQL180512135955510;
-- ALTER TABLE Director DROP CONSTRAINT SQL180512141503440;
-- ALTER TABLE Director DROP CONSTRAINT SQL180512141704000;
-- ALTER TABLE Director DROP CONSTRAINT SQL180512141750580;
-- ALTER TABLE Director DROP CONSTRAINT GGGGGG;
-- ALTER TABLE Rental DROP CONSTRAINT SQL180512135759290;
-- ALTER TABLE Rental DROP CONSTRAINT SQL180512135955790;
-- ALTER TABLE Rental DROP CONSTRAINT SQL180512141503610;
-- ALTER TABLE Rental DROP CONSTRAINT SQL180512141704280;
-- ALTER TABLE Rental DROP CONSTRAINT SQL180512141750840;
-- ALTER TABLE Rental DROP CONSTRAINT HHHHHHHH;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512135759250;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512135955740;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512141503590;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512141704220;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512141750770;
-- ALTER TABLE Person DROP CONSTRAINT JJJJJJJJJJ;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512135759250;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512135955740;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512141503590;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512141704220;
-- ALTER TABLE Person DROP CONSTRAINT SQL180512141750770;
-- ALTER TABLE Person DROP CONSTRAINT JJJJJJJJJJ;

-- alter table SQL180512135758800 drop constraint SQL180512135759250;
-- alter table SQL180512135758800 drop constraint SQL180512135955740;
-- alter table SQL180512135758800 drop constraint SQL180512141503590;
-- alter table SQL180512135758800 drop constraint SQL180512141704220;
-- alter table SQL180512135758800 drop constraint SQL180512141750770;

-- ALTER TABLE Movie DROP CONSTRAINT director_ID;
-- ALTER TABLE Movie DROP CONSTRAINT actor_ID;
-- ALTER TABLE Actor DROP CONSTRAINT movie_ID;
-- ALTER TABLE Director DROP CONSTRAINT movie_ID;
-- ALTER TABLE Rental DROP CONSTRAINT movie_ID;
-- ALTER TABLE Rental DROP CONSTRAINT person_ID;
-- ALTER TABLE Person DROP CONSTRAINT rental_ID;


DROP TABLE Movie;
DROP TABLE Actor;
DROP TABLE Director;
DROP TABLE Rental;
DROP TABLE Person;

CREATE TABLE Movie
(
    movie_ID INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    director_ID INTEGER NOT NULL,
    actor_ID INTEGER NOT NULL,
    release_year INTEGER NOT NULL,
    length INTEGER NOT NULL,
    cover VARCHAR(255) NOT NULL,
    original BOOLEAN NOT NULL,
    rental_number INTEGER,
    rented_now BOOLEAN,
    CONSTRAINT PK_Movie PRIMARY KEY (movie_ID),
    CONSTRAINT FK_Movie_Director FOREIGN KEY (director_ID) REFERENCES Director (director_ID),
    CONSTRAINT FK_Movie_Actor FOREIGN KEY (actor_ID) REFERENCES Actor (actor_ID)
);

CREATE TABLE Actor
(
    actor_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_ID INTEGER NOT NULL,
    CONSTRAINT PK_Actor PRIMARY KEY (actor_ID),
    CONSTRAINT FK_Actor_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID)
);

CREATE TABLE Director
(
    director_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_ID INTEGER NOT NULL,
    CONSTRAINT PK_Director PRIMARY KEY (director_ID),
    CONSTRAINT FK_Director_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID)
);

CREATE TABLE Rental
(
    rental_ID INTEGER NOT NULL,
    movie_ID INTEGER NOT NULL,
    person_ID INTEGER NOT NULL,
    rent_date DATE NOT NULL,
    return_date DATE,
    CONSTRAINT PK_Rental PRIMARY KEY (rental_ID),
    CONSTRAINT FK_Rental_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID),
    CONSTRAINT FK_Rental_Person FOREIGN KEY (person_ID) REFERENCES Person (person_ID)
);

CREATE TABLE Person
(
    person_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    rental_ID INTEGER NOT NULL,
    CONSTRAINT PK_Person PRIMARY KEY (person_ID),
    CONSTRAINT FK_Person_Rental FOREIGN KEY (rental_ID) REFERENCES Rental (rental_ID)
);

-- ALTER TABLE Movie
-- ADD CONSTRAINT PK_Movie PRIMARY KEY (movie_ID);
-- ALTER TABLE Actor
-- ADD CONSTRAINT PK_Actor PRIMARY KEY (actor_ID);
-- ALTER TABLE Director
-- ADD CONSTRAINT PK_Director PRIMARY KEY (director_ID);
-- ALTER TABLE Rental
-- ADD CONSTRAINT PK_Rental PRIMARY KEY (rental_ID);
-- ALTER TABLE Person
-- ADD CONSTRAINT PK_Person PRIMARY KEY (person_ID);

-- ALTER TABLE Movie
-- ADD CONSTRAINT FK_Movie_Director FOREIGN KEY (director_ID) REFERENCES Director (director_ID);
-- ALTER TABLE Movie
-- ADD CONSTRAINT FK_Movie_Actor FOREIGN KEY (actor_ID) REFERENCES Actor (actor_ID);
-- ALTER TABLE Actor
-- ADD CONSTRAINT FK_Actor_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID);
-- ALTER TABLE Director
-- ADD CONSTRAINT FK_Director_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID);
-- ALTER TABLE Rental
-- ADD CONSTRAINT FK_Rental_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID);
-- ALTER TABLE Rental
-- ADD CONSTRAINT FK_Rental_Person FOREIGN KEY (person_ID) REFERENCES Person (person_ID);
-- ALTER TABLE Person
-- ADD CONSTRAINT FK_Person_Rental FOREIGN KEY (rental_ID) REFERENCES Rental (rental_ID);

INSERT INTO Movie (movie_ID, title, director_ID, actor_ID, release_year, length, cover, original, rental_number, rented_now) VALUES
(1, 'Bad Moms', 1, 1, 2016, 100, 'url', true, 0, false),
(1, 'Bad Moms', 1, 2, 2016, 100, 'url', true, 0, false),
(1, 'Bad Moms', 1, 3, 2016, 100, 'url', true, 0, false),
(1, 'Bad Moms', 1, 4, 2016, 100, 'url', true, 0, false),
(2, 'Deadpool', 2, 5, 2016, 108, 'url', true, 0, false),
(3, 'White Chicks', 3, 6, 2004, 109, 'url', true, 0, false),
(3, 'White Chicks', 3, 7, 2004, 109, 'url', true, 0, false);

INSERT INTO Actor (actor_ID, name, movie_ID) VALUES
(1, 'Mila Kunis', 1),
(2, 'Kathryn Hahn', 1),
(3, 'Kristen Bell', 1),
(4, 'Lilly Singh', 1),
(5, 'Ryan Reynolds', 2),
(6, 'Marlon Wayans', 3),
(7, 'Shawn Wayans', 3);

INSERT INTO Director (director_ID, name, movie_ID) VALUES
(1, 'Jon Lucas', 1),
(2, 'Tim Miller', 2),
(3, 'Keenen Ivory Wayans', 3);

INSERT INTO Rental (rental_ID, movie_ID, person_ID, rent_date, return_date) VALUES
(1, 1, 1, '2018-05-12', '2018-06-12'),
(2, 2, 2, '2018-05-13', '2018-06-13'),
(3, 2, 3, '2018-05-14', NULL),
(4, 1, 1, '2018-05-15', '2018-06-15'),
(5, 3, 5, '2018-05-16', '2018-06-16'),
(6, 1, 6, '2018-05-17', '2018-06-17'),
(7, 2, 7, '2018-05-18', NULL),
(8, 2, 8, '2018-05-19', NULL);

INSERT INTO Person (person_ID, name, rental_ID) VALUES
(1, 'Dixie Normous', 1),
(2, 'Evely Sackrider', 1),
(3, 'Diana Dbag', 1),
(1, 'Destinee Hooker', 1),
(5, 'Ben Dover', 1),
(6, 'Mike Litoris', 1),
(7, 'Moe Lester', 1),
(8, 'Anurag Dikshit', 2);


-- *******************************************************************************************************

ALTER TABLE Movie DROP CONSTRAINT PK_Movie;
ALTER TABLE Actor DROP CONSTRAINT PK_Actor;
ALTER TABLE Director DROP CONSTRAINT PK_Director;
ALTER TABLE Rental DROP CONSTRAINT PK_Rental;
ALTER TABLE Person DROP CONSTRAINT PK_Person;

ALTER TABLE Movie DROP CONSTRAINT FK_Movie_Director;
ALTER TABLE Movie DROP CONSTRAINT FK_Movie_Actor;
ALTER TABLE Actor DROP CONSTRAINT FK_Actor_Movie;
ALTER TABLE Director DROP CONSTRAINT FK_Director_Movie;
ALTER TABLE Rental DROP CONSTRAINT FK_Rental_Movie;
ALTER TABLE Rental DROP CONSTRAINT FK_Rental_Person;
ALTER TABLE Person DROP CONSTRAINT FK_Person_Rental;

DROP TABLE Movie;
DROP TABLE Actor;
DROP TABLE Director;
DROP TABLE Rental;
DROP TABLE Person;

CREATE TABLE Movie
(
    movie_ID INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    director_ID INTEGER NOT NULL,
    actor_ID INTEGER NOT NULL,
    release_year INTEGER NOT NULL,
    length INTEGER NOT NULL,
    cover VARCHAR(255) NOT NULL,
    original BOOLEAN NOT NULL,
    rental_number INTEGER,
    rented_now BOOLEAN
);

CREATE TABLE Actor
(
    actor_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_ID INTEGER NOT NULL
);

CREATE TABLE Director
(
    director_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    movie_ID INTEGER NOT NULL
);

CREATE TABLE Rental
(
    rental_ID INTEGER NOT NULL,
    movie_ID INTEGER NOT NULL,
    person_ID INTEGER NOT NULL,
    rent_date DATE NOT NULL,
    return_date DATE
);

CREATE TABLE Person
(
    person_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    rental_ID INTEGER NOT NULL
);

ALTER TABLE Movie ADD CONSTRAINT PK_Movie PRIMARY KEY (movie_ID);
ALTER TABLE Actor ADD CONSTRAINT PK_Actor PRIMARY KEY (actor_ID);
ALTER TABLE Director ADD CONSTRAINT PK_Director PRIMARY KEY (director_ID);
ALTER TABLE Rental ADD CONSTRAINT PK_Rental PRIMARY KEY (rental_ID);
ALTER TABLE Person ADD CONSTRAINT PK_Person PRIMARY KEY (person_ID);

ALTER TABLE Movie ADD CONSTRAINT FK_Movie_Director FOREIGN KEY (director_ID) REFERENCES Director (director_ID);
ALTER TABLE Movie ADD CONSTRAINT FK_Movie_Actor FOREIGN KEY (actor_ID) REFERENCES Actor (actor_ID);
ALTER TABLE Actor ADD CONSTRAINT FK_Actor_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID);
ALTER TABLE Director ADD CONSTRAINT FK_Director_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID);
ALTER TABLE Rental ADD CONSTRAINT FK_Rental_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (movie_ID);
ALTER TABLE Rental ADD CONSTRAINT FK_Rental_Person FOREIGN KEY (person_ID) REFERENCES Person (person_ID);
ALTER TABLE Person ADD CONSTRAINT FK_Person_Rental FOREIGN KEY (rental_ID) REFERENCES Rental (rental_ID);

INSERT INTO Movie (movie_ID, title, director_ID, actor_ID, release_year, length, cover, original, rental_number, rented_now) VALUES
(1, 'Bad Moms', 1, 1, 2016, 100, 'url', true, 0, false),
(1, 'Bad Moms', 1, 2, 2016, 100, 'url', true, 0, false),
(1, 'Bad Moms', 1, 3, 2016, 100, 'url', true, 0, false),
(1, 'Bad Moms', 1, 4, 2016, 100, 'url', true, 0, false),
(2, 'Deadpool', 2, 5, 2016, 108, 'url', true, 0, false),
(3, 'White Chicks', 3, 6, 2004, 109, 'url', true, 0, false),
(3, 'White Chicks', 3, 7, 2004, 109, 'url', true, 0, false);

INSERT INTO Actor (actor_ID, name, movie_ID) VALUES
(1, 'Mila Kunis', 1),
(2, 'Kathryn Hahn', 1),
(3, 'Kristen Bell', 1),
(4, 'Lilly Singh', 1),
(5, 'Ryan Reynolds', 2),
(6, 'Marlon Wayans', 3),
(7, 'Shawn Wayans', 3);

INSERT INTO Director (director_ID, name, movie_ID) VALUES
(1, 'Jon Lucas', 1),
(2, 'Tim Miller', 2),
(3, 'Keenen Ivory Wayans', 3);

INSERT INTO Rental (rental_ID, movie_ID, person_ID, rent_date, return_date) VALUES
(1, 1, 1, '2018-05-12', '2018-06-12'),
(2, 2, 2, '2018-05-13', '2018-06-13'),
(3, 2, 3, '2018-05-14', NULL),
(4, 1, 1, '2018-05-15', '2018-06-15'),
(5, 3, 5, '2018-05-16', '2018-06-16'),
(6, 1, 6, '2018-05-17', '2018-06-17'),
(7, 2, 7, '2018-05-18', NULL),
(8, 2, 8, '2018-05-19', NULL);

INSERT INTO Person (person_ID, name, rental_ID) VALUES
(1, 'Dixie Normous', 1),
(2, 'Evely Sackrider', 1),
(3, 'Diana Dbag', 1),
(1, 'Destinee Hooker', 1),
(5, 'Ben Dover', 1),
(6, 'Mike Litoris', 1),
(7, 'Moe Lester', 1),
(8, 'Anurag Dikshit', 2);