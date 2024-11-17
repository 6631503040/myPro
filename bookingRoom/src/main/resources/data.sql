INSERT INTO division (divisionName) 
VALUES 
('kongphop'),
('Nina'),
('Win'),
('P'),
('Net');


INSERT INTO BUILD (buildName) 
VALUES ('S2'),('S3'),('S4');


INSERT INTO USERS (usersId, usersMail, usersName, usersPassword, usersPhone, usersType, divisionId) 
VALUES
(6631503041, 'student1@example.com', 'Student One', 'password1', '0800000001', 'STUDENT', 1),
(6631503042, 'student2@example.com', 'Student Two', 'password2', '0800000002', 'STUDENT', 1),
(6631503043, 'student3@example.com', 'Student Three', 'password3', '0800000003', 'STUDENT', 1),
(6631503044, 'staff1@example.com', 'Staff One', 'password4', '0800000004', 'STAFF', 2),
(6631503045, 'staff2@example.com', 'Staff Two', 'password5', '0800000005', 'STAFF', 2),
(6631503046, 'staff3@example.com', 'Staff Three', 'password6', '0800000006', 'STAFF', 2),
(6631503047, 'teacher1@example.com', 'Teacher One', 'password7', '0800000007', 'TEACHER', 3),
(6631503048, 'teacher2@example.com', 'Teacher Two', 'password8', '0800000008', 'TEACHER', 3),
(6631503049, 'teacher3@example.com', 'Teacher Three', 'password9', '0800000009', 'TEACHER', 3),
(6631503050, 'teacher4@example.com', 'Teacher Four', 'password10', '0800000010', 'TEACHER', 3),
(6631503053, 'director1@example.com', 'Director One', 'password13', '0800000013', 'DIRECTOR', 5),
(6631503054, 'student4@example.com', 'Student Four', 'password14', '0800000014', 'STUDENT', 1),
(6631503055, 'student5@example.com', 'Student Five', 'password15', '0800000015', 'STUDENT', 1),
(6631503056, 'staff4@example.com', 'Staff Four', 'password16', '0800000016', 'STAFF', 2),
(6631503057, 'staff5@example.com', 'Staff Five', 'password17', '0800000017', 'STAFF', 2),
(6631503058, 'teacher5@example.com', 'Teacher Five', 'password18', '0800000018', 'TEACHER', 3),
(6631503060, 'director2@example.com', 'Director Two', 'password20', '0800000020', 'DIRECTOR', 5),
(6631503051, 'head1@example.com', 'Head of Department One', 'password11', '0800000011', 'HEAD_DEPARTMENT', 4),
(6631503052, 'head2@example.com', 'Head of Department Two', 'password12', '0800000012', 'HEAD_DEPARTMENT', 4),
(6631503059, 'head3@example.com', 'Head of Department Three', 'password19', '0800000019', 'HEAD_DEPARTMENT', 4),
(6631503065, 'head7@example.com', 'Head of Department Seven', 'password17', '0800000017', 'HEAD_DEPARTMENT', 4),
(6631503066, 'head8@example.com', 'Head of Department Eight', 'password18', '0800000018', 'HEAD_DEPARTMENT', 4),
(6631503067, 'head9@example.com', 'Head of Department Nine', 'password19', '0800000019', 'HEAD_DEPARTMENT', 4),
(6631503068, 'head10@example.com', 'Head of Department Ten', 'password20', '0800000020', 'HEAD_DEPARTMENT', 4);


INSERT INTO Department (depName,headDepId) 
VALUES 
('ห้องปฏิบัติการฟิสิกส์(Physics Laboratory)',6631503051),
('ห้องปฏิบัติการชีววิทยาและเทคโนโลยีชีวภาพ(Biology and Biotechnology Laboratory)',6631503052),
('ห้องปฏิบัติการเคมี(Chemistry Laboratory)',6631503059),
('ห้องปฏิบัติการวิทยาศาสตร์เครื่องสำอาง(Cosmetic Science Laboratory)',6631503065),
('ห้องปฏิบัติการเทคโนโลยีหลังการเก็บเกี่ยวและบรรจุภัณฑ์(Postharvest Technology and Packaging Laboratory)',6631503066),
('ห้องปฏิบัติการวัสดุศาสตร์(Material science laboratory)',6631503067),
('ห้องปฏิบัติการเทคโนโลยีอาหาร(Food Technology laboratory)',6631503068);


INSERT INTO ROOM (roomNum,capacity,buildId,roomStatus,depId) 
VALUES 
('213','43',2,'available',1),
('215','43',2,'available',1),
('217','43',2,'available',1),
('220','43',2,'available',1),
('301','36',2,'available',2),
('302/1','5',2,'available',2),
('302/2','5',2,'available',2),
('303','43',2,'available',2),
('305','43',2,'available',2),
('310','43',2,'available',2),
('312','43',2,'available',2),
('314','43',2,'available',2),
('301','10',1,'available',2),
('303','20',1,'available',2),
('304','15',1,'available',2),
('305','10',1,'available',2),
('401','43',2,'available',2),
('415','43',2,'available',2),
('417','43',2,'available',2),
('419','43',2,'available',2),
('422','43',2,'available',2),
('506','5',2,'available',3),
('507','43',2,'available',3),
('509','6',2,'available',3),
('510','43',2,'available',3),
('105','43',2,'available',3),
('106','5',2,'available',3),
('201','18',2,'available',3),
('201/1','5',2,'available',3),
('203','36',2,'available',3),
('205','0',2,'available',3),
('206','3',2,'available',3),
('208','12',2,'available',3),
('107','12',3,'available',3),
('107/1','12',3,'available',3),
('107/2','12',3,'available',3),
('103','12',3,'available',3),
('103/1','30',3,'available',3),
('101','30',3,'available',3),
('102','20',3,'available',3),
('114','30',3,'available',3),
('118','3',3,'available',3),
('119','50',3,'available',3),
('120','50',3,'available',3),
('120/1','30',3,'available',3),
('120/6','3',3,'available',3),
('123','3',3,'available',3);
