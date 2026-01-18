#!/bin/bash

# Retry logic for database connection
MAX_RETRIES=30
RETRY_INTERVAL=2
RETRY_COUNT=0

echo "🔄 Waiting for MySQL to be ready..."
echo "   Host: $DB_HOST_DOCKER"
echo "   Port: $DB_PORT"
echo "   Database: $DB_NAME"

until [ $RETRY_COUNT -ge $MAX_RETRIES ]; do
  if nc -z "$DB_HOST_DOCKER" "$DB_PORT" 2>/dev/null; then
    echo "✅ Port is open, checking MySQL service..."

    # Give MySQL a bit more time to fully initialize
    sleep 2

    echo "🚀 Starting Java application..."
    exec java -jar app.jar
  fi

  RETRY_COUNT=$((RETRY_COUNT + 1))
  echo "⏳ Attempt $RETRY_COUNT/$MAX_RETRIES: Database not ready. Waiting ${RETRY_INTERVAL}s..."
  sleep $RETRY_INTERVAL
done

echo "❌ Failed to connect to database after $MAX_RETRIES attempts"
exit 1

