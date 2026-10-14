<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Task</title>
</head>
<body>

<%-- 
  بنعرّف المتغيرات هنا جوه الـ Service method عاديك خالص، 
  عشان تتاخد وتتنفذ فريش مع كل ريفريش للصفحة (Request Scope).
--%>
<%
    int id = 101;
    String name = "Ahmed Mohamed";
%>

<%!
    /* 
      حتة فنية على السريع: حطينا الفانكشن دي جوه Declaration tag عشان 
      تتخلق كـ Member variable في الكلاس بتاعي مرة واحدة بس وقت الـ Compilation،
      مش كل ما ريكويست يضرب تقوم متكريتة من أول وجديد وتعمل الزحمة ملهاش لازمة في الميموري.
    */
    public String concatData(int id, String name) {
        return "ID: " + id + " - Name: " + name;
    }
%>

<h2>JSP Task Result</h2>

<%-- 
  بنستعمل الـ Expression tag (اللي من غير سيمي كولون في الآخر) 
  عشان نطبع ناتج الفانكشن دي دايركت على الـ Response stream من غير وجع دماغ.
--%>
<p>
    <%= concatData(id, name) %>
</p>

</body>
</html>