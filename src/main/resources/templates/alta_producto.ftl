<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>PV - Alta Producto</title>
        <script>
            Function obtenerToken(){
                <#if idUsuario??>
                    <p>Usuario username: ${username}</p>
                </#if>
            }
        </script>
        <style>
            body {
                font-family: Arial, sans-serif;
                display: flex;
            }
            .sidebar {
                width: 250px;
                background-color: #f8f9fa;
                padding: 10px;
                box-shadow: 2px 0 5px rgba(0,0,0,0.1);
                height: 100vh;
            }
            .sidebar h2 {
                font-size: 18px;
                margin-bottom: 10px;
            }
            .sidebar ul {
                list-style: none;
                padding: 0;
            }
            .sidebar ul li {
                margin-bottom: 10px;
            }
            .sidebar ul li a {
                text-decoration: none;
                color: #000;
                display: block;
                padding: 5px;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            .sidebar ul li a:hover {
                background-color: #e9ecef;
            }
            .sidebar ul li ul {
                list-style: none;
                padding: 0;
                padding-left: 20px;
                display: none;
            }
            .sidebar ul li ul li a {
                padding: 5px 5px;
            }
            .main {
                flex-grow: 1;
                padding: 20px;
            }
            .header {
                background-color: #343a40;
                color: #fff;
                padding: 10px 20px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .header .username {
                cursor: pointer;
                position: relative;
            }
            .header .username ul {
                display: none;
                position: absolute;
                right: 0;
                background-color: #fff;
                color: #000;
                list-style: none;
                padding: 10px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .header .username:hover ul {
                display: block;
            }
        </style>
        <script>
            function toggleSubmenu(id) {
                var submenu = document.getElementById(id);
                if (submenu.style.display === 'block') {
                    submenu.style.display = 'none';
                } else {
                    submenu.style.display = 'block';
                }
            }
        </script>
    </head>
    <body>
        <div class="sidebar">
            <h2>Menú</h2>
            <ul>
                <li><a href="javascript:void(0)" onclick="toggleSubmenu('usuariosSubmenu')">Usuarios</a>
                    <ul id="usuariosSubmenu">
                        <li><a href="/alta_usuario">Alta de Usuarios</a></li>
                        <li><a href="/ver_usuario">Ver Usuarios</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0)" onclick="toggleSubmenu('productosSubmenu')">Productos</a>
                    <ul id="productosSubmenu">
                        <li><a href="/alta_producto">Alta Productos</a></li>
                        <li><a href="/ver_producto">Inventario</a></li>
                    </ul>
                </li>
                <li><a href="javascript:void(0)" onclick="toggleSubmenu('ordenesVentaSubmenu')">Órdenes de Venta</a>
                    <ul id="ordenesVentaSubmenu">
                        <li><a href="/generar_orden">Generar Orden de Venta</a></li>
                        <li><a href="/ver_ordenes">Ver Órdenes de Venta</a></li>
                    </ul>
                </li>
                <#--<li><a href="javascript:void(0)" onclick="toggleSubmenu('reportesSubmenu')">Reportes</a>
                    <ul id="reportesSubmenu">
                        <li><a href="/reportes/ordenes">Órdenes de Venta</a></li>
                        <li><a href="/reportes/inventario">Inventario</a></li>
                    </ul>
                </li>-->
            </ul>
        </div>
        <div class="main">
            <div class="header">
                <div>Bienvenido, ${username}!</div>
                <div class="username">Usuario
                    <ul>
                        <li><a href="/logout">Cerrar Sesión</a></li>
                    </ul>
                </div>
            </div>
            <div class="content">
                <h1>Alta de Productos</h1>

                <form action="/alta_producto" method="post">
                    <label for="codigo">Código de Producto:</label>
                    <input type="text" id="codigo" name="codigo" required><br><br>

                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required><br><br>

                    <label for="descripcion">Descripción:</label>
                    <input type="text" id="descripcion" name="descripcion" ><br><br>

                    <label for="precio">Precio:</label>
                    <input type="text" id="precio" name="precio" required><br><br>

                    <button type="submit">Dar de Alta</button>
                </form>
                <#if error??>
                    <p style="color:red;">${error}</p>
                </#if>
            </div>
        </div>
    </body>
</html>