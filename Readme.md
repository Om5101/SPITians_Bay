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
**SPITians Bay** is a community-driven prototype that enables SPIT students to find **PG accommodations** verified by seniors — completely **broker-free**.

- Developed as part of the **Innovothon Finale Hackathon**.  
- Focused on solving a real student problem: *lack of local rate knowledge and excessive broker charges*.  
- Implements a **custom PG search & filtering algorithm** that ranks listings based on user preferences.

**Objective:**  
Empower juniors to find verified, affordable PGs through seniors already residing nearby — reducing dependency on brokers and saving substantial costs.

---

## 🧑‍💻 Tech Stack
- **Language:** Java (Core + OOP)
- **Database:** MySQL (via JDBC)
- **Architecture:** MVC (Model–View–Controller)
- **Libraries/Utilities:** BCrypt for password hashing, HashSet for duplicate-free filtering
- **Type:** Console-based Java Prototype

---

## 🔄 SDLC Phases

### **1️⃣ Requirement Gathering**
- Conducted informal research among SPIT juniors and peers.  
- Identified pain points:  
  - Students unaware of local PG rates.  
  - High brokerage on platforms like NoBroker.  
- Problem statement:  
  > “Create a community app where SPIT seniors can post PG vacancies and juniors can find verified, affordable accommodations without brokers.”

---

### **2️⃣ Defining / Analysis Phase**
- **Target Users:** SPIT students (verified by college email).  
- **User Roles:**  
  - 🧑‍🎓 **Junior:** Search and filter PGs.  
  - 🧑‍🏫 **Senior:** Post verified PG listings.  
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

---

### **4️⃣ Building / Implementation Phase**

#### 📁 **Project Structure**

| Package | Files | Description |
|----------|-------|-------------|
| `com.spitbay.main` | `Main.java` | Entry point, initializes DB connection & menu navigation. |
| `com.spitbay.model` | `User.java`, `PGListing.java`, `Blog.java`, `SearchPreferences.java`, `Senior.java` | Entity classes with encapsulation and constructors. |
| `com.spitbay.dao` | `UserDAO.java`, `PGListingDAO.java`, `BlogDAO.java`, `SeniorDAO.java`, `UIDDAO.java` | Handles CRUD operations on MySQL. |
| `com.spitbay.service` | `UserService.java`, `PGService.java`, `BlogService.java`, `ScoringService.java` | Contains business logic for authentication, PG management, scoring, blogs. |
| `com.spitbay.controller` | `InputHandler.java`, `MainController.java` | Controls user actions, manages flow between views and services. |
| `com.spitbay.util` | `SecurityUtil.java` | Handles password encryption and validation utilities. |
| `com.spitbay.database` | `DatabaseConnection.java` | Establishes JDBC connection with MySQL. |
| `com.spitbay.view` | `AuthView.java`, `PGView.java`, `MenuView.java`, `BlogView.java` | Handles console interactions and user menus. |
| `com.spitbay.manager` | `ServiceManager.java` | Coordinates all services and dependencies. |

#### ⚙️ **Core Functionalities**
- **Registration & Login:**
  - Validates SPIT mail (`@spit.ac.in`)  
  - Hashes password using **BCrypt**  
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

---

## 🧱 DDLC (Hackathon Workflow)

> **Design & Development Life Cycle** — simplified version of SDLC used in hackathons (24–48 hrs).

| Phase | Description |
|-------|--------------|
| **1. Ideation & Research** | Brainstormed real issues faced by SPIT students (PG search problems). |
| **2. Planning & Design** | Sketched menu flow, ERD, and modular structure (MVC + DAO). |
| **3. Development** | Implemented modules incrementally: User → PG → Search → Blog. |
| **4. Testing & Debugging** | Unit-tested JDBC connections & search logic; resolved input errors. |
| **5. Presentation & Demo** | Demonstrated custom search algorithm and real impact (brokerage savings). |

---

## 🏗️ Future Scope
- Convert to **Spring Boot + React.js** web application.  
- Integrate **Google Maps API** for distance-based search.  
- Implement **JWT authentication**.  
- Add **payment tracking and PG review system**.

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
