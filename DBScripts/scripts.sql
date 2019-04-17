create table employee (
id INT(10) NOT NULL AUTO_INCREMENT,
user_name varchar(30) not null,
password varchar(30) not null,
full_name varchar(30) not null,
emailid varchar(30) not null,
dob date not null,
gender varchar(6),
security_question varchar(30) not null,
security_answer varchar(30) not null,
primary key(id)) auto_increment=1;