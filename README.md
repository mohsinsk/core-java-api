# Core Java API

A lightweight HTTP server application with MySQL database integration, built with Java 17.

## 🚀 Installation & Setup

### Step 1: Clone the Repository

```bash
git clone https://github.com/mohsinsk/core-java-api.git
cd core
```

### Step 2: Configure Environment Variables

Copy the example configuration:
```bash
cp .env.example .env
```

Edit the `.env` file with your credentials:
```bash
nano .env
```

Update with your values:
```env
MODE=local
DB_URL_DOCKER=jdbc:mysql://mysql:3306/core_java_api
DB_URL_LOCAL=jdbc:mysql://127.0.0.1:3306/core_java_api
DB_USER=root
DB_PASSWORD=your_secure_password
DB_NAME=core_java_api
AUTH_TOKEN=Bearer your_secure_token
```

### Step 3: Local Development

```bash
# 1. Initialize database
bash setup-db.sh

# 2. Build the project
mvn clean package -DskipTests

# 3. Start the server
bash run-server.sh
```

Server runs on: `http://localhost:8080`

### Step 4: Docker Deployment

```bash
# Set MODE to docker in .env
MODE=docker

# Run everything
bash docker-setup.sh
```

Server runs on: `http://localhost:8080`

---

## 📡 API Endpoints

All endpoints require authentication header:
```
Authorization: Bearer your_token_from_.env
```

### Get All Users
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer your_token"
```

### Get User by ID
```bash
curl -X GET http://localhost:8080/api/users/1 \
  -H "Authorization: Bearer your_token"
```

### Create User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Authorization: Bearer your_token" \
  -H "Content-Type: application/json" \
  -d '{"id": 1, "name": "John Doe", "role": "admin"}'
```

### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Authorization: Bearer your_token" \
  -H "Content-Type: application/json" \
  -d '{"name": "Jane Doe", "role": "user"}'
```

### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1 \
  -H "Authorization: Bearer your_token"
```

---

## 📝 License

MIT License

Copyright (c) 2026 Mohsin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

