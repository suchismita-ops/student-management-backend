-- =========================================================
-- Student Management System - MySQL Workbench Setup Script
-- =========================================================

-- 1. Create Database
CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;

-- Note: Spring Boot JPA (spring.jpa.hibernate.ddl-auto=update)
-- automatically creates the `user` and `student` tables and seeds
-- default accounts on startup via DataInitializer.java.
-- You can also run the statements below in MySQL Workbench if you
-- want to inspect or manually initialize the schema.

CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS student (
    roll_no INT PRIMARY KEY,
    username VARCHAR(255),
    name VARCHAR(255),
    department VARCHAR(100),
    semester INT,
    java_marks DOUBLE DEFAULT 0,
    dbms_marks DOUBLE DEFAULT 0,
    daa_marks DOUBLE DEFAULT 0,
    os_marks DOUBLE DEFAULT 0,
    web_marks DOUBLE DEFAULT 0,
    attendance DOUBLE DEFAULT 0
);

-- View all users and students
SELECT * FROM user;
SELECT * FROM student;
