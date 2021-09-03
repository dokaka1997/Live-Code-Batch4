<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div class="container">
            <center><h2>SHOWING NEAREST CITY</h2></center>
            <form action="ShowNearestCityServlet" method="GET">
                <div class="form-group">
                    <label for="text">Lat: </label>
                    <input type="text" class="form-control" placeholder="Enter Lat" name="lat" required="true">
                </div>
                <div class="form-group">
                    <label for="text">Lon: </label>
                    <input type="text" class="form-control" placeholder="Enter Lon" name="lon" required="true">
                </div>
                <center> <button type="submit" class="btn btn-default">Submit</button></center>
            </form>
        </div>

    </body>
</html>