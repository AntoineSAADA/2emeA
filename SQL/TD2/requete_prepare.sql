-- Exercice 2
--1
prepare articleInf from 'select * from ARTICLE where prix < ?';
set @prix = 50;
execute articleInf using @prix;

--2
prepare selectionLibeleDepart from 'select quantite from STOCKER natural join ENTREPOT natural join ARTICLE where departement = ? and libelle = ?';
set @departement = 'Loiret';
set @libelle = 'Chaise';
execute selectionLibeleDepart using @departement, @libelle;

--Exercice 2
--1

delimiter // 
CREATE OR REPLACE function maxRefArticle() returns int
begin
    declare maxRef int;
    select max(reference) into maxRef from ARTICLE;
    return maxRef;
end//
delimiter ; 

select maxRefArticle();

--2
delimiter //
CREATE OR REPLACE function deptEntrepot(codeEnt int) returns VARCHAR(42)
begin
    declare dept VARCHAR(42);
    select departement into dept from ENTREPOT where code = codeEnt;
    if dept is null then
        return concat('Entrepot ',codeEnt,' inexistant');
    else
        return dept;
    end if;
    return dept;
end//
delimiter ;
select deptEntrepot(1);

--3
delimiter //
CREATE OR REPLACE function valEntrepot(codeEnt int) returns int
begin
    declare val int;
    select sum(prix*quantite) into val from STOCKER natural join ARTICLE where code = codeEnt;
    return val;
end//
delimiter ;
select valEntrepot(1);

--4
delimiter //
CREATE OR REPLACE procedure selectAllEntrepot() 
begin
    declare codeE int default 0;
    declare nomE VARCHAR(42) default '';
    declare departementE VARCHAR(42) default '';
    declare res VARCHAR(500) default '';

    declare finit boolean default false;

    declare lesEntrepots cursor for select code, nom, departement from ENTREPOT;

    declare continue handler for not found set finit=true;
    open lesEntrepots;

    while not finit do
        fetch lesEntrepots into codeE, nomE, departementE;

        if not finit then
            set res = concat(res, 'Entrepot ', codeE, ' : ', nomE, ' (', departementE, ')\n');
        end if;

    end while;
    close lesEntrepots;

    select res;
end //
delimiter ;

call selectAllEntrepot();


--5
delimiter //
CREATE OR REPLACE procedure selectOrderDep() 
begin
    declare departementE VARCHAR(42) default '';
    declare res VARCHAR(500) default '';
    declare codeE int default 0;

    declare finit boolean default false;

    declare lesEntrepots cursor for select count(code), departement from ENTREPOT group by departement order by departement;

    declare continue handler for not found set finit=true;
    open lesEntrepots;

    while not finit do
        fetch lesEntrepots into codeE, departementE;

        if not finit then
            set res = concat(res, 'Le departement ', departementE, ' a ', codeE, ' entrepots\n');
        end if;

    end while;
    close lesEntrepots;

    select res;

end //
delimiter ;

call selectOrderDep();

--6

delimiter //
CREATE OR REPLACE procedure selectOrderDepVal() 
begin
    declare departementE VARCHAR(42) default '';
    declare res VARCHAR(500) default '';
    declare codeE int default 0;
    declare valE int default 0;

    declare finit boolean default false;

    declare lesEntrepots cursor for select count(distinct code), departement, sum(prix*quantite) from ENTREPOT natural left join STOCKER natural join ARTICLE group by departement order by departement;

    declare continue handler for not found set finit=true;
    open lesEntrepots;

    while not finit do
        fetch lesEntrepots into codeE, departementE , valE;

        if not finit then
            set res = concat(res, 'Le departement ', departementE, ' a ', codeE, ' entrepots et a une valeur de ', valE,'$\n');
        end if;

    end while;
    close lesEntrepots;

    select res;
end //
delimiter ;

call selectOrderDepVal();


-- 7

