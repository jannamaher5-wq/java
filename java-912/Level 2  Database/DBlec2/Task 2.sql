-- 1. إنشاء الجدول بالاسم الجديد
CREATE TABLE Staff (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Department VARCHAR(50),
    Salary DECIMAL(10, 2)
);

-- 2. إدخال البيانات
INSERT ALL
  INTO Staff (EmployeeID, FirstName, LastName, Department, Salary) VALUES (101, 'John1', 'Doe1', 'HR', 20000)
  INTO Staff (EmployeeID, FirstName, LastName, Department, Salary) VALUES (102, 'John2', 'Doe2', 'IT', 50000)
  INTO Staff (EmployeeID, FirstName, LastName, Department, Salary) VALUES (103, 'John3', 'Doe3', 'CS', 40000)
  INTO Staff (EmployeeID, FirstName, LastName, Department, Salary) VALUES (104, 'John4', 'Doe4', 'IT', 10000)
  INTO Staff (EmployeeID, FirstName, LastName, Department, Salary) VALUES (105, 'John5', 'Doe5', 'ZX', 30000)
SELECT * FROM dual;

-- 3. تحديث الراتب
UPDATE Staff
SET Salary = 600000
WHERE EmployeeID = 101;

-- 4. حذف السجل
DELETE FROM Staff
WHERE Department = '101';

-- 5. استرجاع موظفي IT
SELECT *
FROM Staff
WHERE Department = 'IT';

-- 6. عرض البيانات مع الدمج
SELECT 
    EmployeeID, 
    FirstName || ' ' || LastName AS Full_Name, 
    Department, 
    Salary
FROM Staff;