-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 26, 2017 at 01:12 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ec`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` text,
  `image` text,
  `account_type` varchar(50) NOT NULL,
  `account_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `email`, `password`, `image`, `account_type`, `account_status`) VALUES
(1, 'testcase@gmail', 'ab2d1', 'xml', 'org', 'active'),
(2, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(3, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(4, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(8, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(9, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(11, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(12, '@gmail', 'ab2d1', 'xml', 'org', 'active'),
(13, '', NULL, NULL, '', ''),
(14, 'yonathan@gmail.com', 'yonathan', NULL, 'Purchase products', 'Active'),
(15, 'yonathanhlmrm@gmail.com', 'yonathan', NULL, 'Purchase products', 'Active'),
(16, 'tst1@gmail.com', 'yonathan', NULL, 'Purchase products', 'Active'),
(17, 'tst2@gmail.com', 'yonathan', NULL, 'Purchase products', 'Active'),
(18, 'tst3@gmail.com', 'tester135', NULL, 'Purchase products', 'Active'),
(19, 'tst4@gmail.com', 'tester135', NULL, 'Purchase products', 'Active'),
(20, 'tst5@gmail.com', 'tester135', NULL, 'Purchase products', 'Active'),
(21, 'tst6@gmail.com', 'tester135', NULL, 'Purchase products', 'Active'),
(22, 'tst7@gmail.com', 'tester135', NULL, 'Purchase products', 'Active'),
(23, 'tst8@gmail.com', 'tester135', NULL, 'Purchase products', 'Active'),
(24, 'tst9@gmail.com', 'tester1357', 'IMG_0425.png', 'Purchase products', 'Active'),
(27, 'tst12@gmail.com', 'tester135', '426038641_148765_3288441415590532516.jpg', 'Sale Products', 'Active'),
(28, 'tst13@tst.com', 'tester135', NULL, 'Sale Products', 'Active'),
(29, 'tst21@gmail.com', 'tester135', NULL, 'Both', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `account_individual`
--

CREATE TABLE `account_individual` (
  `first_name` varchar(30) DEFAULT NULL,
  `middle_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_individual`
--

INSERT INTO `account_individual` (`first_name`, `middle_name`, `last_name`, `dob`, `gender`, `account_id`) VALUES
(NULL, NULL, NULL, NULL, NULL, 18),
(NULL, NULL, NULL, NULL, NULL, 19),
(NULL, NULL, NULL, NULL, NULL, 20),
(NULL, NULL, NULL, NULL, NULL, 21),
(NULL, NULL, NULL, NULL, NULL, 22),
('tester8', NULL, 'tester', NULL, NULL, 23),
('you', NULL, 'testeriooasdf', '0015-03-09', 'F', 24);

-- --------------------------------------------------------

--
-- Table structure for table `account_organization`
--

CREATE TABLE `account_organization` (
  `name` varchar(30) NOT NULL,
  `legal_status` varchar(30) DEFAULT NULL,
  `established_date` date DEFAULT NULL,
  `established_by` text,
  `description` text,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_organization`
--

INSERT INTO `account_organization` (`name`, `legal_status`, `established_date`, `established_by`, `description`, `account_id`) VALUES
('tester12', 'plc', '2007-01-16', 'asdf', 'asdf', 27),
('kebede balcha', NULL, NULL, NULL, NULL, 28),
('Tester', NULL, NULL, NULL, NULL, 29);

-- --------------------------------------------------------

--
-- Table structure for table `account_wish_list`
--

