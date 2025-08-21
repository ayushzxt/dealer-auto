# API Testing Guide

## Prerequisites
1. Start the application: `mvn spring-boot:run`
2. Application will be available at: `http://localhost:8080`

## Step 1: Get JWT Token
First, you need to get a JWT token to access the secured endpoints.

```bash
curl -X POST "http://localhost:8080/api/auth/token?username=demo" \
  -H "Content-Type: application/json"
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Save the token for subsequent requests:**
```bash
export TOKEN="eyJhbGciOiJIUzI1NiJ9..."
```

## Step 2: Dealer Management APIs

### Create a Dealer
```bash
curl -X POST "http://localhost:8080/api/dealers" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Premium Auto Dealers",
    "email": "premium@example.com",
    "subscriptionType": "PREMIUM"
  }'
```

### Get All Dealers
```bash
curl -X GET "http://localhost:8080/api/dealers" \
  -H "Authorization: Bearer $TOKEN"
```

### Get Dealer by ID
```bash
curl -X GET "http://localhost:8080/api/dealers/1" \
  -H "Authorization: Bearer $TOKEN"
```

### Update Dealer
```bash
curl -X PUT "http://localhost:8080/api/dealers/1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Updated Premium Auto Dealers",
    "email": "updated@example.com",
    "subscriptionType": "PREMIUM"
  }'
```

### Delete Dealer
```bash
curl -X DELETE "http://localhost:8080/api/dealers/1" \
  -H "Authorization: Bearer $TOKEN"
```

## Step 3: Vehicle Management APIs

### Create a Vehicle
```bash
curl -X POST "http://localhost:8080/api/vehicles" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "dealerId": 1,
    "model": "BMW X5",
    "price": 75000.00,
    "status": "AVAILABLE"
  }'
```

### Get All Vehicles
```bash
curl -X GET "http://localhost:8080/api/vehicles" \
  -H "Authorization: Bearer $TOKEN"
```

### Get Vehicle by ID
```bash
curl -X GET "http://localhost:8080/api/vehicles/1" \
  -H "Authorization: Bearer $TOKEN"
```

### Get Vehicles for Premium Dealers Only
```bash
curl -X GET "http://localhost:8080/api/vehicles/premium" \
  -H "Authorization: Bearer $TOKEN"
```

### Update Vehicle
```bash
curl -X PUT "http://localhost:8080/api/vehicles/1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "dealerId": 1,
    "model": "BMW X5 Updated",
    "price": 80000.00,
    "status": "SOLD"
  }'
```

### Delete Vehicle
```bash
curl -X DELETE "http://localhost:8080/api/vehicles/1" \
  -H "Authorization: Bearer $TOKEN"
```

## Step 4: Payment APIs

### Initiate Payment
```bash
curl -X POST "http://localhost:8080/api/payment/initiate" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "dealerId": 1,
    "amount": 1000.00,
    "method": "Card"
  }'
```

**Note:** The payment status will automatically change from "PENDING" to "SUCCESS" after 5 seconds.

## Step 5: Validation Examples

### Invalid Email (Validation Error)
```bash
curl -X POST "http://localhost:8080/api/dealers" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "name": "Test Dealer",
    "email": "invalid-email",
    "subscriptionType": "BASIC"
  }'
```

### Negative Price (Validation Error)
```bash
curl -X POST "http://localhost:8080/api/vehicles" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "dealerId": 1,
    "model": "Test Car",
    "price": -1000.00,
    "status": "AVAILABLE"
  }'
```

## Step 6: Error Handling Examples

### Access Without Token (401 Unauthorized)
```bash
curl -X GET "http://localhost:8080/api/dealers"
```

### Resource Not Found (404 Not Found)
```bash
curl -X GET "http://localhost:8080/api/dealers/999" \
  -H "Authorization: Bearer $TOKEN"
```

## Database Queries

### View All Dealers
```sql
SELECT * FROM dealers;
```

### View All Vehicles
```sql
SELECT v.*, d.name as dealer_name, d.subscription_type 
FROM vehicles v 
JOIN dealers d ON v.dealer_id = d.id;
```

### View Vehicles for Premium Dealers Only
```sql
SELECT v.*, d.name as dealer_name 
FROM vehicles v 
JOIN dealers d ON v.dealer_id = d.id 
WHERE d.subscription_type = 'PREMIUM';
```

### View All Payments
```sql
SELECT p.*, d.name as dealer_name 
FROM payments p 
JOIN dealers d ON p.dealer_id = d.id;
```

### View Payment Status Distribution
```sql
SELECT status, COUNT(*) as count 
FROM payments 
GROUP BY status;
```

### View Dealer Statistics
```sql
SELECT 
    d.name,
    d.subscription_type,
    COUNT(v.id) as total_vehicles,
    COUNT(CASE WHEN v.status = 'AVAILABLE' THEN 1 END) as available_vehicles,
    COUNT(CASE WHEN v.status = 'SOLD' THEN 1 END) as sold_vehicles,
    AVG(v.price) as avg_vehicle_price
FROM dealers d
LEFT JOIN vehicles v ON d.id = v.dealer_id
GROUP BY d.id, d.name, d.subscription_type;
```

## Swagger UI
Access the interactive API documentation at:
```
http://localhost:8080/swagger-ui/index.html
```

## Testing with Postman
1. Import the collection (if available)
2. Set the base URL to: `http://localhost:8080`
3. Get a token using the auth endpoint
4. Set the Authorization header to: `Bearer <your-token>`
5. Test all endpoints

## Monitoring and Logs
- Application logs will show AOP logging for all API calls
- Payment simulation logs will show status updates
- Check console output for detailed request/response logging
