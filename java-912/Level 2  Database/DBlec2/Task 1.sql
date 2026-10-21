-- 1. إنشاء جدول Doctor
CREATE TABLE Doctor (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10, 2),
    address VARCHAR(100)
);

-- 2. إدخال 10 سجلات
INSERT INTO Doctor (id, name, salary, address) VALUES 
(1, 'Dr. Ahmed Ali', 1500, 'Cairo'),
(2, 'Dr. Mona Hassan', 2000, 'Alexandria'),
(3, 'Dr. Khaled Omar', 5000, 'Giza'),
(4, 'Dr. Sarah Mahmoud', 3000, 'Mansoura'),
(5, 'Dr. Youssef Ibrahim', 1000, 'Tanta'),
(6, 'Dr. Mariam Adel', 4500, 'Aswan'),
(7, 'Dr. Amr Diab', 2500, 'Luxor'),
(8, 'Dr. Hoda Kamel', 3500, 'Suez'),
(9, 'Dr. Tarek Zaki', 1200, 'Port Said'),
(10, 'Dr. Nourhan Nabil', 4000, 'Ismailia');

-- 3. تحديث السجل رقم 3 ليصبح الراتب 20000
UPDATE Doctor 
SET salary = 20000 
WHERE id = 3;

-- 4. حذف السجل رقم 9
DELETE FROM Doctor 
WHERE id = 9;

-- 5. دمج الاسم مع الراتب (استخدام || أو CONCAT حسب نوع قاعدة البيانات لديك)
SELECT name || ' - Salary: ' AS doctor_info, salary 
FROM Doctor;

-- 6. عرض جميع السجلات مع ضرب الراتب في 2
SELECT id, name, salary AS original_salary, (salary * 2) AS double_salary, address 
FROM Doctor;

-- 7. اختيار البيانات التي يكون فيها الراتب 1000 أو 2000 أو 3000
SELECT * 
FROM Doctor 
WHERE salary IN (1000, 2000, 3000);

-- 8. إعادة تسمية الجدول من Doctor إلى PRD_DOCTOR
ALTER TABLE Doctor 
RENAME TO PRD_DOCTOR;