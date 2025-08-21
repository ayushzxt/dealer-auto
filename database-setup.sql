-- Create database (run this in PostgreSQL)
CREATE DATABASE dealer_db;

-- Connect to dealer_db and run these commands:

-- Sample data for dealers
INSERT INTO dealers (name, email, subscription_type) VALUES
('Premium Auto Dealers', 'premium@example.com', 'PREMIUM'),
('Basic Car Sales', 'basic@example.com', 'BASIC'),
('Luxury Motors', 'luxury@example.com', 'PREMIUM'),
('Budget Cars', 'budget@example.com', 'BASIC');

-- Sample data for vehicles
INSERT INTO vehicles (dealer_id, model, price, status) VALUES
(1, 'BMW X5', 75000.00, 'AVAILABLE'),
(1, 'Mercedes C-Class', 65000.00, 'AVAILABLE'),
(1, 'Audi A4', 55000.00, 'SOLD'),
(2, 'Honda Civic', 25000.00, 'AVAILABLE'),
(2, 'Toyota Corolla', 22000.00, 'AVAILABLE'),
(3, 'Porsche 911', 120000.00, 'AVAILABLE'),
(3, 'Ferrari F8', 250000.00, 'AVAILABLE'),
(4, 'Ford Focus', 18000.00, 'AVAILABLE');

-- Sample data for payments
INSERT INTO payments (dealer_id, amount, method, status, created_at, updated_at) VALUES
(1, 1000.00, 'Card', 'SUCCESS', NOW(), NOW()),
(2, 500.00, 'UPI', 'PENDING', NOW(), NOW()),
(3, 2000.00, 'NetBanking', 'SUCCESS', NOW(), NOW());
