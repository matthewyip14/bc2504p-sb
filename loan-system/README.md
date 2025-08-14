# 🏦 Loan Management System

A comprehensive Spring Boot application for managing loans, users, and payments with a modern web interface.

## 🚀 Features

### Core Functionality
- **User Management**: Create, update, and manage user accounts
- **Loan Applications**: Apply for loans with automatic calculation
- **Loan Processing**: Approve, reject, and disburse loans
- **Payment Processing**: Track payments and calculate remaining balances
- **Loan Calculator**: Calculate monthly payments and total interest
- **Statistics Dashboard**: View system-wide statistics

### Technical Features
- **RESTful API**: Complete REST API with proper HTTP status codes
- **Database Integration**: PostgreSQL with JPA/Hibernate
- **Validation**: Input validation with Bean Validation
- **Error Handling**: Comprehensive error handling and logging
- **Modern UI**: Responsive web interface with JavaScript
- **Pagination**: Support for paginated results
- **CORS Support**: Cross-origin resource sharing enabled

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.5.3, Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Validation**: Bean Validation (Jakarta)
- **Logging**: SLF4J with Lombok

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 12+
- Git

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd loan-system
```

### 2. Database Setup
1. Create a PostgreSQL database named `bootcamp_2504p`
2. Update database credentials in `src/main/resources/application.yml` if needed:
```yaml
spring:
  datasource:
    url: "jdbc:postgresql://localhost:5432/bootcamp_2504p"
    username: "postgres"
    password: "admin1234"
```

### 3. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access the Web Interface
Open your browser and navigate to `http://localhost:8080` to access the loan management interface.

## 📊 Database Schema

### Users Table
- `id` (Primary Key)
- `name` (User's full name)
- `username` (Unique username)
- `password` (User password)
- `email` (Unique email address)
- `phone` (Phone number)

### Loans Table
- `id` (Primary Key)
- `user_id` (Foreign Key to Users)
- `loan_amount` (Principal amount)
- `interest_rate` (Annual interest rate)
- `loan_term_months` (Loan duration in months)
- `monthly_payment` (Calculated monthly payment)
- `total_amount` (Total amount to be paid)
- `status` (PENDING, APPROVED, ACTIVE, PAID_OFF, etc.)
- `application_date` (When loan was applied)
- `approval_date` (When loan was approved)
- `disbursement_date` (When loan was disbursed)
- `purpose` (Loan purpose description)
- `remaining_balance` (Current outstanding balance)
- `remaining_payments` (Number of payments left)

### Payments Table
- `id` (Primary Key)
- `loan_id` (Foreign Key to Loans)
- `amount` (Payment amount)
- `payment_date` (When payment was made)
- `status` (PENDING, COMPLETED, FAILED, etc.)
- `payment_number` (Sequential payment number)
- `principal_amount` (Principal portion of payment)
- `interest_amount` (Interest portion of payment)
- `remaining_balance` (Balance after payment)
- `notes` (Payment notes)

## 🔌 API Endpoints

### User Management
```
POST   /api/users                    # Create user
GET    /api/users                    # Get all users
GET    /api/users/{id}               # Get user by ID
GET    /api/users/username/{username} # Get user by username
GET    /api/users/email/{email}      # Get user by email
GET    /api/users/search?term={term} # Search users
GET    /api/users/with-loans         # Get users with loans
PUT    /api/users/{id}               # Update user
DELETE /api/users/{id}               # Delete user
GET    /api/users/check-username/{username} # Check username exists
GET    /api/users/check-email/{email}       # Check email exists
```

### Loan Management
```
POST   /api/loans/apply              # Apply for loan
GET    /api/loans                    # Get all loans (paginated)
GET    /api/loans/{id}               # Get loan by ID
GET    /api/loans/user/{userId}      # Get loans by user
GET    /api/loans/status/{status}    # Get loans by status
GET    /api/loans/user/{userId}/active # Get active loans by user
PUT    /api/loans/{id}/approve       # Approve loan
PUT    /api/loans/{id}/reject        # Reject loan
PUT    /api/loans/{id}/disburse      # Disburse loan
POST   /api/loans/calculate          # Calculate loan payment
GET    /api/loans/statistics         # Get loan statistics
```

### Payment Management
```
POST   /api/payments/process         # Process payment
GET    /api/payments                 # Get all payments (paginated)
GET    /api/payments/{id}            # Get payment by ID
GET    /api/payments/loan/{loanId}   # Get payments by loan
GET    /api/payments/status/{status} # Get payments by status
GET    /api/payments/user/{userId}   # Get payments by user
GET    /api/payments/schedule/{loanId} # Get payment schedule
GET    /api/payments/statistics      # Get payment statistics
GET    /api/payments/loan/{loanId}/statistics # Get loan payment stats
```

## 💡 Usage Examples

### 1. Create a User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "username": "johndoe",
    "email": "john@example.com",
    "phone": "123-456-7890"
  }'
