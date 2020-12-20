<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 29.11.2020
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
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
        <div class="shadow p-3 mb-5 bg-white rounded">
            <label>Input your array of coordinates.For example (2,3),(2,8)...</label>
            <form class="mb-3">
                <div class="form-group">
                    <label for="coordinates">Coordinates</label>
                    <input type="text" class="form-control" id="coordinates"  placeholder="Enter coordinates">
                </div>
                <div class="form-group mb-1">
                    <label for="roomName">Room name</label>
                    <input type="text" class="form-control" id="roomName" placeholder="Room name">
                </div>
                <div class="my-2">
                    <button type="submit" class="btn btn btn-primary" style="width: 100px" > ok </button>
                    <button type="submit" class="btn btn-primary ml-2"> add a room to the database </button>
                    <button class="btn btn-primary ml-2" onclick="location.href='/coordinatesList'"> show my rooms </button>
                </div>
            </form>
        </div>


    </body>

</html>
