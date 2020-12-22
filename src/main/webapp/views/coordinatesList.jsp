
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Diachenko.WebFloorHelper.database.model.Model" %>
<%@ page import="com.Diachenko.WebFloorHelper.database.entities.Layout" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous"/>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Coordinates list</title>
    </head>
    <body>
    //if you want use on the local server, you need to change (mysql:3306) to (localhost)
    // DON'T FORGET
        <%  Model model = new Model("jdbc:mysql://mysql:3306/floorlayoutdb?useUnicode=true", "root", "root");
            List<Layout> listOfLayouts = model.list();
            if (listOfLayouts != null && !listOfLayouts.isEmpty()) {
                for (int i = 0; i < listOfLayouts.size(); i++) {
                    Layout layout = listOfLayouts.get(i);
                    out.println("<div class=\"shadow p-2 mb-2 bg-white rounded\" >\n" +
                            "            <h4 class=\"alert-heading\">"+layout.getName()+"</h4>\n" +
                            "            <p>"+  layout.getCoordinates() + "</p>\n" +
                            "            <hr>\n" +
                            "            <a class=\"btn btn btn-primary\" href=\"/edit"+ layout.getId() + "\"role=\"button\">Edit</a>\n" +
                            "            <a class=\"btn btn btn-primary\" href=\"/delete"+layout.getId() + "\"role=\"button\">Delete</a>\n" +
                            "            <a class=\"btn btn btn-primary\" href=\"/showPage"+ layout.getId() + "\"role=\"button\">Show sketch</a>\n" +
                            "        </div>");

                }
            } else {
                out.println("<div class=\"shadow p-3 mb-2 bg-white rounded\">\n" +
                        "   <h3 class=\"text-center text-warning p-3\">There are no layouts yet!</h3>\n" +
                        "   <hr>\n"+
                        " <div class=\"text-center\">\n"+
                        "      <a href=\"/\" class=\"btn btn-primary ml-2\">Back to the main page</a>\n"+
                        "</div>\n"+
                        "</div>");
            }
        %>
    </body>

</html>


