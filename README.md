# SPITian's Bay

Console-based platform for students to share PG listings and blogs.

## Features
- **Senior Registration & Login**: Secure authentication with UID validation
- **PG Listings**: Add and manage PG listings with amenities
- **Blog System**: Share experiences with categories and hashtags
- **Smart Search**: AI-powered PG matching based on preferences
- **Scoring System**: Weighted scoring for rent, distance, amenities, and area

## Technology Stack
- **Java 17** + **MySQL**
- **DAO-Service-Controller** architecture
- **BCrypt** password hashing
- **AES** encryption for sensitive data

## Setup
1. Create MySQL database using `src/main/resources/schema.sql`
2. Load sample data using `src/main/resources/sample_data.sql`
3. Set environment variables (optional):
   ```bash
   DB_URL=jdbc:mysql://localhost:3306/spit_bay
   DB_USER=spitbay
   DB_PASS=spitbay
   ```
4. Run: `mvn compile && mvn exec:java -Dexec.mainClass="com.spitbay.Main"`

## Architecture
- **Model**: Data entities (Blog, PGListing, Senior, etc.)
- **DAO**: Data access objects for database operations
- **Service**: Business logic layer
- **Controller**: Application flow and user interaction
- **View**: Display and input handling
- **Util**: Security and validation utilities
