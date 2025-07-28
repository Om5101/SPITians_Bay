-- Insert sample seniors
INSERT INTO seniors (uid, password) VALUES
('senior1', 'password123'),
('senior2', 'password123'),
('senior3', 'password123'),
('senior4', 'password123'),
('senior5', 'password123');

-- Insert sample PG listings
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

-- Insert sample PG amenities
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
(10, 'WiFi'), (10, 'Laundry'), (10, 'Parking');

-- Insert sample blogs
INSERT INTO blogs (uid, content) VALUES
('senior1', 'My experience living in Andheri West has been amazing. The area is well-connected and has great food options.'),
('senior2', 'Tips for finding the perfect PG in Mumbai: Always check the water supply and electricity backup.'),
('senior3', 'The academic environment at SPIT is challenging but rewarding. Here are my study tips...'),
('senior4', 'Local guide to the best street food near SPIT campus.'),
('senior5', 'How to manage your expenses while living in a PG in Mumbai.'),
('senior1', 'The importance of choosing the right roommates in a PG.'),
('senior2', 'My journey from a small town to Mumbai for higher studies.'),
('senior3', 'Best places to study near SPIT campus.'),
('senior4', 'How to deal with homesickness while living in a PG.'),
('senior5', 'Tips for maintaining a healthy lifestyle in a PG.'),
('senior1', 'The role of technology in modern education at SPIT.'),
('senior2', 'How to make the most of your college life while living in a PG.'),
('senior3', 'The importance of networking during your college years.'),
('senior4', 'Balancing academics and social life in college.'),
('senior5', 'My experience with different PG accommodations in Mumbai.'),
('senior1', 'How to prepare for technical interviews while in college.'),
('senior2', 'The benefits of living in a PG vs. a rented apartment.'),
('senior3', 'Tips for maintaining good relations with PG owners.'),
('senior4', 'How to handle conflicts with roommates in a PG.'),
('senior5', 'The importance of location when choosing a PG in Mumbai.');

-- Insert sample blog categories
INSERT INTO blog_categories (blog_id, category) VALUES
(1, 'Campus Life'),
(2, 'Housing Tips'),
(3, 'Academic Experience'),
(4, 'Local Guide'),
(5, 'Student Resources'),
(6, 'Housing Tips'),
(7, 'Campus Life'),
(8, 'Academic Experience'),
(9, 'Student Resources'),
(10, 'Student Resources'),
(11, 'Academic Experience'),
(12, 'Campus Life'),
(13, 'Academic Experience'),
(14, 'Student Resources'),
(15, 'Housing Tips'),
(16, 'Academic Experience'),
(17, 'Housing Tips'),
(18, 'Housing Tips'),
(19, 'Student Resources'),
(20, 'Housing Tips');

-- Insert sample blog hashtags
INSERT INTO blog_hashtags (blog_id, hashtag) VALUES
(1, '#SPITLife'),
(2, '#PGTips'),
(3, '#StudyTips'),
(4, '#MumbaiFood'),
(5, '#BudgetLiving'),
(6, '#RoommateTips'),
(7, '#CollegeLife'),
(8, '#StudySpots'),
(9, '#Homesickness'),
(10, '#HealthyLiving'),
(11, '#TechEducation'),
(12, '#CollegeExperience'),
(13, '#Networking'),
(14, '#WorkLifeBalance'),
(15, '#PGExperience'),
(16, '#InterviewPrep'),
(17, '#Accommodation'),
(18, '#PGManagement'),
(19, '#ConflictResolution'),
(20, '#LocationMatters'); 