-- Exercice 1
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
    declare code int;
    declare nom VARCHAR(42);
    declare departement VARCHAR(42);
    declare res VARCHAR(500) default '';
    declare finit boolean default false;
    declare lesEntrepots cursor for 
    select code, nom, departement 
    from ENTREPOT;
    declare continue handler for not found set finit = true;
    open lesEntrepots;
    while not finit do
        fetch lesEntrepots into code, nom, departement;
        if not finit then
            set res = concat(res, 'Entrepot ', code, ' : ', nom, ' (', departement, ')');
        end if;
    end while;
    close lesEntrepots;
    select res;
end//
delimiter ;

call selectAllEntrepot();

