<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Task</title>
</head>
<body>

<%-- بنعرّف متغيرين: id للرقم التعريفي و name للاسم، وبندي كل واحد قيمة --%>
<%
    int id = 101;
    String name = "Ahmed Mohamed";
%>

<%!
    // بنعرّف دالة بتستقبل id و name كـ parameters، وبتدمجهم في String واحد.
    public String concatData(int id, String name) {
        return "ID: " + id + " - Name: " + name;
    }
%>

<h2>JSP Task Result</h2>

<%-- بنستدعي الدالة ونبعتلها قيم المتغيرين، وبنعرض النتيجة على الصفحة --%>
<p>
    <%= concatData(id, name) %>
</p>

</body>
</html>