create database storedb;
use storedb;

CREATE TABLE students (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         dob VARCHAR(10),
                         email VARCHAR(100) UNIQUE NOT NULL,
                         mobile VARCHAR(15) UNIQUE,
                         gender VARCHAR(10),
                         address VARCHAR(255),
                         city VARCHAR(50),
                         pin_code VARCHAR(10),
                         state VARCHAR(50),
                         country VARCHAR(50),
                         hobbies VARCHAR(255),
                         board_x VARCHAR(100),
                         percentage_x VARCHAR(10),
                         year_x VARCHAR(4),
                         board_xii VARCHAR(100),
                         percentage_xii VARCHAR(10),
                         year_xii VARCHAR(4),
                         course VARCHAR(100)
);

create database accountdb;
use accountdb;
CREATE TABLE accounts (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          date_of_birth DATE
);

create database shopdb;
use shopdb;
create table products (
                          id int primary key,
                          model varchar(100) not null,
                          description text,
                          quantity int not null,
                          price decimal(10,2) not null,
                          imgurl varchar(255)
);

-- Insert sample data into the products table
INSERT INTO products (id, model, description, quantity, price, imgUrl) VALUES
                                                                           (1, 'Laptop Dell XPS 13', '13-inch laptop with Intel i7, 16GB RAM, 512GB SSD', 10, 1299.99, 'dell_xps13.jpg'),
                                                                           (2, 'Smartphone iPhone 14', '6.1-inch OLED display, A15 Bionic chip', 25, 799.99, 'iphone14.jpg'),
                                                                           (3, 'Wireless Headphones Sony', 'Noise-canceling over-ear headphones', 50, 349.99, 'sony_headphones.jpg'),
                                                                           (4, 'Smartwatch Apple Watch Series 8', 'Fitness tracker with heart rate monitor', 30, 399.99, 'apple_watch8.jpg'),
                                                                           (5, 'Gaming Mouse Logitech G502', 'High-precision gaming mouse with RGB lighting', 100, 79.99, 'logitech_g502.jpg');