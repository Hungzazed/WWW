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