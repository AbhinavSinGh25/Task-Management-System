Prerequisites:

1.Java Development Kit (JDK): Install JDK version 8 or higher.
2.Spring Boot: Download and install Spring Boot.
3.Database: Set up a database (MySQL) and configure the database connection in the application properties(Modify database name and login credentials as per your details).
Following are the queries for the two tables I have made

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT, -- Text data type for longer descriptions
    date_created DATE,
    due_date DATE,
    status VARCHAR(50)
);

IDE used:
1. Spring Tool Suite-Backend
2. Visual Studio Code-Frontend


Instructions:
1. Download the two folders :-
   i)TaskManagementSystem- Spring Boot Project( Run on STS)
   ii)Task Management System- Frontend(Run on visual studio code)
    Note:- Html and Css folders have been made for organisation of files only kindly cut and paste the files in the Task Management System folder only as the mapping has been done in that manner only
          Delete these Html and Css folder after cutting files out of it.

2. Ensure the connection of MySQL with the STS in application.properties
3. Run the server in STS and Visual Studio Code(home.html) is the main html page.(Vsc runs on port 5500 so direct links have been used as it is a personal project only)
4. STS is set to run on port 8090 so that should be kept in mind.
5.You are good to go now.

