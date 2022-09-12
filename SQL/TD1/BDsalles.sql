DROP TABLE Reservable;
DROP TABLE Reserver;
DROP TABLE Salle;
DROP TABLE Occupant;
DROP TABLE Typet;

CREATE TABLE Salle(
    idS int,
    nomSalle varchar(100) unique,
    capacite int
);

CREATE TABLE Typet(
    idT int,
    typeT varchar(100) unique  
);

CREATE TABLE Occupant(
    idO int,
    nomO varchar(100),
    caracteristiqueO varchar(100),
    idT int
);  


CREATE TABLE Reserver(
    idS int,
    dateD date,
    heure int,
    idO int,
    duree int,
    nppers int NOT NULL ,
    CHECK (heure + duree <= 24)
    CHECK ()
);

CREATE TABLE Reservable(
    idT int,
    idS int
);

ALTER TABLE Salle ADD PRIMARY KEY(idS);
ALTER TABLE Occupant ADD PRIMARY KEY(idO);
ALTER TABLE Typet ADD PRIMARY KEY(idT);
ALTER TABLE Reserver ADD PRIMARY KEY(idS,dateD,heure);
ALTER TABLE Reservable ADD PRIMARY KEY(idT,idS);
ALTER TABLE Reserver ADD FOREIGN KEY(idS) REFERENCES Salle(idS);
ALTER TABLE Occupant ADD FOREIGN KEY(idT) REFERENCES Typet(idT);
ALTER TABLE Reserver ADD FOREIGN KEY(idO) REFERENCES Occupant(idO);
ALTER TABLE Reservable ADD FOREIGN KEY(idT) REFERENCES Typet(idT);