delimiter //
create or replace function majArticle(idArticle int(9),nomArticle varchar(42), prixArticle DECIMAL (6 ,2)) returns int
begin
    declare idExiste int;
    declare nomExiste int;
    declare refPlusGrande int;
    select count(reference) from ARTICLE where reference = idArticle into idExiste;
    if idExiste >= 1 then
        select count(libelle) from ARTICLE where reference = idArticle and libelle = nomArticle into nomExiste;
        if nomExiste >= 1 then
            update ARTICLE set prix = prixArticle where reference = idArticle and libelle = nomArticle;
        end if;
        if nomExiste = 0 then
            return -1;
        end if;
    end if;
    if idExiste = 0 then
        select max(reference) from ARTICLE into refPlusGrande;
        set refPlusGrande = refPlusGrande + 1;
        INSERT INTO ARTICLE VALUES (idArticle, nomArticle, prixArticle);
        return refPlusGrande;
    end if;
    return idArticle;
end //
delimiter ;
select majArticle(5,'Chais',92); --  Tous les cas marchent (Message d'erreur, rajout de ligne dans la BD).



-- 8

delimiter //
create or replace function entrerStock(refA int, codeE int, qte int) returns int(5)
begin
    declare idExiste int;
    declare refExiste int;
    declare nvQuantite int(5);
    select count(codeE) from STOCKER where reference = refA into refExiste;
    select count(refA) from STOCKER where code = codeE into idExiste;
    if idExiste >= 1 then
        update STOCKER set quantite = quantite + qte where reference = refA and code = codeE;
        select quantite from STOCKER where reference = refA and code = codeE into nvQuantite;
    end if;
    if idExiste = 0 or refExiste = 0 then
        return -1;
    end if;
    return nvQuantite;
end //
delimiter ;

select(entrerStock(10,1,5));

--9 VOIR SUR TELEPHONE
delimiter //
create or replace function sortirStock(refA int, codeE int, qte int) returns int(5)
begin
    declare idExiste int;
    declare refExiste int;
    declare nvQuantite int(5);
    select count(codeE) from STOCKER where reference = refA into refExiste;
    select count(refA) from STOCKER where code = codeE into idExiste;
    if idExiste >= 1 then
        update STOCKER set quantite = quantite - qte where reference = refA and code = codeE;
        select quantite from STOCKER where reference = refA and code = codeE into nvQuantite;
    end if;
    if idExiste = 0 or refExiste = 0 then
        return -1;
    end if;
    return nvQuantite;
end //
delimiter ;

--Exercice 3

--1
delimiter //
CREATE OR REPLACE TRIGGER plusieurEntrepots BEFORE INSERT ON ENTREPOT FOR EACH ROW
begin
    declare nbEntrepots int;
    declare mes varchar(42);
    select count(code) from ENTREPOT where departement = new.departement and nom = new.nom into nbEntrepots;
    if nbEntrepots > 1 then
        set mes = concat('Le departement ', new.departement, ' a plus d''un entrepot');
        signal sqlstate '45000' set message_text = mes;
        delete from ENTREPOT where code = new.code;
    end if;
end //
delimiter ;

drop TRIGGER plusieurEntrepots;

--2
delimiter //
CREATE OR REPLACE TRIGGER pasPlusTroisEntrepots BEFORE INSERT ON ENTREPOT FOR EACH ROW
begin
    declare nbEntrepots int;
    declare mes varchar(42);
    select count(code) from ENTREPOT into nbEntrepots;
    if nbEntrepots > 3 then
        set mes = concat('Il y a plus de trois entrepots');
        signal sqlstate '45000' set message_text = mes;
        delete from ENTREPOT where code = new.code;
    end if;
end //
delimiter ;

drop TRIGGER pasPlusTroisEntrepots;

--3

--A chaque fois que le stock d’un article est modifie (a la hausse ou a la baisse), on veut conserver une trace de la mise a jour.

delimiter //
CREATE OR REPLACE TRIGGER stockModifie AFTER UPDATE ON STOCKER FOR EACH ROW
begin
    declare mes varchar(100);
    declare stockInitial INT(5);
    select quantite into stockInitial from STOCKER where reference = new.reference and code = new.code;
    insert into HISTORIQUE values (new.reference, old.quantite, stockInitial);
end //
delimiter ;

update STOCKER set quantite = quantite + 100 where reference = 1 and code = 1;