CREATE TABLE `account_wish_list` (
  `account_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_wish_list`
--

INSERT INTO `account_wish_list` (`account_id`, `product_id`) VALUES
(24, 1),
(24, 30),
(24, 31);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `postal_code` varchar(7) NOT NULL,
  `street` varchar(30) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state_province` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'Electornics', 'best trousers.'),
(2, 'Computers', 'best trousers.'),
(3, 'Clothing', 'best trousers.');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `tax` double NOT NULL,
  `shipping` varchar(5) NOT NULL,
  `total` double DEFAULT NULL,
  `status` varchar(20) NOT NULL COMMENT 'Canceled, Pending, Delivered',
  `remark` int(2) NOT NULL DEFAULT '0' COMMENT '0:not deleted, 1:customer only delete, 2:vendor only delete',
  `customer_id` int(11) NOT NULL,
  `order_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `tax`, `shipping`, `total`, `status`, `remark`, `customer_id`, `order_date`) VALUES
(26, 5, 'ab', 200, '', 0, 1, '2016-09-08'),
(27, 5, 'ab', 200, '', 0, 1, '2016-09-08'),
(28, 0, 'FREE', 304.786, 'Canceled', 1, 24, '0021-07-09'),
(29, 0, 'FREE', 192, 'Pending', 1, 24, '0021-07-09'),
(30, 0, 'FREE', 2030, 'Pending', 1, 24, '0021-07-09'),
(31, 0, 'FREE', 7000, 'Pending', 0, 29, '0024-07-09'),
(34, 0, 'FREE', 90, 'Canceled', 1, 24, '0025-07-09'),
(35, 0, 'FREE', 100, 'Pending', 1, 24, '0028-07-09'),
(36, 0, 'FREE', 10, 'Pending', 1, 24, '0028-07-09'),
(37, 0, 'FREE', 90, 'Canceled', 1, 24, '0028-07-09'),
(38, 0, 'FREE', 10, 'Pending', 1, 24, '0030-07-10'),
(39, 0, 'FREE', 1000, 'Pending', 1, 24, '0030-07-10'),
(40, 0, 'FREE', 10, 'Pending', 1, 24, '0030-07-10'),
(41, 0, 'FREE', 10, 'Pending', 0, 24, '0030-07-10'),
(42, 0, 'FREE', 304.786, 'Pending', 0, 24, '2017-01-26');

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `quantity` varchar(10) NOT NULL,
  `total` varchar(10) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`quantity`, `total`, `order_id`, `product_id`) VALUES
