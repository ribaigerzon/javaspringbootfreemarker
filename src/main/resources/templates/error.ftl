<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 300px;
            margin: 0 auto;
            padding: 50px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Error</h2>
        <div class="message">
            <#if message??>
                ${message}
            <#else>
                Ha ocurrido un error inesperado.
                <p>${error}</p>
            </#if>
        </div>
    </div>
</body>
</html>
