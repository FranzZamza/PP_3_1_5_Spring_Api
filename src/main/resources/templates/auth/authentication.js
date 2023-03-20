$(document).ready(function () {
    $('form').submit(function (event) {
        event.preventDefault();
        const username = $('#username').val();
        const email = $('#email').val();
        const password = $('#password').val();
        const age = $('#age').val();
        const lastname = $('#lastname').val()

        let data = {
            'username': username,
            'email': email,
            'password': password,
            'age': age,
            'lastname': lastname
        };

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/auth/registration',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                console.log(response);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});