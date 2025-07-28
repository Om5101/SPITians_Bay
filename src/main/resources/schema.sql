-- Create database
CREATE DATABASE IF NOT EXISTS spit_bay;
USE spit_bay;

-- Create seniors table
CREATE TABLE IF NOT EXISTS seniors (
    uid VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

-- Create PG listings table
CREATE TABLE IF NOT EXISTS pg_listings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(50) NOT NULL,
    aadhar VARCHAR(12) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    rent INT NOT NULL,
    distance INT NOT NULL,
    area INT NOT NULL,
    vacancies INT NOT NULL,
    beds_per_room INT NOT NULL,
    gender CHAR(1) NOT NULL,
    notes TEXT,
    FOREIGN KEY (uid) REFERENCES seniors(uid),
    CONSTRAINT check_area CHECK (area BETWEEN 1 AND 8),
    CONSTRAINT check_gender CHECK (gender IN ('M', 'F'))
);

-- Create PG amenities table
CREATE TABLE IF NOT EXISTS pg_amenities (
    pg_id INT NOT NULL,
    amenity VARCHAR(50) NOT NULL,
    PRIMARY KEY (pg_id, amenity),
    FOREIGN KEY (pg_id) REFERENCES pg_listings(id) ON DELETE CASCADE
);

-- Create blogs table
CREATE TABLE IF NOT EXISTS blogs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (uid) REFERENCES seniors(uid)
);

-- Create blog categories table
CREATE TABLE IF NOT EXISTS blog_categories (
    blog_id INT NOT NULL,
    category VARCHAR(50) NOT NULL,
    PRIMARY KEY (blog_id, category),
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE,
    CONSTRAINT check_category CHECK (category IN (
        'Campus Life',
        'Academic Experience',
        'Housing Tips',
        'Local Guide',
        'Student Resources'
    ))
);

-- Create blog hashtags table
CREATE TABLE IF NOT EXISTS blog_hashtags (
    blog_id INT NOT NULL,
    hashtag VARCHAR(50) NOT NULL,
    PRIMARY KEY (blog_id, hashtag),
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE
); 