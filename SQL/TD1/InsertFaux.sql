--1
insert into Reservable values(51,1);
--Contrainte
ALTER TABLE Reservable ADD FOREIGN KEY(idT) REFERENCES Typet(idT);


--2
insert into Occupant values(4,'Miaou','Miaule',16462);
--Contrainte
ALTER TABLE Occupant ADD FOREIGN KEY(idT) REFERENCES Typet(idT);

--3
insert into Typet values(1,'Accueil periscol');
--Contrainte
    typeT varchar(100) unique  

--4
insert into Salle values(5,'Salle des fêtes',100);
--Contrainte
    nomSalle varchar(100) unique,

--5
insert into Reserver values(1,'2022-06-04',10,500000,24,90);
--Contrainte
ALTER TABLE Reserver ADD FOREIGN KEY(idO) REFERENCES Occupant(idO);

--6
insert into Reserver values(500000,'2022-06-04',10,3,24,90);
--Contrainte 
ALTER TABLE Reserver ADD FOREIGN KEY(idS) REFERENCES Salle(idS);

--7

--Contrainte dans la table Reserver 
    CHECK (heure + duree <= 24)

--8
--Possible et solution = Trigger

--9
--Possible, mais l'heure est arrondie (Sinon trigger)

--10
    nppers int NOT NULL 

--11
--Possible donc solution = Trigger 

--12
--Possible et solution = Trigger








