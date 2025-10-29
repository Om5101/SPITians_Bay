# 🏠 SPITians Bay — Innovothon Finale Project

> **A community platform for SPIT students to find nearby PGs without brokers — built using Core Java, JDBC, and MySQL.**
>
> Designed and implemented following the **Software Development Life Cycle (SDLC)** and **Design & Development Life Cycle (DDLC)** for hackathon-based prototyping.

---

## 📋 Table of Contents
1. [Project Overview](#-project-overview)
2. [Tech Stack](#-tech-stack)
3. [SDLC Phases](#-sdlc-phases)
   - [Requirement Gathering](#1️⃣-requirement-gathering)
   - [Defining / Analysis](#2️⃣-defining--analysis-phase)
   - [Designing](#3️⃣-designing-phase)
   - [Building / Implementation](#4️⃣-building--implementation-phase)
   - [Testing](#5️⃣-testing-phase)
   - [Deployment](#6️⃣-deployment-phase)
4. [DDLC (Hackathon Workflow)](#-ddlc-hackathon-workflow)
5. [Project Structure](#-project-structure)
6. [Sample Test Cases](#-sample-test-cases)
7. [Future Scope](#-future-scope)
8. [Key Takeaways](#-key-takeaways)
9. [Contact](#-contact)

---

## 🚀 Project Overview

![image1](https://github.com/user-attachments/assets/16b2c2da-ee9f-463a-bbb8-afb3ca7ae169)

**SPITians Bay** is a community Platform that enables SPIT students to find **PG accommodations** verified by seniors — completely **broker-free**.

- Focused on solving a real student problem: *lack of local rate knowledge and excessive broker charges*.  

**Objective:**  
 Helps juniors to find verified, affordable PGs through seniors already residing nearby — reducing dependency on brokers and saving brokerages.
---

## 🧑‍💻 Tech Stack
- **Language:** Java (Core + OOP)
- **Database:** MySQL (via JDBC)
- **Architecture:** MVC (Model–View–Controller)
- **Type:** Console-based Java Prototype

---

### **1️⃣ Requirement Gathering**
- Conducted informal research among SPIT juniors and peers.  
- Identified pain points:  
  - Students unaware of local PG rates.  
  - High brokerage on platforms like NoBroker.  
- Problem statement:  
  > “Create a community app where SPIT seniors can post PG vacancies and juniors can find verified, affordable accommodations without brokers.”
  <img width="2400" height="1286" alt="477449300-a4c918d5-d4e3-4f33-b7d5-b410933a2ae1" src="https://github.com/user-attachments/assets/590a7d93-94ad-41c0-959c-09ac28fbceaa" />


---

### **2️⃣ Defining / Analysis Phase**
- **Target Users:** SPIT students (verified by college email).  
- **User Roles:**  
  - 🧑‍🎓 **Junior:** Search and filter PGs and blogs.  
  - 🧑‍🏫 **Senior:** Post verified PG listings and blogs.  
- **Functional Scope:**  
  - User registration & authentication.  
  - PG listing & search with custom ranking.  
  - Hashtag-based blog system.  
- **Scalability:** Designed for ~450–500 users.

---

### **3️⃣ Designing Phase**
- Designed **High-Level Architecture (MVC)** and **ER Diagram**.  
- **Java** chosen for modularity and OOP design — allows rapid prototype changes.  
- **Database:** MySQL with normalized schema and foreign key constraints.  
- **Core Design Decisions:**
  - Encapsulation in entity classes.
  - Polymorphism for role-based behavior.
  - Separation of concerns (DAO, Service, Controller layers).
  ![My First Board](https://github.com/user-attachments/assets/79648441-401d-4249-a770-f34943d929df)
  <img width="1050" height="756" alt="SPITians_Bay_Use_case" src="https://github.com/user-attachments/assets/69b6176d-80e9-4f78-b29e-88e9db74bbb7" />
  <img width="782" height="560" alt="SPITian Bay Tables" src="https://github.com/user-attachments/assets/6d8ea12d-1c49-4fda-8457-e18d3bce88c9" />

---

### **4️⃣ Building / Implementation Phase**

## Project Structure
```
src/main/java/com/spitbay/
├── Main.java                 # Application entry point
├── controller/               # Controllers for user interaction
│   ├── MainController.java   # Main application flow
│   └── InputHandler.java     # Input validation
├── model/                    # Data models
│   ├── User.java            # Base user model
│   ├── Senior.java          # Senior user model
│   ├── PGListing.java       # PG listing model
│   ├── Blog.java            # Blog model
│   └── SearchPreferences.java # Search preferences
├── service/                  # Business logic layer
│   ├── UserService.java     # User operations
│   ├── PGService.java       # PG listing operations
│   ├── BlogService.java     # Blog operations
│   └── ScoringService.java  # PG scoring algorithm
├── dao/                      # Data access layer
│   ├── SeniorDAO.java       # Senior data access
│   ├── PGListingDAO.java    # PG listing data access
│   └── BlogDAO.java         # Blog data access
├── view/                     # View layer for UI
│   ├── MenuView.java        # Menu displays
│   ├── AuthView.java        # Authentication views
│   ├── BlogView.java        # Blog displays
│   └── PGView.java          # PG listing displays
├── database/                 # Database connection
│   └── DatabaseConnection.java
├── manager/                  # Service management
│   └── ServiceManager.java
└── util/                     # Utilities
    └── SecurityUtil.java    # Security utilities
```

## Features

### For Seniors:
- Login/Register
- Add PG Listings with amenities
- Add Blog Posts with categories and hashtags
- View their own listings and blogs

### For Freshers:
- Search for PGs with weighted preferences
- View all blogs
- Filter blogs by categories or hashtags


#### ⚙️ **Core Functionalities**
- **Registration & Login:**
  - Validates SPIT mail (`@spit.ac.in`)  
- **PG Listing Module (for Seniors):**
  - Add / Update / Delete listings  
- **Custom Search Algorithm (for Juniors):**
  - Computes *Compound Match Score* using weighted parameters (Rent, Distance, Food, Wi-Fi, Sharing, Furnishing).  
  - Sorts results using **QuickSort** for ranking.  
- **Hashtag Blog Filtering:**
  - Uses **HashSet** for fast, duplicate-free hashtag search.  

---

### **5️⃣ Testing Phase**

Performed both **Unit** and **Functional** testing on modules.

#### ✅ **Unit Test Cases**

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 1 | Valid SPIT email check | `om@spit.ac.in` | true | ✅ |
| 2 | Invalid email check | `om@gmail.com` | false | ✅ |
| 3 | Password hashing check | `12345` | Returns hash | ✅ |
| 4 | DB connection | MySQL URL | Connection success | ✅ |
| 5 | Scoring algorithm | Rent=10k, Dist=2km | Returns weighted score | ✅ |

#### ✅ **Functional Test Cases**

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 6 | New registration | Valid SPIT mail | “Registration Successful” | ✅ |
| 7 | Duplicate registration | Existing UID | “User already exists” | ✅ |
| 8 | PG search filtering | Rent ≤15000, Wi-Fi=Yes | List of PGs | ✅ |
| 9 | Invalid login | Wrong password | “Invalid credentials” | ✅ |
| 10 | Search ranking order | Multiple PGs | Sorted results | ✅ |

---

### **6️⃣ Deployment Phase**
- Deployed as a **Console-based Java Application**.  
- Connected to local **MySQL database** using JDBC.  
- Can be easily migrated to **Spring Boot + Web Frontend** due to modular architecture.

---.

| Phase | Description |
|-------|--------------|
| **1. Ideation & Research** | Brainstormed real issues faced by SPIT students (PG search problems). |
| **2. Planning & Design** | Sketched menu flow, ERD, and modular structure (MVC + DAO). |
| **3. Development** | Implemented modules incrementally: User → PG → Search → Blog. |
| **4. Testing & Debugging** | Unit-tested JDBC connections & search logic; resolved input errors. |
| **5. Presentation & Demo** | Demonstrated custom search algorithm and real impact (brokerage savings). |

---

## ✨ Key Takeaways
- Followed **complete SDLC + DDLC** — from requirements to deployment.  
- Applied **OOP concepts** (Encapsulation, Polymorphism, Modularity).  
- Designed a **custom scoring algorithm** for PG recommendation.  
- Solved a **real-world community problem** with measurable impact (~₹53+ Lakh saved for 450+ students).  
- Prototype ready for web extension.

---

## 📧 Contact
**👤 Author:** Om Shinde  
**🏫 Institute:** Sardar Patel Institute of Technology, Mumbai  
**📧 Email:** [omshinde@spit.ac.in](mailto:omshinde@spit.ac.in)  
**🔗 LinkedIn:** [linkedin.com/in/omshinde](#)  
**💻 GitHub Repository:** [SPITians Bay](https://github.com/Om5101/SPITians_Bay)

---

⭐ *If you found this project insightful, consider giving it a star!*

## Project Structure
```
src/main/java/com/spitbay/
├── Main.java                 # Application entry point
├── controller/               # Controllers for user interaction
│   ├── MainController.java   # Main application flow
│   └── InputHandler.java     # Input validation
├── model/                    # Data models
│   ├── User.java            # Base user model
│   ├── Senior.java          # Senior user model
│   ├── PGListing.java       # PG listing model
│   ├── Blog.java            # Blog model
│   └── SearchPreferences.java # Search preferences
├── service/                  # Business logic layer
│   ├── UserService.java     # User operations
│   ├── PGService.java       # PG listing operations
│   ├── BlogService.java     # Blog operations
│   └── ScoringService.java  # PG scoring algorithm
├── dao/                      # Data access layer
│   ├── SeniorDAO.java       # Senior data access
│   ├── PGListingDAO.java    # PG listing data access
│   └── BlogDAO.java         # Blog data access
├── view/                     # View layer for UI
│   ├── MenuView.java        # Menu displays
│   ├── AuthView.java        # Authentication views
│   ├── BlogView.java        # Blog displays
│   └── PGView.java          # PG listing displays
├── database/                 # Database connection
│   └── DatabaseConnection.java
├── manager/                  # Service management
│   └── ServiceManager.java
└── util/                     # Utilities
    └── SecurityUtil.java    # Security utilities
```

## How to Run

1. **Setup Database:**
   - Create MySQL database named `spit_bay`
   - Run the schema.sql file to create tables
   - Run sample_data.sql to insert sample data

2. **Update Database Credentials:**
   - Edit `DatabaseConnection.java` if your MySQL credentials are different
   - Default: username=`spitbay`, password=`spitbay`

3. **Compile and Run:**
   ```bash
   # Using Maven
   mvn compile
   mvn exec:java -Dexec.mainClass="com.spitbay.Main"
   
   # Or manually
   javac -cp "mysql-connector-java-8.0.27.jar" src/main/java/com/spitbay/**/*.java
   java -cp ".:mysql-connector-java-8.0.27.jar" com.spitbay.Main
   ```

## Features

### For Seniors:
- Login/Register
- Add PG Listings with amenities
- Add Blog Posts with categories and hashtags
- View their own listings and blogs

### For Freshers:
- Search for PGs with weighted preferences
- View all blogs
- Filter blogs by categories or hashtags

## Architecture

### MVC Pattern
- **Model**: Data classes (User, Senior, PGListing, Blog)
- **View**: UI display classes (MenuView, AuthView, BlogView, PGView)
- **Controller**: Application flow (MainController, InputHandler)

### Design Patterns Used
- **DAO Pattern**: Data access objects for database operations
- **Service Layer**: Business logic separation
- **Singleton**: Database connection management
- **MVC**: Clean separation of concerns

### Security Features
- BCrypt password hashing
- Input validation and sanitization
- SQL injection prevention
- Secure authentication flow

### Database Optimizations
- **Singleton Pattern**: Single database connection manager instance
- **Driver Loading**: MySQL driver loaded once at startup
- **Reduced DB Calls**: Eliminated redundant database queries
- **Simple & Clean**: No complex pooling, easy to understand

## Key Features

### For Seniors:
- Secure login/registration
- Add PG listings with amenities
- Create blog posts with hashtags
- Manage their listings and blogs

### For Freshers:
- Search PGs with weighted preferences
- View and filter blogs by categories/hashtags
- Get personalized PG recommendations

## Benefits of Clean Architecture

1. **Maintainable**: Clear separation of concerns
2. **Scalable**: Easy to add new features
3. **Testable**: Each layer can be tested independently
4. **Professional**: Industry-standard patterns
5. **Secure**: Proper input validation and encryption

This clean, well-structured project demonstrates professional Java development practices!
