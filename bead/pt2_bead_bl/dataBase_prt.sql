/*USE `filmek`;*/
DROP TABLE `person`;
CREATE TABLE `person` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nev` varchar(255) NOT NULL,
  `típus` int(10) unsigned,
  PRIMARY KEY (`id`)
);

/*Data for the table `person` */

insert  into `person`(`id`,`nev`,`típus`) values 
/*Directors 1*/

(1,'Frank Darabont',1),

(2,'Francis Ford',1),

(3,'James Caan',1),

(4,'Christopher Nolan',1),

(5,'Sidney Lumet',1),

(6,'Steven Spielberg',1),

(7,'Quentin Tarantino',1),

(8,'Peter Jackson',1),

(9,'Sergio Leone',1),

(10,'David Fincher',1),

/*MainCharacters 0*/

(11,'Tim Robbins',0),
(12,'Morgan Freeman',0),

(13,'Marlon Brando',0),
(14,'Al Pacino',0),

(15,'Robert DeNiro',0),
(16,'Christian Bale',0),

(17,'Heath Ledger',0),
(18,'Marlon Brando',0),

(19,'Henry Fonda',0),
(20,'Lee J.Cobb',0),

(21,'Liam Neeson',0),
(22,'Ralph Fiennes',0),

(23,'John Travolta',0),
(24,'Uma Thurman',0),

(25,'Elijah Wood',0),
(26,'Viggo Mortensen',0),

(27,'Clint Eastwood',0),
(28,'Eli Wallach',0),

(29,'Brad Pitt',0),
(30,'Edward Norton',0),

/*Friends 2*/


(31,'Kiss Pista',2),
(32,'Sándor János',2),
(33,'Kengyel Zsuzsanna',2),
(34,'Horváth Alex',2),
(35,'Hajnal Emese',2),
(36,'Kántor Bálint',2);


/*Table structure for table `film` */

DROP TABLE `film`;

CREATE TABLE `film` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cim` varchar(255) NOT NULL,
  `rendezok` int(10) NOT NULL,
  `foszereplok` varchar(255) NOT NULL,
  `év` int(10) unsigned NOT NULL,
  `hossz` int(10) unsigned NOT NULL,
  `típus` varchar(3) NOT NULL,
  
  `kép` varchar(255) NOT NULL,
  `eredeti` tinyint(1) unsigned NOT NULL,
  `hanyszor` int(10) unsigned NOT NULL,
  `visszahozva` tinyint(1) unsigned NOT NULL,
  
  `kolcson_tol` varchar(255) NOT NULL,
  `kolcson_ig` varchar(255) NOT NULL,
  `kolcson_ki` int(10) unsigned NOT NULL,
  
  PRIMARY KEY (`id`)
);

/*Data for the table `film` */

insert  into `film`(`id`,`cim`,`rendezok`,`foszereplok`,`év`,`hossz`,`típus`,`kép`,`eredeti`,`hanyszor`,`visszahozva`,`kolcson_tol`,`kolcson_ig`,`kolcson_ki`) values 

(1,'The Shawshank Redemption',1,'11,12',1994,160,'DVD','redemption.jpg',1,9,1,'','',31),

(2,'The Godfather',2,'13,14',1972,220,'VHS','goldfather.jpg',0,6,0,'2015-03-01','2015-03-22',32),

(3,'The Godfather: Part II',2,'14,15',1974,190,'DVD','goldfather2.jpg',0,1,1,'','',31),

(4,'The Dark Knight',4,'17,18',2008,250,'DVD','knight.jpg',1,3,0,'2016-03-21','2016-04-26',32),

(5,'12 Angry Men',5,'19,20',1957,170,'DVD','men.jpg',1,0,1,'','',33),

(6,'Schindler\s List',6,'21,22',1993,210,'VHS','list.jpg',0,0,0,'2017-06-12','2017-07-01',34),

(7,'Pulp Fiction',7,'23,24',1994,260,'DVD','fiction.jpg',1,0,0,'2018-01-09','2018-02-07',35),

(8,'The Lord of the Rings: The Return of the King',8,'25,26',2003,170,'VHS','rings.jpg',1,0,1,'','',36),

(9,'Il buono, il brutto, il cattivo',9,'27,28',1966,150,'VHS','il.jpg',1,0,0,'2017-03-20','2017-03-21',36),

(10,'Fight Club',10,'29,30',1999,210,'DVD','club.jpg',1,0,1,'','',34);

