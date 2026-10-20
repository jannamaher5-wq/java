<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h2>User Information Form</h2>
    
    <!-- 
      هنا بنعمل الفورم، وبنقوله:
      - action="result.jsp": لما أضغط submit ودّي البيانات دي لصفحة result.jsp
      - method="POST": ابعت البيانات مستخبية مش متشعلقة في رابط الصفحة فوق (علشان الأمان)
    -->
    <form action="result.jsp" method="POST">
        
        <!-- 
          مربع كتابة عادي للاسم
          name="fullName": ده الاسم الحركي للحقل ده، عشان الصفحة الثانية تعرف تطلب منه البيانات
        -->
        <label>Full Name:</label><br>
        <input type="text" name="fullName" required><br><br>

        <!-- 
          مربع باسورد
          type="password": بيخلي الكلام يظهر نجووم أو نقط عشان محدش يشوفه
        -->
        <label>Password:</label><br>
        <input type="password" name="password" required><br><br>

        <!-- 
          مربع للسن
          type="number": مش بيقبل غير أرقام بس
        -->
        <label>Age:</label><br>
        <input type="number" name="age" required><br><br>

        <!-- 
          أزرار الراديو (تختار منها حاجة واحدة بس)
          لاحظ إن كلهم واخدين نفس الـ name اللي هو "addressRadio" عشان لما يختار واحدة يلغي الباقي
          وكل واحدة ليها value بيتبعت لما تختارها
        -->
        <label>Address (Radio):</label><br>
        <input type="radio" name="addressRadio" value="cairo" required> Cairo
        <input type="radio" name="addressRadio" value="alex"> Alex
        <input type="radio" name="addressRadio" value="menofia"> Menofia<br><br>

        <!-- 
          القائمة المنسدلة (Dropdown List)
          اسمها "addressSelect" وكل خيار جواها (option) ليه value هو اللي بيتبعت
        -->
        <label>Address (Select):</label><br>
        <select name="addressSelect" required>
            <option value="">-- Select City --</option>
            <option value="cairo">Cairo</option>
            <option value="alex">Alex</option>
            <option value="menofia">Menofia</option>
        </select><br><br>

        <!-- الزرار اللي بيدوس عليه عشان يبعت كل العك ده للصفحة الثانية -->
        <input type="submit" value="Submit">
        
    </form>
</body>
</html>