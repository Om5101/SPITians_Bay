-- ===========================================
-- SPIT BAY SAMPLE DATA - EXECUTION ORDER
-- ===========================================
-- This file is designed to be executed in MySQL Workbench
-- without foreign key constraint violations
-- 
-- SECURITY NOTE: All passwords are BCrypt hashed for security.
-- Original password for all test users is: password123
-- ===========================================

-- Clear existing data in reverse dependency order (IMPORTANT: Run this first if you have existing data)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE blog_hashtags;
TRUNCATE TABLE blog_categories;
TRUNCATE TABLE blogs;
TRUNCATE TABLE pg_amenities;
TRUNCATE TABLE pg_listings;
TRUNCATE TABLE seniors;
TRUNCATE TABLE valid_uids;
SET FOREIGN_KEY_CHECKS = 1;

-- Step 0: Insert valid UIDs for registration validation
INSERT INTO valid_uids (uid) VALUES
('ofss@spit.ac.in'),
('om@spit.ac.in'),
('omkar@spit.ac.in'),
('vedang@spit.ac.in'),
('aditya@spit.ac.in'),
('vishwa@spit.ac.in'),
('amit@spit.ac.in'),
('amey@spit.ac.in');

-- Step 1: Insert sample seniors (must be first as other tables reference this)
-- Note: Passwords are BCrypt hashed for security (original password was 'password123')
INSERT INTO seniors (uid, password) VALUES
('senior1', '$2a$10$N.zmdr9k7uOCQb376NoUnulTJ8iKyRY9X3A2Zk4p6z1QJQbKdq9bOu'),
('senior2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyRY9X3A2Zk4p6z1QJQbKdq9bOu'),
('senior3', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyRY9X3A2Zk4p6z1QJQbKdq9bOu'),
('senior4', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyRY9X3A2Zk4p6z1QJQbKdq9bOu'),
('senior5', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iKyRY9X3A2Zk4p6z1QJQbKdq9bOu');

-- Step 2: Insert sample PG listings (references seniors table)
INSERT INTO pg_listings (uid, aadhar, address, rent, distance, area, vacancies, beds_per_room, gender, notes) VALUES
('senior1', '123456789012', '123 Main Street, Andheri West', 12000, 500, 1, 2, 2, 'M', 'Near Metro Station'),
('senior1', '234567890123', '456 Park Road, Bandra East', 15000, 800, 2, 1, 3, 'F', 'Gated Society'),
('senior2', '345678901234', '789 Lake View, Powai', 18000, 1200, 3, 3, 2, 'M', 'Lake View'),
('senior2', '456789012345', '321 Garden Road, Vile Parle', 14000, 600, 1, 2, 2, 'F', 'Near Airport'),
('senior3', '567890123456', '654 Beach Road, Juhu', 20000, 1500, 4, 1, 3, 'M', 'Beach View'),
('senior3', '678901234567', '987 Hill Street, Malad West', 16000, 1000, 2, 2, 2, 'F', 'Peaceful Area'),
('senior4', '789012345678', '147 Market Road, Borivali', 13000, 700, 1, 3, 3, 'M', 'Near Market'),
('senior4', '890123456789', '258 School Lane, Kandivali', 17000, 900, 3, 1, 2, 'F', 'Near School'),
('senior5', '901234567890', '369 Temple Road, Goregaon', 19000, 1100, 4, 2, 2, 'M', 'Religious Area'),
('senior5', '012345678901', '741 College Street, Dadar', 22000, 1300, 2, 1, 3, 'F', 'Near College'),
('senior1', '123456789013', '852 Hospital Road, Sion', 14500, 750, 1, 2, 2, 'M', 'Near Hospital'),
('senior2', '234567890124', '963 Station Road, Kurla', 13500, 650, 2, 3, 3, 'F', 'Near Station'),
('senior3', '345678901235', '159 Mall Road, Andheri East', 21000, 1400, 3, 1, 2, 'M', 'Near Mall'),
('senior4', '456789012346', '357 Office Park, Powai', 17500, 950, 4, 2, 2, 'F', 'Near IT Park'),
('senior5', '567890123457', '486 Sports Complex, Bandra', 19500, 1150, 1, 1, 3, 'M', 'Near Sports Complex'),
('senior1', '678901234568', '753 Library Road, Vile Parle', 15500, 850, 2, 2, 2, 'F', 'Near Library'),
('senior2', '789012345679', '951 Cinema Street, Juhu', 22500, 1600, 3, 1, 3, 'M', 'Near Cinema'),
('senior3', '890123456780', '852 Gym Road, Malad', 16500, 900, 4, 2, 2, 'F', 'Near Gym'),
('senior4', '901234567891', '741 Park Avenue, Borivali', 18500, 1050, 1, 1, 3, 'M', 'Near Park'),
('senior5', '012345678902', '963 Lake View, Powai', 20500, 1250, 2, 2, 2, 'F', 'Lake View');

-- Step 3: Insert sample PG amenities (references pg_listings table)
-- Note: We have 20 PG listings, so amenities should reference IDs 1-20
INSERT INTO pg_amenities (pg_id, amenity) VALUES
(1, 'WiFi'), (1, 'AC'), (1, 'Food'),
(2, 'WiFi'), (2, 'Laundry'), (2, 'Food'),
(3, 'WiFi'), (3, 'AC'), (3, 'Gym'),
(4, 'WiFi'), (4, 'Laundry'), (4, 'Parking'),
(5, 'WiFi'), (5, 'AC'), (5, 'Food'),
(6, 'WiFi'), (6, 'Laundry'), (6, 'Gym'),
(7, 'WiFi'), (7, 'AC'), (7, 'Parking'),
(8, 'WiFi'), (8, 'Laundry'), (8, 'Food'),
(9, 'WiFi'), (9, 'AC'), (9, 'Gym'),
(10, 'WiFi'), (10, 'Laundry'), (10, 'Parking'),
(11, 'WiFi'), (11, 'Parking'),
(12, 'WiFi'), (12, 'Laundry'),
(13, 'WiFi'), (13, 'AC'),
(14, 'WiFi'), (14, 'Food'),
(15, 'WiFi'), (15, 'Gym'),
(16, 'WiFi'), (16, 'AC'),
(17, 'WiFi'), (17, 'Parking'),
(18, 'WiFi'), (18, 'Laundry'),
(19, 'WiFi'), (19, 'Food'),
(20, 'WiFi'), (20, 'AC');

-- Step 4: Insert blogs (references seniors table)
INSERT INTO blogs (uid, content) VALUES
('senior1', 'Explored Carter Road last weekend with my PG mates — perfect spot to unwind after project submissions! Highly recommend the sunset view. #WeekendVibes #AndheriSpots'),
('senior2', 'Before finalizing a PG, check distance from SPIT, water timings, and owner rules. Small details make big difference. #PGHunt #PGLife'),
('senior3', 'Our SPIT football team had a great match this weekend! Sports really help balance the academic grind. #StudentSports #CampusVibes'),
('senior4', 'Discovered a hidden gem — "Anand Vada Pav" near Andheri Station. Must-try for every SPITian! #MumbaiEats #BudgetMumbai'),
('senior5', 'Proud to be part of the Cultural Committee this year! Planning fests really teaches teamwork and leadership. #CollegeLife #CollegeEvents'),
('senior1', 'Weekends are for long walks, beach breezes, and reconnecting with friends. Juhu never disappoints! #WeekendPlans #LifeInMumbai'),
('senior2', 'Andheri has tons of PG options, but not all are genuine. I used SPITians Bay filters to find verified listings and saved both time and money! #PGRecommendations #PGLife'),
('senior3', 'Found a quiet café near SPIT for group studies. Great WiFi and affordable snacks. #StudySpots #SPITiansBay'),
('senior4', 'Homesickness hits hard sometimes, but joining hostel game nights and PG group dinners really helps. #RoommateStories #StudentWellbeing'),
('senior5', 'Balancing fitness and PG life? Join the local Andheri gym with student discounts — best stress relief! #StudentWellbeing #LifeInMumbai'),
('senior1', 'SPIT innovation labs are amazing — currently exploring IoT for college projects. #TechAtSPIT #SPITCommunity'),
('senior2', 'Networking starts in college! Join LinkedIn groups and alumni events early. You will thank yourself later. #Networking101 #CollegeLife'),
('senior3', 'Managing expenses in Mumbai? Try cooking together with PG mates — cheaper and fun. #BudgetMumbai #PGLife'),
('senior4', 'Annual SPIT Fest coming up! Excited to see juniors take charge this year. #CollegeEvents #SPITCommunity'),
('senior5', 'Tried multiple PGs before settling — cleanliness, distance, and food quality matter the most. #PGRecommendations #PGLife'),
('senior1', 'Preparing for interviews? Start with basics — SQL, Java, and mock interviews on weekends. #PlacementPrep #TechAtSPIT'),
('senior2', 'Living in Mumbai teaches independence and time management like no classroom can. #LifeInMumbai #SPITiansBay'),
('senior3', 'Roommate tip: Respect each other's space and share chores — makes PG life peaceful. #RoommateStories #StudentWellbeing'),
('senior4', 'Conflict with roommates? Talk openly instead of holding grudges — communication solves most issues. #StudentWellbeing #PGLife'),
('senior5', 'Choosing the right PG location saves hours of travel — Andheri West is ideal for SPIT students. #LocationMatters #PGHunt');

-- Step 5: Insert blog categories (references blogs table)
-- Note: Using valid categories from schema constraint and matching blog IDs
INSERT INTO blog_categories (blog_id, category) VALUES
(1, 'Campus Life'),
(2, 'Housing Tips'),
(3, 'Campus Life'),
(4, 'Local Guide'),
(5, 'Campus Life'),
(6, 'Campus Life'),
(7, 'Housing Tips'),
(8, 'Student Resources'),
(9, 'Campus Life'),
(10, 'Student Resources'),
(11, 'Academic Experience'),
(12, 'Academic Experience'),
(13, 'Housing Tips'),
(14, 'Campus Life'),
(15, 'Housing Tips'),
(16, 'Academic Experience'),
(17, 'Campus Life'),
(18, 'Housing Tips'),
(19, 'Campus Life'),
(20, 'Housing Tips');

-- Step 6: Insert blog hashtags (references blogs table)
INSERT INTO blog_hashtags (blog_id, hashtag) VALUES
(1, '#SPITiansBay'),
(1, '#WeekendVibes'),
(1, '#AndheriSpots'),
(2, '#PGHunt'),
(2, '#PGLife'),
(3, '#StudentSports'),
(3, '#CampusVibes'),
(4, '#MumbaiEats'),
(4, '#BudgetMumbai'),
(5, '#CollegeLife'),
(5, '#CollegeEvents'),
(6, '#WeekendPlans'),
(6, '#LifeInMumbai'),
(7, '#PGRecommendations'),
(7, '#PGLife'),
(8, '#StudySpots'),
(8, '#SPITiansBay'),
(9, '#RoommateStories'),
(9, '#StudentWellbeing'),
(10, '#StudentWellbeing'),
(10, '#LifeInMumbai'),
(11, '#TechAtSPIT'),
(11, '#SPITCommunity'),
(12, '#Networking101'),
(12, '#CollegeLife'),
(13, '#BudgetMumbai'),
(13, '#PGLife'),
(14, '#CollegeEvents'),
(14, '#SPITCommunity'),
(15, '#PGRecommendations'),
(15, '#PGLife'),
(16, '#PlacementPrep'),
(16, '#TechAtSPIT'),
(17, '#LifeInMumbai'),
(17, '#SPITiansBay'),
(18, '#RoommateStories'),
(18, '#StudentWellbeing'),
(19, '#StudentWellbeing'),
(19, '#PGLife'),
(20, '#LocationMatters'),
(20, '#PGHunt');
