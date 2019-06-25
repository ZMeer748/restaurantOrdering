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
    order_content JSON,
    order_remarks VARCHAR (200) NOT NULL,
    order_total FLOAT NOT NULL,
    order_datetime DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_item_id PRIMARY KEY (order_id),
    CONSTRAINT fk_user_id FOREIGN KEY(order_user_id) REFERENCES user(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;