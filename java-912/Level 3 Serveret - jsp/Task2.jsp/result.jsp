<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <h2>Submitted User Information</h2>

    <%-- 
      أي حاجة بين العلامتين دول <% %> في الـ JSP
      معناها: "يا جافا اشتغلي هنا دي منطقة كود جافا (Scriptlet)"
    --%>
    <%
        // بنستخدم request.getParameter ونديلها نفس الـ name اللي سميناه في الفورم
        // عشان نشد القيمة اللي المستخدم كتبها ونخزنها في متغير جافا عادي
        
        String fullName = request.getParameter("fullName");       // بنجيب الاسم
        String password = request.getParameter("password");       // بنجيب الباسورد
        String age = request.getParameter("age");                 // بنجيب السن
        String addressRadio = request.getParameter("addressRadio"); // بنجيب المحافظة من أزرار الراديو
        String addressSelect = request.getParameter("addressSelect"); // بنجيب المحافظة من القائمة
    %>

    <%-- 
      علامة <%= %> دي طريقة سريعة في الـ JSP عشان نطبع حتة جافا جوا الـ HTML
      يعني بنقوله: اطبع قيمة المتغير ده هنا في الصفحة
    --%>
    <p><strong>Full Name:</strong> <%= fullName %></p>
    <p><strong>Password:</strong> <%= password %></p>
    <p><strong>Age:</strong> <%= age %></p>
    <p><strong>Address (Radio):</strong> <%= addressRadio %></p>
    <p><strong>Address (Select):</strong> <%= addressSelect %></p>

</body>
</html>