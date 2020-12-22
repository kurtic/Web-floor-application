
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous"/>
    </head>
    <body>
        <div class="shadow p-2 mb-2 bg-white rounded">
            <h2 class="p-3 text-black-50">Are you sure ?</h2>
            <form method="post" class="">
                <hr>
                <button type="submit" class="ml-3 btn btn btn-danger">Yes, delete</button>
                <a class="btn btn btn-success ml-3" href="/coordinatesList" role="button">Back to the list</a>
            </form>
        </div>
    </body>
</html>
