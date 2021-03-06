-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2020 at 06:08 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testparkinator`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `car_id` int(11) NOT NULL,
  `car_reg` varchar(20) NOT NULL,
  `car_colour` varchar(100) NOT NULL,
  `car_make` varchar(100) NOT NULL,
  `car_model` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `clamping_companies`
--

CREATE TABLE `clamping_companies` (
  `cc_id` int(11) NOT NULL,
  `cc_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lot_managers`
--

CREATE TABLE `lot_managers` (
  `lot_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `parked_cars`
--

CREATE TABLE `parked_cars` (
  `zone_id` int(11) NOT NULL,
  `car_id` int(11) NOT NULL,
  `book_from` datetime NOT NULL,
  `book_to` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `parking_lots`
--

CREATE TABLE `parking_lots` (
  `lot_id` int(11) NOT NULL,
  `cc_id` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `parking_zones`
--

CREATE TABLE `parking_zones` (
  `zone_id` int(11) NOT NULL,
  `zone_name` varchar(30) NOT NULL,
  `max_spaces` int(11) NOT NULL,
  `is_vip` tinyint(1) NOT NULL,
  `lot_id` int(11) NOT NULL,
  `max_disabled_spaces` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salt`
--

CREATE TABLE `salt` (
  `user_id` int(11) NOT NULL,
  `salt` varchar(29) NOT NULL,
  `answer_salt` varchar(29) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salt`
--

INSERT INTO `salt` (`user_id`, `salt`, `answer_salt`) VALUES
(14, '$2a$12$JEV2Q3QjkQUeBt3ppnKdt.', '$2a$12$CAi4ItWkf9y5TDmtvDybye'),
(15, '$2a$12$jL1zKr8wqlta7zIG3RN8qu', '$2a$12$/BXnO.apyFUrX0SABxypUO'),
(16, '$2a$12$wZrluDWmlSL5Gja10diRvO', '$2a$12$0Z51IHVEQmQC6gfdmjtOZu');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_fullname` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `hash` varchar(60) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  `question` varchar(50) NOT NULL,
  `answer_hash` varchar(60) NOT NULL,
  `has_disabled_badge` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_fullname`, `email`, `hash`, `user_type`, `question`, `answer_hash`, `has_disabled_badge`) VALUES
(14, 'Michael Lawson', 'michael.c.k.lawson@gmail.com', '$2a$12$JEV2Q3QjkQUeBt3ppnKdt.tEw3w3CbHXhgwPK5eh.wDYctKp6knCy', 'admin', 'What is your oldest sibling\'s middle name?', '$2a$12$CAi4ItWkf9y5TDmtvDybyeBwRuOrF3.ZD7ih5OZxVjTCB7mFewXNq', 0),
(15, 'test1', 'test1@email.com', '$2a$12$jL1zKr8wqlta7zIG3RN8qu05ujys5GukLBD2wtTRvO2m4YSVolx9S', 'regular', 'What was your childhood nickname?', '$2a$12$/BXnO.apyFUrX0SABxypUOX0Y2ZSAmNoCU2NbBoZTo0H88x0Bu7WK', 0),
(16, 'Test2', 'test2@gmail.com', '$2a$12$wZrluDWmlSL5Gja10diRvO4e1NnbV0xLbtKnQeZNz8HIYMRHbjXQu', 'regular', 'What was your childhood nickname?', '$2a$12$0Z51IHVEQmQC6gfdmjtOZucqtS02hXKxMiRV5dIQxUSfj5kcoWcdi', 0);

-- --------------------------------------------------------

--
-- Table structure for table `vips`
--

CREATE TABLE `vips` (
  `zone_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`car_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `clamping_companies`
--
ALTER TABLE `clamping_companies`
  ADD PRIMARY KEY (`cc_id`);

--
-- Indexes for table `lot_managers`
--
ALTER TABLE `lot_managers`
  ADD KEY `lot_id` (`lot_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `parked_cars`
--
ALTER TABLE `parked_cars`
  ADD KEY `zone_id` (`zone_id`,`car_id`),
  ADD KEY `car_id` (`car_id`);

--
-- Indexes for table `parking_lots`
--
ALTER TABLE `parking_lots`
  ADD PRIMARY KEY (`lot_id`),
  ADD KEY `cc_id` (`cc_id`);

--
-- Indexes for table `parking_zones`
--
ALTER TABLE `parking_zones`
  ADD PRIMARY KEY (`zone_id`),
  ADD KEY `lot_id` (`lot_id`);

--
-- Indexes for table `salt`
--
ALTER TABLE `salt`
  ADD KEY `salt` (`salt`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `vips`
--
ALTER TABLE `vips`
  ADD KEY `zone_id` (`zone_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `clamping_companies`
--
ALTER TABLE `clamping_companies`
  MODIFY `cc_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `parking_lots`
--
ALTER TABLE `parking_lots`
  MODIFY `lot_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `parking_zones`
--
ALTER TABLE `parking_zones`
  MODIFY `zone_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `lot_managers`
--
ALTER TABLE `lot_managers`
  ADD CONSTRAINT `lot_managers_ibfk_1` FOREIGN KEY (`lot_id`) REFERENCES `parking_lots` (`lot_id`),
  ADD CONSTRAINT `lot_managers_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `parked_cars`
--
ALTER TABLE `parked_cars`
  ADD CONSTRAINT `parked_cars_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `parking_zones` (`zone_id`),
  ADD CONSTRAINT `parked_cars_ibfk_2` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`);

--
-- Constraints for table `parking_lots`
--
ALTER TABLE `parking_lots`
  ADD CONSTRAINT `parking_lots_ibfk_1` FOREIGN KEY (`cc_id`) REFERENCES `clamping_companies` (`cc_id`);

--
-- Constraints for table `parking_zones`
--
ALTER TABLE `parking_zones`
  ADD CONSTRAINT `parking_zones_ibfk_1` FOREIGN KEY (`lot_id`) REFERENCES `parking_lots` (`lot_id`);

--
-- Constraints for table `salt`
--
ALTER TABLE `salt`
  ADD CONSTRAINT `salt_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vips`
--
ALTER TABLE `vips`
  ADD CONSTRAINT `vips_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `parking_zones` (`zone_id`),
  ADD CONSTRAINT `vips_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
