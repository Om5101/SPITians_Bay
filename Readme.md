# 🏠 SPITians Bay — Innovothon Finale Project

![Project Banner](https://github.com/user-attachments/assets/16b2c2da-ee9f-463a-bbb8-afb3ca7ae169)
> **Platform for SPIT students to find nearby PGs without brokers — built using Core Java, JDBC, and MySQL.**
>
> Designed and implemented following **Software Development Life Cycle (SDLC)** and **Hackathon DDLC (Design & Development Life Cycle)**.

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

**SPITians Bay** is a community platform that enables SPIT students to find **PG accommodations verified by seniors** — completely **broker-free**.

- Focused on solving a real student problem: *lack of local rate knowledge and excessive broker charges*.  
- Designed as an **Innovothon Finale Hackathon Prototype** under strict time constraints.

**🎯 Objective:**  
Help juniors find verified, affordable PGs through seniors already residing nearby — reducing dependency on brokers and saving brokerage costs.

---

## 🧑‍💻 Tech Stack
- **Language:** Java (Core + OOP)
- **Database:** MySQL (via JDBC)
- **Architecture:** MVC (Model–View–Controller)
- **Type:** Console-based Java Prototype

---

## 🔄 SDLC Phases

### **1️⃣ Requirement Gathering**
- Conducted informal research among SPIT juniors and peers.  
- Identified pain points:  
  - Students unaware of local PG rates.  
  - High brokerage on platforms like NoBroker.  

> **Problem Statement:**  
> “Create a community app where SPIT seniors can post PG vacancies and juniors can find verified, affordable accommodations without brokers.”

📸 **Survey & Problem Discovery**
<img width="800" alt="Requirement Gathering" src="https://github.com/user-attachments/assets/590a7d93-94ad-41c0-959c-09ac28fbceaa" />

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

**Core Design Choices:**
- Encapsulation in entity classes  
- Polymorphism for role-based behavior  
- Separation of concerns (DAO, Service, Controller layers)  

📸 **High-Level System Architecture**
![Architecture Diagram](https://github.com/user-attachments/assets/79648441-401d-4249-a770-f34943d929df)

📸 **Use Case Diagram**
<img width="800" alt="Use Case Diagram" src="https://github.com/user-attachments/assets/69b6176d-80e9-4f78-b29e-88e9db74bbb7" />

📸 **Database Schema**
<img width="600" alt="Database Tables" src="https://github.com/user-attachments/assets/6d8ea12d-1c49-4fda-8457-e18d3bce88c9" />

---

### **4️⃣ Building / Implementation Phase**

## 📂 Project Structure

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


---

### ⚙️ **Core Functionalities**
#### 👩‍🏫 For Seniors:
- Register/Login with SPIT email verification  
- Post PG Listings with amenities  
- Add Blog Posts with categories and hashtags  
- View or update their own listings  

#### 👨‍🎓 For Freshers:
- Search for PGs using weighted preferences  
- View all blogs  
- Filter blogs using hashtags  

#### 💡 **Algorithmic Highlights**
- **Custom Search Algorithm:**  
  Computes *Compound Match Score* using weighted parameters (Rent, Distance, Food, Wi-Fi, Sharing, Furnishing).  
  Implements **QuickSort** for ranking efficiency.  
- **Hashtag Blog Filtering:**  
  Uses **HashSet** for fast, duplicate-free filtering.

---

## 🧪 5️⃣ Testing Phase

📸 **Testing Screenshots**
![Testing Screenshot](https://github.com/user-attachments/assets/4a1b848a-849a-4148-8956-ce7dfd1dd71d)

Performed both **Unit** and **Functional** testing for validation.

### ✅ **Unit Test Cases**

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 1 | Valid SPIT email check | `om@spit.ac.in` | true | ✅ |
| 2 | Invalid email check | `om@gmail.com` | false | ✅ |
| 3 | Password hashing validation | `12345` | Returns hashed value | ✅ |
| 4 | Database connection test | JDBC URL | Connection successful | ✅ |
| 5 | Scoring algorithm accuracy | Rent=10k, Dist=2km | Returns computed score | ✅ |

### ✅ **Functional Test Cases**

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 6 | New registration | Valid SPIT mail | “Registration Successful” | ✅ |
| 7 | Duplicate registration prevention | Existing UID | “User already exists” | ✅ |
| 8 | PG search filtering | Rent ≤15000, Wi-Fi=Yes | Filtered PG list | ✅ |
| 9 | Invalid login handling | Wrong password | “Invalid credentials” | ✅ |
| 10 | Search result ranking | Multiple PGs | Sorted list by score | ✅ |

---

## 🚀 6️⃣ Deployment Phase
- Deployed as a **Console-based Java Application**.  
- Connected to a **local MySQL database** using JDBC.  
- Ready for migration to **Spring Boot + React** architecture.

---

## 🧱 DDLC (Hackathon Workflow)
> Simplified **Design & Development Life Cycle (DDLC)** followed during the 48-hour hackathon.

| Phase | Description |
|-------|--------------|
| **1. Ideation & Research** | Brainstormed real issues faced by SPIT students (PG search & brokerage problems). |
| **2. Planning & Design** | Sketched flowcharts, database ERD, and modular MVC structure. |
| **3. Development** | Implemented modules sequentially — User → PG → Search → Blog. |
| **4. Testing & Debugging** | Performed both unit and integration testing for key modules. |
| **5. Presentation & Demo** | Showcased impact — projected brokerage savings of ₹53+ Lakh for 450+ students. |

---

## 🔮 Future Scope
- Upgrade to **Spring Boot + React.js** full-stack web app.  
- Integrate **Google Maps API** for location-based filtering.  
- Add **JWT Authentication & Role-Based Access Control**.  
- Include **PG rating and payment tracking** module.

---

## ✨ Key Takeaways
- Followed complete **SDLC + DDLC** from requirements → deployment.  
- Applied **OOP principles** (Encapsulation, Polymorphism, Modularity).  
- Developed **custom PG scoring algorithm** for personalized recommendations.  
- Delivered a **real-world impactful solution** for SPIT students.  
- Estimated **₹53+ Lakh brokerage savings** across 450+ students.  

---

## 📧 Contact
**👤 Author:** Om Shinde  
**🏫 Institute:** Sardar Patel Institute of Technology, Mumbai  
**📧 Email:** [omshinde@spit.ac.in](mailto:omshinde@spit.ac.in)  
**🔗 LinkedIn:** [linkedin.com/in/omshinde](#)  
**💻 GitHub Repository:** [SPITians Bay](https://github.com/Om5101/SPITians_Bay)

---

⭐ *If you found this project insightful, consider giving it a star!*
