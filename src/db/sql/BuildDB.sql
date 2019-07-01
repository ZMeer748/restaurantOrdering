create database OrderSystemDB;

USE OrderSystemDB;

CREATE TABLE
IF NOT EXISTS user
(
    user_id INT AUTO_INCREMENT,
    user_password VARCHAR (40) NOT NULL,
    user_name VARCHAR (40) NOT NULL,
    user_isVIP ENUM('N','Y') DEFAULT 'N',
    CONSTRAINT pk_user_id PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
IF NOT EXISTS menu_item
(
    item_id INT NOT NULL,
    item_name VARCHAR (40) NOT NULL,
    item_price FLOAT NOT NULL,
    item_sort VARCHAR (10),
    item_image_URL VARCHAR(100) NULL,
    CONSTRAINT pk_item_id PRIMARY KEY (item_id),
    CONSTRAINT uc_item_name UNIQUE (item_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE
IF NOT EXISTS order_list
(
    order_id INT AUTO_INCREMENT,
    order_user_id INT,
    order_customer_num SMALLINT,
    order_content VARCHAR (200),
    order_remarks VARCHAR (200) NOT NULL,
    order_total FLOAT NOT NULL,
    order_datetime DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_item_id PRIMARY KEY (order_id),
    CONSTRAINT fk_user_id FOREIGN KEY(order_user_id) REFERENCES user(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

use OrderSystemDB
insert into user
values
(1, '123456', 'admin', 'Y'),
(2, '123456', 'nonVIP', 'N'),
(3, '123456', 'VIP', 'Y');

use OrderSystemDB
insert into menu_item values
(1, 'Curry beef with rice', 60, 'Food', 'resources/img/Curry beef with rice.png'),
(2, 'Sushi set meal', 65, 'Food', 'resources/img/Sushi set meal.png'),
(3, 'YangZhou fire rice', 45, 'Food', 'resources/img/YangZhou fire rice.png'),
(4, 'Sirloin Steak with Spaghetti', 72, 'Food', 'resources/img/Sirloin Steak with Spaghetti.png'),
(5, 'Chicken vegetable roll', 42, 'Food', 'resources/img/Chicken vegetable roll.png'),
(21, 'Soft drink', 10, 'Drink', 'resources/img/Soft drink.png'),
(22, 'Red wine', 15, 'Drink', 'resources/img/Red wine.png'),
(23, 'Beer', 15, 'Drink', 'resources/img/Beer.png');

use OrderSystemDB
insert into menu_item values
(6, '测试', 66, 'Drink', null);

use OrderSystemDB
update menu_item set item_image_URL = 'C:/Users/SISSON/Desktop/Chicken vegetable roll.png' where item_id = 6;

-- 查询订单记录
use OrderSystemDB
select order_id, order_user_id, order_customer_num, order_remarks, order_total, order_datetime from order_list;

--查询订单记录内容
use OrderSystemDB
select order_id, order_content from order_list;

