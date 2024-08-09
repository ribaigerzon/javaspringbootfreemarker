<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>PV - Generar Orden de Venta</title>
        <script>
            Function obtenerToken(){
                <#if idUsuario??>
                    <p>Usuario username: ${username}</p>
                    <p>Usuario ID: ${idUsuario}</p>
                </#if>
            }
        </script>
        <script>
            function addProduct() {
                var productSelect = document.getElementById("id_producto");
                var selectedProduct = productSelect.options[productSelect.selectedIndex];
                var productName = selectedProduct.text;
                var productId = selectedProduct.value;
                var productPrice = parseFloat(selectedProduct.getAttribute("data-price"));
                var quantity = document.getElementById("cantidad").value;

                if (quantity <= 0) {
                    alert("La cantidad debe ser mayor a 0");
                    return;
                }

                var table = document.getElementById("productosTable");
                var row = table.insertRow(-1);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);

                cell1.innerHTML = productName;
                cell2.innerHTML = quantity;
                cell3.innerHTML = productPrice;
                cell4.innerHTML = (quantity * productPrice).toFixed(2);

                var totalInput = document.getElementById("total");
                totalInput.value = (parseFloat(totalInput.value) + (quantity * productPrice)).toFixed(2);

                // Add hidden inputs for the product ID and quantity
                var hiddenInputProduct = document.createElement("input");
                hiddenInputProduct.type = "hidden";
                hiddenInputProduct.name = "productIds";
                hiddenInputProduct.value = productId;
                document.getElementById("productInputs").appendChild(hiddenInputProduct);

                var hiddenInputQuantity = document.createElement("input");
                hiddenInputQuantity.type = "hidden";
                hiddenInputQuantity.name = "quantities";
                hiddenInputQuantity.value = quantity;
                document.getElementById("productInputs").appendChild(hiddenInputQuantity);
            }
        </script>
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
            table {
                width: 100%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
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
                <h1>Generar Orden de Venta</h1>

                <#if error??>
                    <p style="color:red;">${error}</p>
                </#if>

                <form action="/generar_orden" method="post">
                    <input type="hidden" name="token" value="${token!}"/>

                    <label for="fechaVenta">Fecha de Venta:</label>
                    <input type="date" name="fechaVenta" id="fechaVenta" required/>
                    <br/>

                    <label for="id_producto">Producto:</label>
                    <select name="id_producto" id="id_producto" required>
                        <#list productos as producto>
                            <option value="${producto.id_producto}" data-price="${producto.precio}">${producto.nombre}</option>
                        </#list>
                    </select>
                    <br/>

                    <label for="cantidad">Cantidad:</label>
                    <input type="number" name="cantidad" id="cantidad" required/>
                    <br/>

                    <button type="button" onclick="addProduct()">Agregar Producto</button>

                    <table id="productosTable">
                        <thead>
                            <tr>
                                <th>Producto</th>
                                <th>Cantidad</th>
                                <th>Precio Unitario</th>
                                <th>Importe</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Rows will be added here by JavaScript -->
                        </tbody>
                    </table>

                    <div id="productInputs">
                        <!-- Hidden inputs for product IDs and quantities will be added here by JavaScript -->
                    </div>

                    <label for="total">Total:</label>
                    <input type="text" id="total" name="total" value="0.00" readonly/>
                    <br/>

                    <button type="submit">Generar Orden</button>
                </form>
                <a href="/ver_ordenes">Ver Órdenes de Venta</a>
            </div>
        </div>
    </body>
</html>
