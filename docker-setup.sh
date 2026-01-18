#!/bin/bash

# Complete setup script for Docker deployment

echo "🚀 Core Java API - Docker Setup Script"
echo "========================================"
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker first."
    exit 1
fi

# Check if Docker Compose is installed
if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose is not installed. Please install Docker Compose first."
    exit 1
fi

echo "✅ Docker and Docker Compose found"
echo ""

# Build Docker images (includes Maven build inside container)
echo "🐳 Building Docker images (this may take a few minutes)..."
docker-compose build

if [ $? -ne 0 ]; then
    echo "❌ Docker build failed!"
    exit 1
fi

echo "✅ Docker images built"
echo ""

# Start containers
echo "🚀 Starting containers..."
docker-compose up -d

if [ $? -ne 0 ]; then
    echo "❌ Failed to start containers!"
    exit 1
fi

echo "✅ Containers started"
echo ""

# Wait for services to be ready
echo "⏳ Waiting for services to be ready..."
sleep 15

# Check status
echo ""
echo "📊 Service Status:"
docker-compose ps

echo ""
echo "✅ Setup complete!"
echo ""
echo "🎯 Next steps:"
echo "  1. Test the API:"
echo "     curl -X GET http://localhost:8080/api/users \\"
echo "       -H \"Authorization: Bearer my-secret-token-123\""
echo ""
echo "  2. View logs:"
echo "     docker-compose logs -f app"
echo ""
echo "  3. Stop containers:"
echo "     docker-compose down"
echo ""
echo "📖 For more info, see DOCKER_GUIDE.md"

