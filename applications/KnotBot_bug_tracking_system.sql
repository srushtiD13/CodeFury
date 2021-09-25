CREATE DATABASE `bug_tracking_system` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(20) DEFAULT 'inProgress',
  `manager_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `manager_id_idx` (`manager_id`),
  CONSTRAINT `manager_id` FOREIGN KEY (`manager_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `tester_manager` (
  `tester_id` int(11) NOT NULL,
  `manager_id` int(11) DEFAULT '-1',
  `no_of_project` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tester_project` (
  `tester_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT '-1',
  KEY `tester_id_idx` (`tester_id`),
  KEY `project_id_idx` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` varchar(20) NOT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `last_logged_on` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=latin1;

CREATE TABLE `bug` (
  `unique_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `project_id` int(11) NOT NULL,
  `created_by` int(11) DEFAULT NULL,
  `open_date` date DEFAULT NULL,
  `assigned_to` int(11) DEFAULT '-1',
  `mark_for_closing` varchar(10) DEFAULT 'no',
  `closed_by` int(11) DEFAULT '-1',
  `closed_on` date DEFAULT NULL,
  `status` varchar(25) DEFAULT 'open',
  `severity` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`unique_id`),
  KEY `project_id_idx` (`project_id`),
  KEY `assigned_to_idx` (`assigned_to`),
  KEY `closed_by_idx` (`closed_by`),
  CONSTRAINT `assigned_to` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `closed_by` FOREIGN KEY (`closed_by`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `project_id` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `developer_project` (
  `developer_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

