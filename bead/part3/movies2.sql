ALTER TABLE Rental DROP CONSTRAINT FK_Rental_Movie;

ALTER TABLE Movie DROP CONSTRAINT PK_Movie;
ALTER TABLE Rental DROP CONSTRAINT PK_Rental;

DROP TABLE Movie;
DROP TABLE Rental;

CREATE TABLE Movie
(
    id INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    directors VARCHAR(255) NOT NULL,
    actors VARCHAR(255) NOT NULL,
    release_year INTEGER NOT NULL,
    length_in_mins INTEGER NOT NULL,
    cover VARCHAR(255) NOT NULL,
    original BOOLEAN NOT NULL,
    rental_number INTEGER,
    available BOOLEAN
);

CREATE TABLE Rental
(
    id INTEGER NOT NULL,
    movie_ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    rent_date DATE NOT NULL,
    return_date DATE
);

ALTER TABLE Movie ADD CONSTRAINT PK_Movie PRIMARY KEY (id);
ALTER TABLE Rental ADD CONSTRAINT PK_Rental PRIMARY KEY (id);

ALTER TABLE Rental ADD CONSTRAINT FK_Rental_Movie FOREIGN KEY (movie_ID) REFERENCES Movie (id);



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

-- INSERT INTO Movies (movie_ID, title, directors, actors, release_year, length_in_mins, cover, original, rental_number, available) VALUES
-- (1, 'Bad Moms', 'Jon Lucas', 'Mila Kunis', 2016, 100, 'url', true, 0, false),
-- (2, 'Deadpool', 'Tim Miller', 'Ryan Reynolds', 2016, 108, 'url', true, 0, false),
-- (3, 'White Chicks', 'Keenen Ivory Wayans', 'Marlon Wayans', 2004, 109, 'url', true, 0, false);

-- INSERT INTO Rental (rental_ID, movie_ID, person, rent_date, return_date) VALUES
-- (1, 1, 'Dixie Normous', '2016-11-12', '2016-12-12'),
-- (2, 2, 'Evely Sackrider', '2016-11-13', '2016-12-13'),
-- (3, 2, 'Diana Dbag', '2016-11-14', NULL),
-- (4, 1, 'Destinee Hooker', '2016-11-15', '2016-12-15'),
-- (5, 3, 'Ben Dover', '2016-11-16', '2016-12-16'),
-- (6, 1, 'Mike Litoris', '2016-11-17', '2016-12-17')
-- (7, 2, 'Moe Lester', '2016-11-18', NULL),
-- (8, 2, 'Anurag Dikshit', '2016-11-19', NULL);