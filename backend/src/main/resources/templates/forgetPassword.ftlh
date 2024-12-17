<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot Email using FreeMarker</title>
</head>
<body>
<div style="margin-top: 10px">Greetings,
                             This email is regarding to the change of password.
                             If you want to change the password click on below link or ignore email.
</div>
<div>Your username is <b>${userInfo.username}</b></div>
<div> Click on this to set a new password: <a href="${forgetPasswordLink}">${forgetPasswordLink}</a></div>
<br/>
<div> Have a nice day..!</div>
</body>
</html>