# E-Commerce Backend System

This project is a fully functional e-commerce backend built using **Spring Boot**, **Spring Security**, **JWT**, **JPA**, and **PostgreSQL**. It manages users, products, shopping carts, orders, and profiles, and includes proper authentication and authorization mechanisms.

## Features

- ✅ User registration and login (with JWT authentication)
- ✅ Role-based access control (`ROLE_USER`, `ROLE_ADMIN`)
- ✅ Product catalog with category support
- ✅ User profile management
- ✅ Shopping cart with quantity control
- ✅ Order creation (checkout process)
- ✅ Comprehensive unit and repository testing
- ✅ CORS and CSRF handling for frontend integration

## Technologies Used

- Java 21
- Spring Boot 3.3.13
- Spring Security (JWT-based)
- Spring Data JPA
- PostgreSQL
- Maven
- JUnit 5
- Mockito
- Postman for API testing

## API Endpoints

### Authentication

| Method | Endpoint      | Description                 |
|--------|---------------|-----------------------------|
| POST   | /register     | Register a new user         |
| POST   | /login        | Login and receive JWT token |

### Products & Categories

| Method | Endpoint               | Description                     |
|--------|------------------------|---------------------------------|
| GET    | /products              | List all products               |
| GET    | /products/{id}         | Get product by ID               |
| GET    | /categories            | List all categories             |
| GET    | /categories/{id}      | Get category by ID              |
| GET    | /categories/{id}/products | List products in a category |

### User Profile

| Method | Endpoint   | Description             |
|--------|------------|-------------------------|
| GET    | /profile   | Get current user profile |
| PUT    | /profile   | Update user profile      |

### Shopping Cart

| Method | Endpoint                   | Description                     |
|--------|----------------------------|---------------------------------|
| GET    | /cart                      | Get current user's cart         |
| POST   | /cart/products/{productId} | Add product to cart             |
| PUT    | /cart/products/{productId} | Update product quantity in cart |
| DELETE | /cart                      | Clear the shopping cart         |

### Orders

| Method | Endpoint     | Description          |
|--------|--------------|----------------------|
| POST   | /orders      | Checkout and create order |

## Testing

- ✅ Repository tests for `User`, `Product`, `Profile`, `ShoppingCart`, and `Category` using `@DataJpaTest`
- ✅ Controller tests using `@WebMvcTest` with `MockMvc`
- ✅ JWT Security is disabled for testing to simplify integration

To run tests:

```bash
mvn test
