/*3. a) */ 

SELECT user.name, user.surname, etaireia.name , job.id, job.salary, count(distinct cand_usrname )
    FROM job
	INNER JOIN recruiter ON recruiter.username = job.recruiter
	INNER JOIN user ON recruiter.username=user.username
 	INNER JOIN etaireia ON recruiter.firm = etaireia.AFM
   	INNER JOIN applies ON applies.job_id=job.id
	WHERE salary>'1900'
   	GROUP BY job.id;


/*b)*/ 
	SELECT username, certificates, count(degr_title), avg(has_degree.grade)
	FROM candidate
	INNER JOIN has_degree ON has_degree.cand_usrname = candidate.username
   	GROUP BY username
	HAVING count(degr_title)>1;

	
/*c)*/
	SELECT candidate.username, count(applies.job_id), avg(job.salary)
 	FROM candidate
 	INNER JOIN applies ON applies.cand_usrname = candidate.username
 	INNER JOIN job ON applies.job_id = job.id
   	GROUP BY candidate.username
	HAVING avg(job.salary)> 1800;


/*d)*/
	SELECT etaireia.name, antikeim.title, job.position
	FROM etaireia
	INNER JOIN recruiter ON recruiter.firm = etaireia.AFM
 	INNER JOIN  job ON job.recruiter = recruiter.username
 	INNER JOIN requires ON requires.job_id = job.id
 	INNER JOIN antikeim ON antikeim.title = requires.antikeim_title
	WHERE etaireia.city = 'Patra' AND antikeim.title LIKE '% Program %'
	GROUP BY etaireia.name;


/*e)*/
	SELECT recruiter.username, count(distinct job.id) AS positions, count(distinct interview.A_A) AS interviews, avg(job.salary)
 	FROM recruiter
	INNER JOIN job ON job.recruiter = recruiter.username
	INNER JOIN interview ON interview.recruiter_username = recruiter.username
	group by username
	HAVING  count(distinct interview.A_A) > 2
    ORDER BY avg(job.salary) DESC;


	
#######################################################################################################################################
 #										TRIGGERS																					#
########################################################################################################################################

use erecruit;
DELIMITER $
drop trigger if exists User_Insert;
CREATE TRIGGER User_Insert
after INSERT ON `user`
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();    
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Insert', 'user', 'Yes');
END$

drop trigger if exists User_Update;
CREATE TRIGGER User_Update
after UPDATE ON `user`
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Update', 'user', 'Yes');
END$
 

drop trigger if exists User_Delete;
CREATE TRIGGER User_Delete
before DELETE ON `user`
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);  
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Delete', 'user', 'Yes');
END$
 
  
drop trigger if exists Candidate_Insert;
CREATE TRIGGER Candidate_Insert
after INSERT ON candidate
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Insert', 'candidate', 'Yes');
END$
 


  
drop trigger if exists Candidate_Update;
CREATE TRIGGER Candidate_Update
after UPDATE ON candidate
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  

    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Update', 'candidate', 'Yes');
END$
 


  
drop trigger if exists Candidate_Delete;
CREATE TRIGGER Candidate_Delete
before DELETE ON candidate
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);  
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Delete', 'candidate', 'Yes');
END$




  
drop trigger if exists Recruiter_Insert;
CREATE TRIGGER Recruiter_Insert
after INSERT ON recruiter
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();   
 SELECT username into curuser FROM loginHistory limit 1;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Insert', 'recruiter', 'Yes');
END$
 

  
drop trigger if exists Recruiter_Update;
CREATE TRIGGER Recruiter_Update
after UPDATE ON recruiter
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Update', 'recruiter', 'Yes');
END$
 

  
drop trigger if exists Recruiter_Delete;
CREATE TRIGGER Recruiter_Delete
before DELETE ON recruiter
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);  
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();   
 SELECT username into curuser FROM loginHistory limit 1;
  
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Delete', 'recruiter', 'Yes');
END$
 


  
drop trigger if exists Etaireia_Insert;
CREATE TRIGGER Etaireia_Insert
after INSERT ON etaireia
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Insert', ' etaireia', 'Yes');
END$
 

