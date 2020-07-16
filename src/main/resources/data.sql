 CREATE DATABASE company CHARACTER SET utf8 COLLATE utf8_general_ci;

 CREATE TABLE IF NOT EXISTS employee ( 
            first_name varchar(25), 
            last_name  varchar(25), 
            department varchar(15), 
            email  varchar(50),
            id INT AUTO_INCREMENT PRIMARY KEY
            );

CREATE TABLE IF NOT EXISTS profile_image (
            id INT AUTO_INCREMENT PRIMARY KEY,
            profile_photo MEDIUMBLOB NOT NULL,
            employee_id INT UNIQUE ,
            FOREIGN KEY(employee_id)REFERENCES employee(id)
           );

CREATE TABLE IF NOT EXISTS employee_file (
            id INT AUTO_INCREMENT PRIMARY KEY,
            file_name TEXT NOT NULL,
            employee_id INT UNIQUE,
            FOREIGN KEY(employee_id)REFERENCES employee(id)
             );

CREATE or REPLACE PROCEDURE "CREATE_EMP"
(first_name IN VARCHAR,
last_name IN VARCHAR,
department IN VARCHAR,
email IN VARCHAR,
)
IS
BEGIN
INSERT INTO employee (first_name, last_name, department, email) VALUES (first_name, last_name, department, email);
END;

CREATE or REPLACE FUNCTION sum4
(n1 in number,n2 in number)
return number
is
temp number(8);
begin
temp :=n1+n2;
return temp;
end;


