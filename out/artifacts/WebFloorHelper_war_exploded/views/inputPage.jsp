<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 12.12.2020
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit layout</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="w3-card-4">

            <div>
                <h2>Write new data:</h2>
            </div>

            <form method="post" name="form" action="/RoomServlet">
                <label>Write array with your coordinates [example: (x, y),... where x = 0...9, y = 0...9, min 4 corners must be presented ]:
                    <input type="text" name="coordinatesEdit" required maxlength="100"><br/>
                </label>
                <label>Write the name of your layout:
                    <input type="text" name="nameEdit"><br/>
                </label>
                <button>Edit</button>
                <a>Back to the list</a>
            </form>
        </div>
    </body>
</html>

