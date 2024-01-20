-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 10, 2023 at 04:23 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `databaza`
--

-- --------------------------------------------------------
--
-- --------------------------------------------------------
--
-- Table structure for table `user`
--
CREATE TABLE `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255),
  `registration_date` DATETIME
);


INSERT INTO `user` (`first_name`, `last_name`, `username`, `password`, `email`, `registration_date`)
VALUES
  ('Livia', 'Lika', 'lika.livia', 'password123', 'lika.livia@gmail.com', '2023-01-10 12:00:00'),
  ('Albora', 'Bardhi', 'bora.bardhu', 'pass456', 'borabardh1@gmail.com', '2023-01-11 14:30:00'),
  ('Brisild', 'Velo', 'brisild.velo', 'velo123', 'brisild.velo@gmail.com', '2023-01-12 10:15:00'),
  ('Arb', 'Jaupaj', 'arbi.jaupaj', 'jaupajarb123', 'arbjaupaj8@gmaile.com', '2023-01-13 09:45:00'),
  ('Denisa', 'Kraja', 'denia.kraja', 'evapass', 'kraja01@gmail.com', '2023-01-14 16:00:00');
-- --------------------------------------------------------
--
-- Table structure for table `course`
--
CREATE TABLE `course` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `course_name` VARCHAR(255) NOT NULL,
  `description` TEXT,
  `lecturer` VARCHAR(255),
  `schedule` VARCHAR(255),
  `location` VARCHAR(255),
  `max_capacity` INT
);

INSERT INTO `course` (`course_name`, `description`, `lecturer`, `schedule`, `location`, `max_capacity`)
VALUES
  ('Introduction to Programming', 'Basic programming concepts and logic', 'Prof. Smith', 'Mon/Wed 10:00 AM - 12:00 PM', 'Room 101', 50),
  ('Database Management', 'Fundamentals of database design', 'Prof. Johnson', 'Tue/Thu 2:00 PM - 4:00 PM', 'Room 201', 40),
  ('Web Development', 'Building dynamic websites', 'Prof. Davis', 'Mon/Fri 1:00 PM - 3:00 PM', 'Room 301', 60),
  ('Data Science', 'Analyzing and interpreting complex data sets', 'Prof. Brown', 'Wed/Fri 9:00 AM - 11:00 AM', 'Room 401', 35),
  ('Software Engineering', 'Principles of software design and development', 'Prof. Wilson', 'Tue/Thu 3:00 PM - 5:00 PM', 'Room 501', 45);


-- --------------------------------------------------------
--
-- Table structure for table `registration_table`
--
CREATE TABLE `registration_table` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `registration_date` DATETIME,
  `user_id` INT,
  `course_id` INT,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
);
INSERT INTO `registration_table` (`registration_date`, `user_id`, `course_id`)
VALUES
  ('2023-01-10 13:30:00', 1, 1),
  ('2023-01-11 15:45:00', 2, 3),
  ('2023-01-12 11:30:00', 3, 2),
  ('2023-01-13 10:00:00', 4, 5),
  ('2023-01-14 16:30:00', 5, 4);

-- --------------------------------------------------------
--
-- Table structure for table `feedback_table`
--
CREATE TABLE `feedback_table` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `description` TEXT,
  `rating` INT,
  `feedback_date` DATETIME,
  `user_id` INT,
  `course_id` INT,
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
);

INSERT INTO `feedback_table` (`description`, `rating`, `feedback_date`, `user_id`, `course_id`)
VALUES
  ('Great course, loved it!', 5, '2023-01-11 10:30:00', 1, 1),
  ('Excellent teaching, very helpful', 4, '2023-01-13 12:45:00', 2, 3),
  ('Good content, but could be improved', 3, '2023-01-15 09:30:00', 3, 2),
  ('Enjoyed the practical exercises', 5, '2023-01-16 11:00:00', 4, 5),
  ('Challenging but rewarding', 4, '2023-01-17 17:15:00', 5, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
