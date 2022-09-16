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

SELECT * FROM Reserver r natural join Occupant natural join Salle s1
WHERE Occupant.idT not in (SELECT idT FROM Reservable natural join Salle s2
                            WHERE s1.idS = s2.idS);

--4

-- Ecrire une requete pour afficher les couples de reservations différentes qui se chevauchent.
SELECT * FROM Reserver r1 natural join Reserver r2
WHERE r1.idS = r2.idS
AND r1.idO  != r2.idO
AND r1.dateD = r2.dateD
AND r1.heure < r2.heure
AND (r1.heure + r1.duree) > r2.heure;

