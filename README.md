StoreMate

StoreMate is a retail store management system built with Java Spring Boot that helps store owners manage products, inventory, sales, and business performance from a single platform.

The application is designed to simplify everyday store operations while providing useful insights into revenue, profit, and top-selling products.

**🚀 Features**
👤 User Management
User registration and login
Password encryption using BCrypt
Session-based user authentication
User-specific store management
🏪 Store Management
Create and manage multiple stores
Each store maintains its own inventory and sales data
Store-specific reports and analytics
📦 Inventory Management
Add products to a store
Store product name, quantity, cost price, and selling price
Update product information
Delete products
Automatically update inventory after a sale
Track available stock
🛒 Sales Management
Create sales containing multiple products
Specify quantities for each product
Automatically calculate sale totals
Automatically deduct sold quantities from inventory
Store sales history
📊 Reports & Analytics
Total revenue across all stores
Total profit across all stores
Store-specific revenue
Store-specific profit
Most-selling product
Most-selling product for individual stores
Quantity of products sold

**🛠️ Tech Stack**
**Backend**
Java
Spring Boot
Spring MVC
Spring Data JPA
Hibernate
Spring Security
Database
MySQL

**Frontend**
HTML
CSS
JavaScript
Thymeleaf
Development Tools
Eclipse / Spring Tool Suite
MySQL
Git
GitHub
Maven

🏗️ Architecture

StoreMate follows a layered architecture:

Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Spring Data JPA
   ↓
Hibernate
   ↓
MySQL
Main Components

Controller Layer

Handles HTTP requests and communicates with the service layer.

Service Layer

Contains the application's business logic, such as inventory updates and sales processing.

Repository Layer

Uses Spring Data JPA to communicate with the database.

Entity Layer

Represents the application's database entities and their relationships.

Thymeleaf

Used to generate dynamic HTML pages using data provided by the Spring Boot backend.

🗄️ Main Entities

The application currently uses several core entities:

User

Stores user account information.

User
 ├── id
 ├── name
 ├── email
 └── password
Store

Represents a store belonging to a user.

Store
 ├── id
 ├── name
 └── user
Product

Represents products maintained within a store.

Product
 ├── id
 ├── name
 ├── quantity
 ├── costPrice
 ├── sellPrice
 └── store
Sale

Represents a completed sale.

Sale
 ├── id
 ├── saleDate
 ├── totalAmount
 └── store
SaleItem

Represents individual products included in a sale.

SaleItem
 ├── id
 ├── quantity
 ├── price
 ├── costPrice
 ├── subTotal
 ├── sale
 └── product
🔄 How Sales Work

A typical sale follows this process:

Select Store
     ↓
Select Products
     ↓
Enter Quantities
     ↓
Calculate Subtotal
     ↓
Complete Sale
     ↓
Create Sale & SaleItems
     ↓
Update Inventory
     ↓
Generate Sales Data
     ↓
Update Reports

For example, if a store has:

Product: Keyboard
Stock: 20
Selling Price: ₹800

and the customer purchases 3 keyboards:

Sale Total = ₹800 × 3
           = ₹2,400

The inventory is automatically updated:

Previous Stock = 20
Sold           = 3
Remaining      = 17
📈 Reporting

StoreMate provides both overall and store-specific reporting.

**Overall Reports**

The reports page provides:

Total Revenue
Total Profit
Most Selling Product
Quantity Sold
Store Reports

Each store can have its own:

Revenue
Profit
Most Selling Product
Quantity Sold

This allows users managing multiple stores to compare the performance of their individual stores.

🔐 Security

StoreMate uses BCrypt password hashing to avoid storing user passwords as plain text.

The application also uses session-based authentication to ensure that users can access their own stores and associated data.

Security configuration is currently being developed and will be further improved as the project evolves.

📂 Project Structure

A simplified structure of the application:

src
└── main
    ├── java
    │   └── ...
    │       ├── controller
    │       ├── service
    │       ├── repository
    │       ├── entity
    │       └── ...
    │
    └── resources
        ├── templates
        │   ├── login.html
        │   ├── register.html
        │   ├── dashboard.html
        │   ├── inventory.html
        │   ├── selling.html
        │   ├── reports.html
        │   └── about.html
        │
        └── application.properties
⚙️ Getting Started

**Prerequisites**

Make sure you have the following installed:

Java JDK
Maven
MySQL
Git
1. Clone the Repository
git clone https://github.com/YOUR-USERNAME/StoreMate.git

Navigate into the project:

cd StoreMate
2. Create the Database

Create a MySQL database:

CREATE DATABASE storemate;
3. Configure the Database

Update your application.properties with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/storemate
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run the Application

Using Maven:

mvn spring-boot:run

Or run the main Spring Boot application class from your IDE.

🖥️ Application Flow
Register
   ↓
Login
   ↓
Dashboard
   ↓
Create / Select Store
   ↓
Manage Inventory
   ↓
Start Selling
   ↓
Complete Sale
   ↓
Inventory Updated
   ↓
View Reports
🔮 Future Improvements

**StoreMate is an ongoing project. **

Planned improvements include:

Improved Spring Security authentication

Advanced sales history

Invoice generation

Discounts and tax calculation

Detailed revenue and profit charts

Date-based reports

Product search and filtering

Better mobile responsiveness

REST API improvements

Dashboard analytics

Improved validation and error handling

🎯 Project Goals

StoreMate was created to build a practical backend-oriented application using Spring Boot and Java while solving a real-world business problem.

The project focuses on understanding:

Layered architecture
Spring Boot
Spring Data JPA
Hibernate
Entity relationships
Database design
Transaction management
Business logic
Inventory management
Sales processing
Data aggregation and reporting

**👨‍💻 Author**

Sayan Roy

BCA Student
Interested in Java, Spring Boot, Backend Development and Software Engineering.

📄 License

This project is currently intended for educational and portfolio purposes.
