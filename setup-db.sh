#!/bin/bash

# Create database and table for the application

# Load environment variables from .env if it exists
if [ -f .env ]; then
    export $(cat .env | grep -v '#' | xargs)
fi

# Use environment variables with defaults
DB_HOST="${DB_HOST:-127.0.0.1}"
DB_USER="${DB_USER:-root}"
DB_PASSWORD="${DB_PASSWORD:-}"
DB_NAME="${DB_NAME:-core_java_api}"

echo "Creating database and table..."
echo "Host: $DB_HOST, Database: $DB_NAME"

# Use password from environment or prompt if empty
if [ -z "$DB_PASSWORD" ]; then
    echo "❌ Error: DB_PASSWORD not set"
    echo "Please set DB_PASSWORD in .env file or as environment variable"
    exit 1
fi

mysql -h "$DB_HOST" -u "$DB_USER" -p"$DB_PASSWORD" -e "
CREATE DATABASE IF NOT EXISTS $DB_NAME;
USE $DB_NAME;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SHOW TABLES;
"

if [ $? -eq 0 ]; then
    echo "✅ Database setup complete!"
else
    echo "❌ Error setting up database"
    exit 1
fi

