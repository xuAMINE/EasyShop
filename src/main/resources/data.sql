-- Insert a category
INSERT INTO categories (name, description)
VALUES  ('Electronics', 'Electronics.'),
        ('Fashion', 'Discover trendy clothing and accessories for men and women.'),
        ('Home & Kitchen', 'Find everything you need to decorate and equip your home.');

INSERT INTO users ( username, hashed_password, role)
VALUES  ('user','$2a$10$NkufUPF3V8dEPSZeo1fzHe9ScBu.LOay9S3N32M84yuUM2OJYEJ/.','ROLE_USER'),
        ('admin','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_ADMIN'),
        ('george','$2a$10$lfQi9jSfhZZhfS6/Kyzv3u3418IgnWXWDQDk7IbcwlCFPgxg9Iud2','ROLE_USER');

/* INSERT Profiles */
INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip)
VALUES  (1, 'Joe', 'Joesephus', '800-555-1234', 'joejoesephus@email.com', '789 Oak Avenue', 'Dallas', 'TX', '75051'),
        (2, 'Adam', 'Admamson', '800-555-1212', 'aaadamson@email.com', '456 Elm Street','Dallas','TX','75052'),
        (3, 'George', 'Jetson', '800-555-9876', 'george.jetson@email.com', '123 Birch Parkway','Dallas','TX','75051');

-- Insert products
INSERT INTO products (name, price, category_id, description, image_url, stock, featured, color)
VALUES
    ('Smartphone', 499.99, 1, 'A powerful and feature-rich smartphone for all your communication needs.', 'smartphone.jpg', 50, true, 'Black'),
    ('Headphones', 99.99, 1, 'Immerse yourself in music with these high-quality headphones.', 'headphones.jpg', 100, false, 'White'),
    ('Smart TV', 1499.99, 1, 'Experience stunning visuals and smart features with this advanced television.', 'smart-tv.jpg', 20, true, 'Black'),
    ('Digital Camera', 599.99, 1, 'Capture life''s precious moments with this professional-grade digital camera.', 'camera.jpg', 15, true, 'Silver'),
    ('Tablet', 349.99, 1, 'A portable and versatile tablet for productivity and entertainment.', 'tablet.jpg', 40, true, 'Black'),
    ('Gaming Console', 399.99, 1, 'Experience the latest gaming adventures with this powerful gaming console.', 'gaming-console.jpg', 10, true, 'Black'),
    ('Men''s Suit', 199.99, 2, 'Look sharp and elegant with this tailored suit.', 'mens-suit.jpg', 10, true, 'Dark Blue'),
    ('Men''s Shorts', 34.99, 2, 'Comfortable and versatile shorts for a relaxed summer look.', 'mens-shorts.jpg', 30, true, 'Tan'),
    ('Men''s Sweater', 59.99, 2, 'Stay cozy and fashionable with this stylish sweater.', 'mens-sweater.jpg', 20, true, 'Brown'),
    ('Men''s Polo Shirt', 39.99, 2, 'A classic and versatile polo shirt for a smart-casual style.', 'mens-polo-shirt.jpg', 50, true, 'Olive'),
    ('Men''s Jacket', 79.99, 2, 'A trendy and functional jacket for all seasons.', 'mens-jacket.jpg', 15, true, 'Charcoal'),
    ('Men''s Dress Shoes', 89.99, 2, 'Elevate your formal look with these stylish dress shoes.', 'mens-dress-shoes.jpg', 25, true, 'Brown'),
    ('Women''s Jumpsuit', 89.99, 2, 'A fashionable and effortless jumpsuit for a trendy look.', 'womens-jumpsuit.jpg', 20, true, 'Maroon'),
    ('Women''s T-Shirt', 29.99, 2, 'A comfortable and stylish t-shirt for everyday wear.', 'womens-tshirt.jpg', 50, true, 'Mint'),
    ('Women''s Blazer', 89.99, 2, 'A sophisticated and tailored blazer for a polished look.', 'womens-blazer.jpg', 15, true, 'Maroon'),
    ('Women''s Activewear Set', 79.99, 2, 'Stay active and stylish with this matching activewear set.', 'womens-activewear.jpg', 25, true, 'Lavender'),
    ('Women''s Swimwear', 59.99, 2, 'Stylish and comfortable swimwear for beach and pool days.', 'womens-swimwear.jpg', 50, true, 'Yellow'),
    ('Women''s Shoes', 79.99, 2, 'Step out in style with these fashionable and comfortable shoes.', 'womens-shoes.jpg', 35, true, 'Red'),
    ('Slow Cooker', 69.99, 3, 'Prepare flavorful and tender meals with this convenient slow cooker.', 'slow-cooker.jpg', 50, true, 'Blue'),
    ('Electric Kettle', 29.99, 3, 'Boil water quickly and efficiently with this sleek electric kettle.', 'electric-kettle.jpg', 15, true, 'Silver'),
    ('Microwave Oven', 99.99, 3, 'Heat and cook food with ease using this versatile microwave oven.', 'microwave-oven.jpg', 25, true, 'Silver'),
    ('Food Storage Containers', 24.99, 3, 'Keep your food fresh and organized with this set of reusable storage containers.', 'food-storage-containers.jpg', 50, true, 'White'),
    ('Utensil Set', 19.99, 3, 'A comprehensive set of cooking utensils for all your kitchen needs.', 'utensil-set.jpg', 35, true, 'Silver'),
    ('Dining Set', 149.99, 3, 'Elevate your dining experience with this elegant and durable dining set.', 'dining-set.jpg', 25, true, 'Red'),
    ('Casserole Dish', 34.99, 3, 'Bake and serve delicious casseroles with this versatile and stylish dish.', 'casserole-dish.jpg', 50, true, 'White'),
    ('Cutting Board Set', 29.99, 3, 'A set of durable and hygienic cutting boards for all your food preparation.', 'cutting-board-set.jpg', 10, true, 'Gray'),
    ('Laptop', 999.99, 1, 'A high-performance laptop for work and entertainment.', 'laptop.jpg', 30, true, 'Gray'),
    ('Laptop', 999.99, 1, 'A high-performance gaming laptop.', 'laptop.jpg', 30, true, 'Gray'),
    ('Tea Kettle', 29.99, 3, 'Brew a perfect cup of tea with this classic tea kettle.', 'tea-kettle.jpg', 50, true, 'White');


INSERT INTO shopping_cart (user_id, product_id, quantity)
VALUES  (3, 8, 1),
        (3, 10, 1);