<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous"/>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Title</title>
    </head>
    <body>
        <header class="navbar navbar-expand bg-dark p-3">
            <nav  class="nav-brand  text-light font-italic" >
                <h3>Web floor application</h3>
            </nav>
        </header>
        <div class="shadow p-3 mb-2 bg-white rounded">
            <label>Input your array of coordinates.For example (2,3),(2,8),(8,8),(8,3)...</label>
            <form class="mb-3" method="post" name="form">
                <div class="form-group">
                    <label for="coordinates">Coordinates</label>
                    <input name="values" type="text" class="form-control" id="coordinates" required placeholder="Enter coordinates">
                </div>
                <div class="form-group mb-1">
                    <label for="roomName">Room name</label>
                    <input name="name" type="text" class="form-control" id="roomName" required placeholder="Room name">
                </div>
                <div class="mt-3">
                    <button  class="btn btn btn-primary" style="width: 100px" type="submit"> ok </button>
                    <a role="button" class="btn btn-primary ml-2" onclick="location.href='/coordinatesList'"> Back to the list </a>
                </div>
            </form>
        </div>


    </body>
</html>
