<!DOCTYPE html>
<html>
<head>
    <title>Punto de Venta</title>

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
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Punto de Venta</h2>
        <h3>Bienvenido</h3>
        <form action="/login" method="post">
            <div class="form-group">
                <label for="nombre">Nombre de Usuario:</label>
                <input type="text" id="nombre" name="nombre" required>
            </div>
            <div class="form-group">
                <label for="contrasenia">Contraseña:</label>
                <input type="password" id="contrasenia" name="contrasenia" required>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
            <#if error??>
                <div class="error">${error}</div>
            </#if>
        </form>
    </div>
</body>
</html>
