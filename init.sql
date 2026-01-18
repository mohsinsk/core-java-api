-- Initialize the database
USE core_java_api;

-- Create users table if not exists
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Optional: Insert sample data
INSERT INTO users (name, role) VALUES
('Admin User', 'admin'),
('Regular User', 'user');

-- Verify table creation
SELECT 'Database and table initialized!' as status;

