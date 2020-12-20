<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 08.12.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <style>
            <%@include file="/WEB-INF/css/stylesheet.css"%>
        </style>
        <title>Title</title>
    </head>
    <body>
        <div id="container_div">
            <form method="post" name="form" action="/ValuesServlet">
                <input type="text" value="(2,3),(3,4),(5,1),(6,2)" name="values"/>
            </form>
            <canvas id="canvas" width=300 height=300 style="background-color:#ffff"></canvas>

            <script>
                var foo = <%=request.getAttribute("data").toString()%>
            </script>
        </div>
    </body>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/canvas.js"%>
    </script>
</html>
