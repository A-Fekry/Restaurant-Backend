Restaurant Management System
A full-featured restaurant REST API with authentication, menu management, and order processing built with Spring Boot and Oracle Database.
Features

User Authentication: JWT-based login/registration with role-based access (USER/ADMIN)
Menu Management: Create, update, delete products and categories
Order Processing: Place orders with automatic total calculation and unique order codes
Chef Profiles: Manage chef information with social media links
Contact System: Customer messaging with 1000-character limit
Search & Pagination: Filter products by category/keywords with paginated results
Bilingual Support: English and Arabic error messages
API Documentation: Swagger UI integration

Tech Stack

Spring Boot 3.4.2
Java 17
Oracle Database
Spring Security + JWT
MapStruct
Maven

Quick Start
Prerequisites

JDK 17+
Oracle Database
Maven

Configuration
Update application.yml:
yamlspring:
  datasource:
    url: jdbc:oracle:thin:@//localhost:1521/orclpdb
    username: hr
    password: hr

server:
  port: 8333

token:
  secret: xmnosonsiaoij23423#$@#$sdvsFDSWElfkgj
  expiration: 1h
Run Application
bash./mvnw spring-boot:run
App runs at: http://localhost:8333
API Endpoints
Authentication (Public)

POST /auth/login - Login
POST /auth/create-account - Register

Products (ADMIN: write, USER: read)

POST /product/save - Add product
POST /product/update - Update product
DELETE /product/remove - Delete product
GET /product/pageNo/{pageNo}/pageSize/{pageSize} - Get all products
GET /product/get-product-by-category-id/{categoryId}/pageNo/{pageNo}/pageSize/{pageSize} - Filter by category
GET /product/search-by-letters/{letters}/pageNo/{pageNo}/pageSize/{pageSize} - Search products

Categories (ADMIN only)

POST /category/save - Add category
POST /category/update - Update category
DELETE /category/remove - Delete category
GET /category/get-all-categories - Get all categories

Orders (USER)

POST /order/save - Place order
GET /order/get-by-id - Get user's orders (requires token)
GET /order/get-all - Get all orders (ADMIN only)

Contact (USER)

POST /contact/save - Send message (requires token)
GET /contact/get-all - View all messages (ADMIN only)

Chefs

POST /chef/save - Add chef (ADMIN)
GET /chef - Get all chefs

Usage Example
1. Register
jsonPOST /auth/create-account
{
  "userName": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "phoneNumber": "1234567890"
}
2. Login
jsonPOST /auth/login
{
  "userName": "john_doe",
  "password": "password123"
}
Response: { "token": "eyJhbGc...", "roles": ["ROLE_USER"] }
3. Add Product (ADMIN)
jsonPOST /product/save
Authorization: Bearer eyJhbGc...

{
  "productDto": {
    "name": "Margherita Pizza",
    "description": "Classic Italian pizza",
    "price": 12.99,
    "logoPath": "/images/pizza.jpg"
  },
  "catName": "Pizza"
}
4. Place Order (USER)
jsonPOST /order/save
Authorization: Bearer eyJhbGc...

{
  "productIds": [1, 2, 3],
  "productsQuantity": [2, 1, 3],
  "quantity": 6
}
Response: { "code": "a7f3b2c9-..." } (unique order code)
Security

JWT: 1-hour expiration
Password: BCrypt encryption
Roles: ROLE_USER (default), ROLE_ADMIN
CORS: Enabled for http://localhost:4200

Role-Based Access

ADMIN: Full CRUD on products, categories, chefs; view all orders/contacts
USER: View products/chefs, place orders, send contact messages

Swagger Documentation
Access API docs: http://localhost:8333/swagger-ui.html
License
Apache License 2.0