```

### 2. Apply for a Loan
```bash
curl -X POST http://localhost:8080/api/loans/apply \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "loanAmount": 10000,
    "interestRate": 5.5,
    "loanTermMonths": 60,
    "purpose": "Home renovation"
  }'
```

### 3. Calculate Loan Payment
```bash
curl -X POST http://localhost:8080/api/loans/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "loanAmount": 10000,
    "interestRate": 5.5,
    "loanTermMonths": 60
  }'
```

### 4. Process a Payment
```bash
curl -X POST http://localhost:8080/api/payments/process \
  -H "Content-Type: application/json" \
  -d '{
    "loanId": 1,
    "amount": 200.00,
    "notes": "Monthly payment"
  }'
```

## 🔧 Configuration

### Application Properties
The main configuration is in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    driverClassName: "org.postgresql.Driver"
    url: "jdbc:postgresql://localhost:5432/bootcamp_2504p"
    username: "postgres"
    password: "admin1234"
  jpa:
    show-sql: true
    hibernate.ddl-auto: update
```

### Environment Variables
You can override database settings with environment variables:
- `DB_URL`: Database connection URL
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Manual Testing
1. Start the application
2. Open `http://localhost:8080` in your browser
3. Use the web interface to test all features
4. Monitor the console for application logs

## 📈 Business Logic

### Loan Calculation
The system uses the standard loan payment formula:
```
P = L[c(1 + c)^n]/[(1 + c)^n - 1]
```
Where:
- P = Monthly payment
- L = Loan amount
- c = Monthly interest rate (annual rate ÷ 12)
- n = Total number of payments

### Payment Processing
1. Calculate interest for the current period
2. Apply payment to interest first, then principal
3. Update remaining balance
4. Track payment history
5. Update loan status if fully paid

### Loan Status Flow
1. **PENDING**: Initial application status
2. **APPROVED**: Loan approved by admin
3. **ACTIVE**: Loan disbursed and active
4. **PAID_OFF**: Loan fully repaid
5. **REJECTED**: Loan application rejected
6. **DEFAULTED**: Loan in default
7. **CANCELLED**: Loan cancelled

## 🚨 Error Handling

The application includes comprehensive error handling:
- Input validation errors
- Database constraint violations
- Business logic validation
- Proper HTTP status codes
- Detailed error messages

## 🔒 Security Considerations

- Input validation on all endpoints
- SQL injection prevention through JPA
- CORS configuration for web interface
- Proper error handling without exposing sensitive information

## 📝 Future Enhancements

- Authentication and authorization
- Email notifications
- PDF generation for loan documents
- Advanced reporting and analytics
- Mobile application
- Integration with payment gateways
- Automated loan approval rules
- Interest rate management
- Late payment penalties
- Loan refinancing features

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the application logs for error details
- Review the API documentation above

---

**Note**: This is a demonstration system. For production use, additional security measures, comprehensive testing, and proper deployment procedures should be implemented. 