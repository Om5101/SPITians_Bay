# 🏠 SPITians Bay — Innovothon Finale Project

<figure align="center">
  <img src="https://github.com/user-attachments/assets/16b2c2da-ee9f-463a-bbb8-afb3ca7ae169" alt="Project Banner" style="max-width:90%;height:auto;">
  <figcaption><em>Figure 1: SPITians Bay – Community Platform Prototype</em></figcaption>
</figure>

> **Platform for SPIT students to find nearby PGs without brokers — built using Core Java, JDBC, and MySQL.**  
> Designed and implemented following **Software Development Life Cycle (SDLC)** and **Hackathon DDLC**.

---

## 📋 Table of Contents
1. [Project Overview](#-project-overview)  
2. [Tech Stack](#-tech-stack)  
3. [SDLC Phases](#-sdlc-phases)  
4. [DDLC (Hackathon Workflow)](#-ddlc-hackathon-workflow)  
5. [Project Structure](#-project-structure)  
6. [Sample Test Cases](#-sample-test-cases)  
7. [Future Scope](#-future-scope)  
8. [Key Takeaways](#-key-takeaways)  
9. [Contact](#-contact)

---

## 🚀 Project Overview

**SPITians Bay** is a community platform that enables SPIT students to find **PG accommodations verified by seniors** — completely **broker-free**.

**🎯 Objective:** Help juniors find verified, affordable PGs via seniors already living locally — reduce broker dependency.

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
- Problem: students lack local rent knowledge and pay high brokerages.

<figure align="center">
  <img src="https://github.com/user-attachments/assets/590a7d93-94ad-41c0-959c-09ac28fbceaa" alt="Requirement Gathering" style="max-width:85%;height:auto;">
  <figcaption><em>Figure 2: Requirement Gathering – Surveys & Interviews</em></figcaption>
</figure>

### 2️⃣ Defining / Analysis
- Users: SPIT students (verified by college email).  
- Roles: **Junior** (search) and **Senior** (post listings).  
- Features: Registration, PG posting, custom search ranking, blog/hashtags.

### 3️⃣ Designing
- MVC + DAO structure, ERD for MySQL (normalized).  
- Java for modularity and quick prototype iterations.

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

**Core features**
- Senior: register/login, post/update PG, add blogs (hashtags).  
- Junior: register/login, set search preferences, view ranked PGs, filter blogs.  
- Algorithm: compound match score (rent, distance, amenities) + QuickSort.  
- Blog filtering: HashSet for duplicate-free hashtag search.

---

## 5️⃣ Testing

<figure align="center">
  <img src="https://github.com/user-attachments/assets/4a1b848a-849a-4148-8956-ce7dfd1dd71d" alt="Testing Screenshot" style="max-width:75%;height:auto;">
  <figcaption><em>Figure 6: Test Execution – Unit & Functional Checks</em></figcaption>
</figure>

<div align="center">

**Unit Test Cases**

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 1 | Valid SPIT email check | `om@spit.ac.in` | true | ✅ |
| 2 | Invalid email check | `om@gmail.com` | false | ✅ |
| 3 | Password hashing | `12345` | hashed output | ✅ |
| 4 | DB connection test | JDBC URL | connection success | ✅ |
| 5 | Scoring algorithm result | rent=10k, dist=2km | computed score | ✅ |

</div>

<div align="center">

**Functional Test Cases**

| No. | Description | Input | Expected Output | Result |
|:--:|:--|:--|:--|:--:|
| 6 | New registration | Valid SPIT mail | “Registration Successful” | ✅ |
| 7 | Duplicate registration | Existing UID | “User already exists” | ✅ |
| 8 | PG search filter | Rent ≤15000, Wi-Fi=Yes | Filtered PG list | ✅ |
| 9 | Invalid login | Wrong password | “Invalid credentials” | ✅ |
|10 | Search ranking order | Multiple PGs | Sorted by score | ✅ |

</div>

---

## 6️⃣ Deployment
- Console-based Java app, connected to local MySQL via JDBC.  
- Modular design ready for migration to Spring Boot + web UI.

---

## 🧱 DDLC (Hackathon Workflow)

<div align="center">

| Phase | Description |
|------:|-------------|
| Ideation & Research | Identified PG problem via quick surveys |
| Planning & Design | Sketched ERD, flow and MVC modules |
| Development | Implemented User → PG → Search → Blog |
| Testing | Unit + Functional test cycles |
| Presentation | Demo with impact estimation (₹53+ Lakh saved) |

</div>

---

## 🔮 Future Scope
- Migrate to **Spring Boot + React**  
- Add **Google Maps** distance filtering  
- Add **JWT auth**, ratings & payments module

---

## ✨ Key Takeaways
- Full SDLC + hackathon DDLC executed.  
- OOP & modular design for rapid changes.  
- Custom scoring algorithm for PG ranking.  
- Real impact for SPIT students.

---

## 📧 Contact
**Om Shinde** — Sardar Patel Institute of Technology, Mumbai  
Email: [omshinde@spit.ac.in](mailto:omshinde@spit.ac.in)  
GitHub: https://github.com/Om5101/SPITians_Bay

---

<p align="center">⭐ <em>If this project helped, consider starring the repo!</em> ⭐</p>
