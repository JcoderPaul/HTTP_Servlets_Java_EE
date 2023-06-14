<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SimpleRegForm</title>
</head>
<body>
        <form action="/simple-reg-form" method="post">
            <label for="nameId"> Enter name:
                <input type="text" name="name" id="nameId">
            </label><br>
            <label for="birthdayId"> Birthday:
                <input type="date" name="birthday" id="birthdayId">
            </label><br>
            <label for="emailId"> Email:
                <input type="text" name="email" id="emailId">
            </label><br>
            <label for="passId"> Enter password:
                <input type="password" name="password" id="passId">
            </label><br>
            <select name="role" id="role">
                <option volue="USER">USER</option>
                <option volue="USER">ADMIN</option>
            </select><br>
            <input type="radio" name="gender" value="MALE"> MALE
            <br>
            <input type="radio" name="gender" value="FEMALE"> FEMALE
            <br>
            <button type="submit">SEND</button>
        </form>
</body>
</html>
