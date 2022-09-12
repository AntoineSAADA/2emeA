--1
SELECT idO,nomO,idT,typeT FROM Occupant natural join Typet;

--2
Select * FROM Salle WHERE

--3
SELECT * FROM Occupant natural join Reserver natural join Salle
WHERE nomSalle = 'Salle des fêtes'
Union 
SELECT * FROM Occupant natural join Reserver natural join Salle
WHERE nomSalle = 'Salle Info';
--4
SELECT * FROM Occupant natural join Reserver natural join Salle
WHERE nomSalle = 'Salle des fêtes'
INTERSECT
SELECT idO,nomO FROM Occupant natural join Reserver natural join Salle
WHERE nomSalle = 'Salle Info';

--5
Select idO,nomO,caracteristiqueO
from Occupant natural join Reserver natural join Salle
WHERE nomSalle ='Salle des fetes'
EXCEPT 
select idO,nomO,caracteristiqueO
from Occupant natural join Reserver natural join Salle
where nomSalle = 'Salle Info';

--6
SELECT *
FROM Salle 
WHERE capacite = (Select max(capacite) from Salle);

--7
SELECT idS, MONTHNAME(dateD) as mois, sum(duree) as total
from Reserver natural join Salle
group by nomSalle,mois;

--8
select idO,count(distinct idS)
from Occupant natural join Reserver
group by idO
having count(distinct ids) = (select count(*) from Salle);


--Exercice 2

--1

SELECT * FROM Reserver
WHERE (heure + duree) > 24;

--2

SELECT * FROM Reserver natural join Salle
WHERE nppers > capacite;

--3

SELECT * FROM Reserver r natural join Reservable
WHERE r.typeT not in (SELECT * FROM Reserver r natural join Reservable 
                        WHERE ) 
