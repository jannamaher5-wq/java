
CREATE TABLE Manger1 (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    age NUMBER,
    birth_date DATE,
    address VARCHAR2(200)
);
SELECT * FROM Manger1;

ALTER TABLE Manger1 
DROP COLUMN address;


ALTER TABLE Manger1 
ADD (
    city_address VARCHAR2(100),
    street VARCHAR2(100)
);


ALTER TABLE Manger1 
RENAME COLUMN name TO full_name;

-
CREATE TABLE Owner AS 
SELECT id, full_name AS name, birth_date 
FROM Manger1 
WHERE 1 = 0;


RENAME Manger1 TO Master1;


ALTER TABLE Master1 READ ONLY;


DROP TABLE Owner;
DROP TABLE Master1;