-- 1. هاتلي الموظفين اللي الـ ID بتاعهم بين 100 و 105 (BETWEEN بتشمل الرقمين عادي)
SELECT * 
FROM employees 
WHERE employee_id BETWEEN 100 AND 105;

-- 2. الموظفين اللي الأيدي بتاعهم جوه الليست دي بالظبط (بدل ما نكتب OR كتير)
SELECT * 
FROM employees 
WHERE employee_id IN (151, 152, 153, 154, 155);

-- 3. الأسامي اللي بتتبدأ بحرف P كابيتال أو سمول (حلوة عشان الـ case sensitivity)
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE 'P%';

-- 4. الأسامي اللي بتخلص بحرف A أو a
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE '%A';

-- 5. الأسامي اللي فيها حرف A في أي حتة جوة الكلمة
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE '%A%';

-- 6. الحرف التالت في الاسم يكون e أو E (الـ underscore بيمثل حرف واحد)
SELECT * 
FROM employees 
WHERE UPPER(first_name) LIKE '__E%';

-- 7. الموظفين الكبار اللي ملهمش مدير (manager_id بيبقى فاضي NULL)
SELECT * 
FROM employees 
WHERE manager_id IS NULL;

-- 8. الناس اللي تحت مديرين وعندهه ID للـ manager
SELECT * 
FROM employees 
WHERE manager_id IS NOT NULL;

-- 9. هنضيف موظف جديد ونحط للـ manager بتاعه NULL
INSERT INTO employees (
    employee_id, first_name, last_name, email, 
    hire_date, job_id, salary, manager_id, department_id
) VALUES (
    999, 'John', 'Doe', 'JDOE', 
    SYSDATE, 'IT_PROG', 6000, NULL, 60
);

-- 10. الناس اللي شغالة في الشغلانتين دول بس (AD_VP أو IT_PROG)
SELECT * 
FROM employees 
WHERE job_id IN ('AD_VP', 'IT_PROG');

-- 11. رتبلي الموظفين حسب الـ last_name أبجدياً من الـ A للـ Z
SELECT * 
FROM employees 
ORDER BY last_name ASC;

-- 12. رتبلي التواريخ من الأحدث للأقدم (عشان نجيب الناس الجداد في الأول)
SELECT * 
FROM employees 
ORDER BY hire_date DESC;

-- 13. ترتيب بمستويين: الأول الأقسام تصاعدي، وجوه كل قسم رتب المرتبات تنازلي
SELECT * 
FROM employees 
ORDER BY department_id ASC, salary DESC;

-- 14. اعرض الـ last_name بحروف صغيرة سمول في النتيجة بس
SELECT employee_id, first_name, LOWER(last_name) AS last_name 
FROM employees;

-- 15. اعرض الـ first_name بحروف كبيرة كابيتال
SELECT employee_id, UPPER(first_name) AS first_name, last_name 
FROM employees;

-- 16. خلي أول حرف من الاسم كابيتال وباقي الحروف سمول (تنسيق شكل الاسم)
SELECT INITCAP(first_name) AS first_name, INITCAP(last_name) AS last_name 
FROM employees;

-- 17. دور على اللي اسمهم smith بغض النظر عن كابيتال أو سمول
SELECT * queries
FROM employees 
WHERE LOWER(last_name) = 'smith';