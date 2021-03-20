DROP TABLE IF EXISTS `exchange_rate_tbl`;

CREATE TABLE `exchange_rate_tbl` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `currency` varchar(45) NOT NULL,
  `value` decimal(16,4) NOT NULL DEFAULT '0.0000',
  `description` varchar(45) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `exchange_rate_tbl` VALUES (1,'THB',0.6345,'THB','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(2,'KRW',23.2665,'KRW','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(3,'ILS',0.0678,'ILS','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(4,'RON',0.0845,'RON','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(5,'EUR',0.0173,'EUR','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(6,'DKK',0.1287,'DKK','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(7,'HRK',0.1311,'HRK','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(8,'SGD',0.0276,'SGD','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(9,'HUF',6.3663,'HUF','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(10,'PHP',1.0000,'PHP','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(11,'SEK',0.1760,'SEK','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(12,'CAD',0.0257,'CAD','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(13,'BRL',0.1137,'BRL','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(14,'PLN',0.0799,'PLN','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(15,'AUD',0.0266,'AUD','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(16,'JPY',2.2414,'JPY','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(17,'GBP',0.0148,'GBP','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(18,'IDR',296.3749,'IDR','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(19,'CNY',0.1339,'CNY','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(20,'CHF',0.0191,'CHF','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(21,'MXN',0.4180,'MXN','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(22,'TRY',0.1494,'TRY','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(23,'BGN',0.0338,'BGN','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(24,'HKD',0.1598,'HKD','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(25,'USD',0.0206,'USD','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(26,'MYR',0.0845,'MYR','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(27,'RUB',1.5239,'RUB','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(28,'ZAR',0.3029,'ZAR','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(29,'NOK',0.1765,'NOK','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(30,'CZK',0.4521,'CZK','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(31,'INR',1.4917,'INR','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(32,'ISK',2.6196,'ISK','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE'),(33,'NZD',0.0287,'NZD','2021-03-21 00:00:02','2021-03-21 00:00:02','ACTIVE');

DROP TABLE IF EXISTS `source_account_tbl`;
CREATE TABLE `source_account_tbl` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `user_reference_no` varchar(45) NOT NULL,
  `account_number` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `balance` decimal(16,4) NOT NULL,
  `currency` varchar(45) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `transaction_tbl`;
CREATE TABLE `transaction_tbl` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `reference_no` varchar(45) NOT NULL,
  `account_number` varchar(45) NOT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `amount` decimal(16,4) DEFAULT '0.0000',
  `remaining_balance` decimal(16,4) NOT NULL,
  `transaction_type` varchar(45) NOT NULL,
  `transaction_date` datetime NOT NULL,
  `transaction_status` varchar(45) NOT NULL,
  `free_text` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user_tbl`;
CREATE TABLE `user_tbl` (
  `id` bigint(45) NOT NULL AUTO_INCREMENT,
  `reference_no` varchar(45) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);