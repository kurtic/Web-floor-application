
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error!</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous"/>
    </head>
    <body>
        <header class="navbar-expand bg-dark py-3">
                <h3 class="text-light font-italic text-center m-0">Something wrong!</h3>
        </header>
        <div class="shadow p-3 mb-2 bg-white rounded">
            <div class="text-danger p-3 text-center">
                <h3><%=request.getAttribute("error")%></h3>
            </div>
            <hr>
            <div class="text-center">

                <button class=" btn btn-primary" onclick="location.href='/'">Enter data again</button>
            </div>
        </div>
    </body>
</html>
