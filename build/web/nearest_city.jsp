<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nearest City</title>
        <link rel="stylesheet" href="css/myStyle.css" type="text/css" />
        <link rel="stylesheet" href="css/detail.css" type="text/css" />
    </head>
    <body>
    <center><h1>Nearest City</h1></center>
    <table>
        <thead>
            <tr>
                <th>City</th>
                <th>State</th>
                <th>Country</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>${cityModel.city}</th>
                <td>${cityModel.state}</td>
                <td>${cityModel.country}</td>
            </tr>
        </tbody>
    </table>
</body>
</html>
