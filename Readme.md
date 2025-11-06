# 🏠 SPITians Bay — Innovothon Finale Project

<figure align="center">
  <img src="https://github.com/user-attachments/assets/16b2c2da-ee9f-463a-bbb8-afb3ca7ae169" alt="Project Banner" style="max-width:90%;height:auto;">
  <figcaption><em>Figure 1: SPITians Bay – Community Platform Prototype</em></figcaption>
</figure>

> **Platform for SPIT students to find nearby PGs without brokers — built using Core Java, JDBC, and MySQL.**  
> Designed and implemented following **Software Development Life Cycle (SDLC)** and **Hackathon DDLC (Design & Development Life Cycle)**.

---

## 📋 Table of Contents
1. [Project Overview](#-project-overview)  
2. [Hackathon Workflow](#-hackathon-workflow)  
3. [Tech Stack](#-tech-stack)  
4. [SDLC Phases](#-sdlc-phases)  
5. [Project Structure](#-project-structure)  
6. [Sample Test Cases](#-sample-test-cases)  
7. [Future Scope](#-future-scope)  
8. [Key Takeaways](#-key-takeaways)  
9. [Contact](#-contact)

---

## 🚀 Project Overview

**SPITians Bay** is a community platform that enables SPIT students to find **PG accommodations verified by seniors** — completely **broker-free**.

**🎯 Objective:**  
Help juniors find verified, affordable PGs through seniors already residing nearby — reducing dependency on brokers and saving brokerage costs.

---

## 🧱 Hackathon Workflow (DDLC)

> The Steps followed during the 48-hour Innovothon Hackathon — emphasizing speed, modularity, and collaboration.

<div align="center">

| Phase | Description |
|------:|-------------|
| **1. Ideation & Research** | Collected feedback from SPIT juniors about PG search challenges and broker issues. |
| **2. Planning & Design** | Outlined ER diagram, system flow, and assigned module responsibilities (User, PG, Blog). |
| **3. Development** | Built modules incrementally using Core Java, JDBC, and MySQL. Implemented MVC + DAO structure. |
| **4. Testing & Debugging** | Conducted unit and functional testing under hackathon deadlines. |
| **5. Presentation & Impact** | Presented results: estimated ₹53+ Lakh brokerage savings for 450+ students. |

</div>

---

## 🧑‍💻 Tech Stack
- **Language:** Java (Core + OOP)  
- **Database:** MySQL (via JDBC)  
- **Architecture:** MVC (Model–View–Controller)  
- **Type:** Console-based Java Prototype

---

## 🔄 SDLC Phases

### 1️⃣ Requirement Gathering
- Conducted quick surveys and interviews with juniors and seniors.  
- Problem identified: students lack local rent knowledge and pay high brokerages.

<figure align="center">
  <img src="[https://drive.google.com/file/d/1GBfYnVEs-lvRXVSSjiyBgIJmjhmIwV5E/view?usp=drive_link](https://drive.google.com/file/d/1GBfYnVEs-lvRXVSSjiyBgIJmjhmIwV5E/view?usp=drive_link)" alt="Requirement Gathering" style="max-width:85%;height:auto;">
  <figcaption><em>Figure 2: Requirement Gathering – Surveys & Interviews</em></figcaption>
</figure>

---

### 2️⃣ Defining / Analysis
- **Users:** SPIT students (verified by college email).  
- **Roles:**  
  - 🧑‍🎓 **Junior:** Search and filter PGs, read blogs.  
  - 🧑‍🏫 **Senior:** Post verified PG listings, write blogs.  
- **Features:** Registration, authentication, PG posting, custom search, hashtag-based blog system.  
- **Scalability:** Designed for 450–500 users.

---

### 3️⃣ Designing
- Applied **MVC + DAO** architecture.  
- Used **ERD** for database relationships (Users ↔ PGs ↔ Blogs).  
- **Java** chosen for modular OOP structure, easy testing, and quick updates.

<figure align="center">
  <img src="https://github.com/user-attachments/assets/79648441-401d-4249-a770-f34943d929df" alt="Architecture Diagram" style="max-width:80%;height:auto;">
  <figcaption><em>Figure 3: High-Level Architecture (MVC + DAO)</em></figcaption>
</figure>

<figure align="center">
  <img src="https://github.com/user-attachments/assets/69b6176d-80e9-4f78-b29e-88e9db74bbb7" alt="Use Case Diagram" style="max-width:70%;height:auto;">
  <figcaption><em>Figure 4: Use Case Diagram — Roles & Interactions</em></figcaption>
</figure>

<figure align="center">
  <img src="https://github.com/user-attachments/assets/6d8ea12d-1c49-4fda-8457-e18d3bce88c9" alt="Database Tables" style="max-width:65%;height:auto;">
  <figcaption><em>Figure 5: Database Schema & Table Relationships</em></figcaption>
</figure>

<figure align="center">
  <img src="https://github.com/user-attachments/assets/6d8ea12d-1c49-4fda-8457-e18d3bce88c9" alt="Database Tables" style="max-width:65%;height:auto;">
  <figcaption><em>Figure 5: Database Schema & Table Relationships</em></figcaption>
</figure>

---

## 4️⃣ Building / Implementation

<center>

<pre style="text-align:left; display:inline-block;">
src/main/java/com/spitbay/
├── Main.java                  # Application entry point
├── controller/                # Controllers for user interaction
│   ├── MainController.java    # Main application flow
│   └── InputHandler.java      # Input validation and routing
├── model/                     # Data models
│   ├── User.java              # Base user model
│   ├── Senior.java            # Senior user model
│   ├── PGListing.java         # PG listing model
│   ├── Blog.java              # Blog model
│   └── SearchPreferences.java # User search preferences
├── service/                   # Business logic layer
│   ├── UserService.java       # Handles registration & login
│   ├── PGService.java         # PG listing logic
│   ├── BlogService.java       # Blog operations
│   └── ScoringService.java    # Custom PG scoring algorithm
├── dao/                       # Data access layer
│   ├── SeniorDAO.java         # Senior data access
│   ├── PGListingDAO.java      # PG listing data access
│   └── BlogDAO.java           # Blog data access
├── view/                      # Console-based UI
│   ├── MenuView.java          # Navigation menu
│   ├── AuthView.java          # Registration/Login screens
│   ├── BlogView.java          # Blog display and filters
│   └── PGView.java            # PG listing results
├── database/                  # Database connection
│   └── DatabaseConnection.java
├── manager/                   # Manages services
│   └── ServiceManager.java
└── util/                      # Utilities
    └── SecurityUtil.java      # Security utilities (password hashing)
</pre>

</center>

**Core Functionalities**
- **Seniors:** Register/login, add/update PGs, write blogs.  
- **Juniors:** Search PGs using weighted preferences, read blogs.  
- **Algorithm:** Calculates *Compound Match Score* using rent, distance, and amenities; sorted via QuickSort.  
- **Blog Filtering:** Uses HashSet for duplicate-free hashtags.

---

## 🧪 5️⃣ Testing

<figure align="center">
  <img src="https://github.com/user-attachments/assets/4a1b848a-849a-4148-8956-ce7dfd1dd71d" alt="Testing Screenshot" style="max-width:75%;height:auto;">
  <figcaption><em>Figure 6: Test Execution – Unit & Functional Testing</em></figcaption>
</figure>

<div align="center">

### 🧩 Unit Test Cases

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 1 | Valid SPIT email check | `om@spit.ac.in` | true | ✅ |
| 2 | Invalid email check | `om@gmail.com` | false | ✅ |
| 3 | Password hashing | `12345` | Hashed output | ✅ |
| 4 | Database connection | JDBC URL | Connection success | ✅ |
| 5 | Scoring algorithm | Rent=10k, Dist=2km | Computed score | ✅ |

### 🧩 Functional Test Cases

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 6 | Registration | Valid SPIT mail | “Registration Successful” | ✅ |
| 7 | Duplicate Registration | Existing UID | “User already exists” | ✅ |
| 8 | PG Search Filter | Rent ≤15000, Wi-Fi=Yes | Filtered PG list | ✅ |
| 9 | Invalid Login | Wrong password | “Invalid credentials” | ✅ |
|10 | Search Ranking Order | Multiple PGs | Sorted list by score | ✅ |

</div>

---

## 🚀 6️⃣ Deployment
- Deployed as a **Console-based Java Application**.  
- Connected to **MySQL database via JDBC**.  
- Structured for easy transition to Spring Boot + React.

---

## 🔮 Future Scope
- Upgrade to **Spring Boot + React.js** full-stack app.  
- Add **Google Maps API** for distance-based search.  
- Implement **JWT authentication** and PG ratings module.  
- Add **payment tracking** for rent management.

---

## ✨ Key Takeaways
- Followed complete **SDLC + DDLC** from idea → deployment.  
- Applied **OOP principles** (Encapsulation, Polymorphism).  
- Built **custom PG scoring algorithm** for smart recommendations.  
- Solved a **real SPIT student problem** with measurable impact (~₹53+ Lakh saved).  

---

## 📧 Contact
**👤 Author:** Om Shinde  
**🏫 Institute:** Sardar Patel Institute of Technology, Mumbai  
**📧 Email:** [omshinde@spit.ac.in](mailto:omshinde@spit.ac.in)  
**💻 GitHub:** [SPITians Bay Repository](https://github.com/Om5101/SPITians_Bay)

---

<p align="center">⭐ <em>If this project helped you, please star the repository!</em> ⭐</p>
