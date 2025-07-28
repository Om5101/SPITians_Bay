CREATE DATABASE IF NOT EXISTS pg_management;
USE pg_management;

CREATE TABLE senior_auth (
    uid VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE pg_listing (
    pg_id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(50),
    aadhar VARCHAR(20) UNIQUE NOT NULL,
    address VARCHAR(255) NOT NULL,
    rent INT NOT NULL,
    distance INT NOT NULL,
    area VARCHAR(50) NOT NULL,
    vacancies INT NOT NULL,
    beds INT NOT NULL,
    notes VARCHAR(255),
    FOREIGN KEY (uid) REFERENCES senior_auth(uid)
);

CREATE TABLE pg_amenities (
    pg_id INT,
    amenity_name VARCHAR(50),
    has_it ENUM('YES','NO') NOT NULL,
    PRIMARY KEY (pg_id, amenity_name),
    FOREIGN KEY (pg_id) REFERENCES pg_listing(pg_id)
);

CREATE TABLE blogs (
    blog_id INT AUTO_INCREMENT PRIMARY KEY,
    uid VARCHAR(50),
    content TEXT NOT NULL,
    FOREIGN KEY (uid) REFERENCES senior_auth(uid)
);

CREATE TABLE blog_categories (
    blog_id INT,
    category_name VARCHAR(50),
    PRIMARY KEY (blog_id, category_name),
    FOREIGN KEY (blog_id) REFERENCES blogs(blog_id)
);

CREATE TABLE blog_hashtags (
    blog_id INT,
    hashtag VARCHAR(50),
    PRIMARY KEY (blog_id, hashtag),
    FOREIGN KEY (blog_id) REFERENCES blogs(blog_id)
); 