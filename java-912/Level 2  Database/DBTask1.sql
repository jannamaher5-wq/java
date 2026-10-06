
-- بنعمل جدول Manger1 وبنحدد الـ ID كـ Primary Key وباقي الأعمدة
CREATE TABLE Manger1 (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    age NUMBER,
    birth_date DATE,
    address VARCHAR2(200)
);

-- بنتشك على الجدول عشان نتأكد إنه اتكريت صح
SELECT * FROM Manger1;


-- بنشيل عمود العنوان القديم عشان مش محتاجينه
ALTER TABLE Manger1 
DROP COLUMN address;

-- بنزود عمودين جديدين للعنوان (المدينة والشارع) عشان نحسن شكل البيانات
ALTER TABLE Manger1 
ADD (
    city_address VARCHAR2(100),
    street VARCHAR2(100)
);

-- بنغير اسم العمود من name لـ full_name عشان يبقى أوضح
ALTER TABLE Manger1 
RENAME COLUMN name TO full_name;


-- بنكريت جدول جديد اسمه Owner وبناخد نفس ديزاين الجدول الأولاني بس من غير ما ننقل داتا (عشان الشرط 0 = 1)
CREATE TABLE Owner AS 
SELECT id, full_name AS name, birth_date 
FROM Manger1 
WHERE 1 = 0;


-- بنغير اسم الجدول من Manger1 لـ Master1
RENAME Manger1 TO Master1;


-- بنخلي الجدول Read Only عشان محدش يلعب فيه أو يغير داتا باللغبطة
ALTER TABLE Master1 READ ONLY;


-- بنمسح الجداول خالص من الداتابيز عشان ننظف الشغل
DROP TABLE Owner;
DROP TABLE Master1;