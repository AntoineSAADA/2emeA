delimiter //
-- 2
create or replace function abac (bacS varchar(42)) returns int
begin 
    declare res int(6);
    select count(*) from ELEVE where bac = bacS into res;
    return res;
END //

-- 3
create or replace function nbHEleve (numS int(9)) returns int
begin 
    declare res int(6);
    select sum(nbheureseffectuees) from SUIVRE where num = numS into res;
    return res;
END //

--4
create or replace procedure matiereSuivit(numS int(9))
begin 
    declare res varchar(4200) default '';
    declare ref int(9);
    declare nm VARCHAR(42);
    declare finit boolean default false;
    declare cur1 cursor for select nom from SUIVRE natural join MATIERE where num = numS;
    declare continue handler for not found set finit = true;
    open cur1;
    while not finit do
        fetch cur1 into nm;
        if not finit then 
            set res = concat(res,nm,',');
        end if;
    end while;
    close cur1;   
    select res;
END //

--5
create or replace TRIGGER pasDeuxNomsIdentiques BEFORE INSERT ON MATIERE FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM MATIERE WHERE nom = NEW.nom) > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Il existe déjà une matière avec ce nom';
    END IF;
END //
    
END //

delimiter ; 

select abac('S');
select nbHEleve(1);
call matiereSuivit(1);
-- Insertion provoquant le trigger pasDeuxNomsIdentiques
insert into MATIERE values (9,'Mathématiques','Mathématiques',30);
