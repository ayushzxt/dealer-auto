# Dealer & Vehicle Management API with Payment Simulation

## Quick Start

### Prerequisites
- Java 17
- Maven
- PostgreSQL

### Setup
1. **Create Database:**
   ```sql
   CREATE DATABASE dealer_db;
   ```

2. **Start Application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Get JWT Token:**
   ```bash
   curl -X POST "http://localhost:8080/api/auth/token?username=demo"
   ```

4. **Test APIs:**
   - Use the provided cURL commands in `API-TESTING-GUIDE.md`
   - Or run the PowerShell script: `.\test-api.ps1`
   - Or import `Dealer-Vehicle-API.postman_collection.json` into Postman

### Quick Test Commands

```bash
# Get token
TOKEN=$(curl -s -X POST "http://localhost:8080/api/auth/token?username=demo" | jq -r '.token')

# Create dealer
curl -X POST "http://localhost:8080/api/dealers" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Test Dealer","email":"test@example.com","subscriptionType":"PREMIUM"}'

# Get all dealers
curl -X GET "http://localhost:8080/api/dealers" \
  -H "Authorization: Bearer $TOKEN"
```

## Documentation
- **API Testing Guide:** `API-TESTING-GUIDE.md`
- **Database Setup:** `database-setup.sql`
- **Postman Collection:** `Dealer-Vehicle-API.postman_collection.json`
- **PowerShell Script:** `test-api.ps1`

## Features
- ✅ Dealer & Vehicle CRUD operations
- ✅ Premium dealer vehicle filtering
- ✅ Payment simulation with async status updates
- ✅ JWT authentication
- ✅ Bean validation
- ✅ AOP logging
- ✅ Global error handling
- ✅ Swagger documentation
- ✅ Comprehensive test coverage

## API Endpoints
- **Dealer CRUD:** `/api/dealers`
- **Vehicle CRUD:** `/api/vehicles`
- **Premium Vehicles:** `GET /api/vehicles/premium`
- **Payment:** `POST /api/payment/initiate`
- **Auth:** `POST /api/auth/token`
- **Swagger UI:** `/swagger-ui/index.html`

## Database Schema
- **dealers:** id, name, email, subscription_type
- **vehicles:** id, dealer_id, model, price, status
- **payments:** id, dealer_id, amount, method, status, created_at, updated_at

## Testing
```bash
mvn test
```

