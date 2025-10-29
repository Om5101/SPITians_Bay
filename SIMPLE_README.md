# SPIT Bay - Clean Project

## Overview
This is a clean, well-structured Java application for SPIT Bay - a PG search platform for engineering students. The project follows MVC architecture with proper separation of concerns.

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