drop trigger if exists Etaireia_Update;
CREATE TRIGGER Etaireia_Update
before UPDATE ON etaireia
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  
  
  IF new.afm NOT LIKE old.afm THEN 
   
     set new.afm=old.afm;
	 INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
	 VALUES(curuser,curr_date, curr_time, 'Update', 'etaireia', 'No');
  
  
  ELSEIF new.DOY NOT LIKE old.DOY THEN 
   
     set new.DOY=old.DOY;   
	 INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
	 VALUES(curuser,curr_date, curr_time, 'Update', 'etaireia', 'No');
 
  
   ELSEIF new.name NOT LIKE old.name THEN 
   
     set new.name=old.name;   
	 INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
	 VALUES(curuser,curr_date, curr_time, 'Update', 'etaireia', 'No');
 
   ELSE
	 INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
	 VALUES(curuser,curr_date, curr_time, 'Update', 'etaireia', 'Yes');
   END IF;
  
END$

  
drop trigger if exists Etaireia_Delete;
CREATE TRIGGER Etaireia_Delete
before DELETE ON etaireia
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);  
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Delete', 'etaireia', 'Yes');
END$
 
drop trigger if exists Job_Insert;
CREATE TRIGGER Job_Insert
after INSERT ON job
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  

    SET curr_time= CURTIME(); 
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Insert', 'job', 'Yes');
END$
   
drop trigger if exists Job_Update;
CREATE TRIGGER Job_Update
after UPDATE ON job
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Update', 'job', 'Yes');
END$
   
drop trigger if exists Job_Delete;
CREATE TRIGGER Job_Delete
before DELETE ON job
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);  
  SET curr_date= CURDATE();  
    SET curr_time= CURTIME();  
SELECT username into curuser FROM loginHistory order by auto desc limit 1 ;
  
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
  VALUES(curuser,curr_date, curr_time, 'Delete', 'job', 'Yes');
END$
 
 
drop trigger if exists applies_Delete;
CREATE TRIGGER applies_Delete
before DELETE ON applies
FOR EACH ROW
BEGIN
  DECLARE curr_date DATE;
  DECLARE curr_time TIME;
  DECLARE curuser VARCHAR(12);
  declare sub DATE;
  declare id_thesis int(4);
  SET curr_date= CURDATE();  

    SET curr_time= CURTIME();   
 SELECT username into curuser FROM loginHistory limit 1;
  select job_id into id_thesis from applies where job_id=old.job_id and cand_usrname=old.cand_usrname limit 1;
  select submission_date into sub from job where id=id_thesis;

 if sub  <= curr_date THEN
         SIGNAL SQLSTATE VALUE '45000'
         SET MESSAGE_TEXT = 'You ARE NOT allowed to delete it.';
end if;
  INSERT INTO istoriko(who,ist_date, ist_time, energeia, table_nam, success)
    VALUES(curuser,curr_date, curr_time, 'Delete', 'applies', 'Yes');
    
END$
DELIMITER ;

#######################################################################################################################################
 #										STORED PROCEDURE																					#
########################################################################################################################################

USE erecruit;
DELIMITER $$
drop procedure if exists proc ;
CREATE PROCEDURE proc (IN idi int(4))
BEGIN


drop table if exists test;
drop table if exists failedOnes;
drop table if exists success;

CREATE table test select candidate_username, avg(personality) as av, count(candidate_username) as co from interview where job_id = idi group by candidate_username;

CREATE table success select interview.candidate_username, av, interview.education, interview.experience, co, av+education+experience as SUM from test 
inner join interview on interview.candidate_username = test.candidate_username where av!=0 AND education!=0 AND experience !=0  AND  job_id = idi order by SUM desc;


create table failedOnes select interview.candidate_username, interview.job_id, interview.education, interview.personality, interview.experience 
from interview where interview.job_id = idi  AND (interview.education = 0 OR interview.personality=0 OR interview.experience=0) AND  interview.candidate_username NOT IN (SELECT candidate_username from success);


select * from success group by candidate_username;

SELECT candidate_username,CONCAT_WS(',',IF(education='0','inadequate education=0',NULL),IF(personality='0','failed the interview=0',NULL),IF(experience='0','no prior experience',NULL)) AS Message FROM failedOnes;			
select applies.cand_usrname from applies where applies.job_id = idi AND  cand_usrname not in (select candidate_username from interview);

END $$

DELIMITER ;