('1', '304.786', 28, 1),
('2', '180.0', 29, 29),
('1', '12.0', 29, 32),
('2', '2000.0', 30, 21),
('3', '30.0', 30, 30),
('7', '7000.0', 31, 21),
('1', '90.0', 34, 29),
('1', '90.0', 35, 29),
('1', '10.0', 35, 30),
('1', '10.0', 36, 30),
('1', '90.0', 37, 29),
('1', '10.0', 38, 30),
('1', '1000.0', 39, 21),
('1', '10.0', 40, 30),
('1', '10.0', 41, 30),
('1', '304.786', 42, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `images` varchar(200) DEFAULT '3.png',
  `unit_price` double NOT NULL,
  `atp` varchar(5) NOT NULL,
  `description` text NOT NULL,
  `category_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `images`, `unit_price`, `atp`, `description`, `category_id`, `account_id`) VALUES
(1, 'Testing', '1.png', 304.786, '10', 'best product.', 1, 27),
(21, 'Mac Computer', '1.png', 1000, '7', 'get it now', 1, 27),
(29, 'lo', '2.png', 90, '90', 'fg', 1, 27),
(30, 'Toshiba', '2.png', 10, '23', 'tester', 1, 27),
(31, 'Trouser', '3.png', 12, '12', 'asdf', 3, 27),
(32, 'Tosiba', '3.png', 12, '34', 'sdf', 2, 27),
(33, 'you', '3.png', 12, '12', 'thrs', 2, 27),
(35, 'sdafqwe', '426038641_148765_3288441415590532516.jpg', 123, '12', 'asdf', 1, 27);

-- --------------------------------------------------------

--
-- Table structure for table `product_cancelation`
--

CREATE TABLE `product_cancelation` (
  `id` int(11) NOT NULL,
  `charge` int(2) NOT NULL,
  `criteria` int(4) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_cancelation`
--

INSERT INTO `product_cancelation` (`id`, `charge`, `criteria`, `product_id`) VALUES
(1, 20, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `product_discount`
--

CREATE TABLE `product_discount` (
  `id` int(11) NOT NULL,
  `discount_percentage` int(2) NOT NULL,
  `frm` date NOT NULL,
  `too` date NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_review`
--

CREATE TABLE `product_review` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `comment` text NOT NULL,
  `product_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_review`
--

INSERT INTO `product_review` (`id`, `date`, `comment`, `product_id`, `account_id`) VALUES
(1, '0030-07-10', 'This is a testing comment', 30, 24),
(2, '0030-07-10', 'This iger', 33, 24),
(3, '0030-07-10', 'testing', 33, 24),
(4, '0030-08-10', 'asdf', 31, 24),
(5, '0030-08-10', 'dfgsdfgdsfg', 31, 24),
(6, '0030-08-10', 'asfdasdf', 29, 24),
(7, '0030-08-10', 'sadfasdf', 29, 24),
(8, '0030-08-10', 'hj', 29, 24),
(9, '0030-08-10', 'hjju', 29, 24),
(10, '2017-01-25', 'wer', 29, 24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `account_individual`
--
ALTER TABLE `account_individual`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `account_organization`
--
ALTER TABLE `account_organization`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `account_wish_list`
--
ALTER TABLE `account_wish_list`
  ADD PRIMARY KEY (`account_id`,`product_id`),
  ADD KEY `fk_wish_list_2` (`product_id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_address` (`account_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_account_idx` (`customer_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `FK_order_item_1` (`order_id`),
  ADD KEY `fk_product_idx` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category_idx` (`category_id`),
  ADD KEY `Fk_product_2` (`account_id`);

--
-- Indexes for table `product_cancelation`
--
ALTER TABLE `product_cancelation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_js9nfoymufr9gncpa6vbinxn5` (`product_id`);

--
-- Indexes for table `product_discount`
--
ALTER TABLE `product_discount`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_j3mi0112m5k44eektibqa4i5k` (`product_id`);

--
-- Indexes for table `product_review`
--
ALTER TABLE `product_review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_product_review_1` (`product_id`),
  ADD KEY `FK_product_review_account` (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `product_cancelation`
--
ALTER TABLE `product_cancelation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `product_discount`
--
ALTER TABLE `product_discount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_review`
--
ALTER TABLE `product_review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `account_individual`
--
ALTER TABLE `account_individual`
  ADD CONSTRAINT `FK_account_individual_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKbc8kxfbaleqf1uk3lwqwya2ea` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `account_organization`
--
ALTER TABLE `account_organization`
  ADD CONSTRAINT `FKspeij76slbdi2t1xpsbi3bos4` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `account_wish_list`
--
ALTER TABLE `account_wish_list`
  ADD CONSTRAINT `FKan4ey2jw5d4l3gmh3dkvo3u4o` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKd42bvd9q9och6di5oy0sunx98` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_wish_list_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_wish_list_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FKascogpq8x3gfx04oy2fr6l3i5` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_address` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_orders_1` FOREIGN KEY (`customer_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKll9qbvk2w486ag2h01rraqj7b` FOREIGN KEY (`customer_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `fk_order_item_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `fk_order_item_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_product_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKjchtiyhk49vgjb82rhjdvrxaj` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `Fk_product_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `product_cancelation`
--
ALTER TABLE `product_cancelation`
  ADD CONSTRAINT `FK2fk4nfn91gciuj80mfujlav81` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK_js9nfoymufr9gncpa6vbinxn5` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_discount`
--
ALTER TABLE `product_discount`
  ADD CONSTRAINT `FK_j3mi0112m5k44eektibqa4i5k` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKr5ttw8wovl5nkcc9ysfc16fkk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product_review`
--
ALTER TABLE `product_review`
  ADD CONSTRAINT `FKkaqmhakwt05p3n0px81b9pdya` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKkmsntrnepiw3ln45spvldpeu0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
