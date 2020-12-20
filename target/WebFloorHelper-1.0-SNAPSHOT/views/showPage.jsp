<%@ page import="java.util.ArrayList" %>
<%@ page import="static com.Diachenko.WebFloorHelper.tools.DataFormat.getCoordinates" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous"/>
    <meta charset="utf-8">
    <style>
        <%@include file="/WEB-INF/css/stylesheet.css"%>
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Layout</title>
</head>

    <body>
        <header class="navbar navbar-expand bg-dark p-3">
            <nav class="nav-brand  text-light font-italic">
                <h3>Web floor application</h3>
            </nav>
        </header>
        <h2 class="text-center"> Layout:<%=request.getAttribute("n")%></h2>
        <div class="text-center">
            <canvas id="canvas"></canvas>
        </div>
        <div class="text-center">
            <button class="btn btn btn-primary" onclick="location.href='/'">Back to main</button>
            <button class="btn btn btn-primary" onclick="location.href='/coordinatesList'">To the list of layouts</button>
        </div>
        <script type="text/javascript">
            <%@include file="/WEB-INF/js/canvas.js"%>
        </script>
        <%
            String coordinates = request.getAttribute("c").toString();
            ArrayList<Integer> coordinatesList = getCoordinates(coordinates);
            for (int i = 0; i < coordinatesList.size(); i += 2) {
                out.println("<script>\ndraw(" + coordinatesList.get(i) + "," + coordinatesList.get(i + 1) + ")\n</script>");
            }
            out.println("<script>\ndrawElse(" + coordinatesList.get(0) + "," + coordinatesList.get(1) + ")\n</script>");
        %>
    </body>
</html>
