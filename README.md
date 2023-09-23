
# SIS VENTA UMG

Este sistema incluye una serie de procesos, herramientas, estrategias y tecnologías diseñadas para maximizar las ventas de productos o servicios de la empresa, esta demo usa lector de codigo de barra para la salida de productos


=====

[![Build Status](https://travis-ci.org/pouchdb-community/pouchdb-authentication.svg?branch=master)](https://travis-ci.org/pouchdb-community/pouchdb-authentication)
[![Greenkeeper badge](https://badges.greenkeeper.io/pouchdb-community/pouchdb-authentication.svg)](https://greenkeeper.io/)

Conexion db con Mysql Server.

```js
    private final String url = "jdbc:mysql://localhost:3306/VENTAS";
    private final String usuarioDB = "root";
    private final String contraseñaDB = "";
    Connection con = DriverManager.getConnection(url,      usuarioDB, contraseñaDB);
    String query = "SELECT * FROM USUARIOS WHERE EMAIL = ? AND CLAVE = ?";

```


Overview
----------

Punto de venta realizado en java netbeens,Librerias JAR usadas

* **MysqlDriver 
* **ItextSharp
* **JDK 20
* **Gradient icon
* **icepdf
* **table-custum

Estos componentes no pertenecen a Sisventa, estos componentes son opensorce

### Estado del proyecto

Este proyectos un mvp aun falta implementar varios modulos.


Utilidad
------

* [JavaNetbeen](https://github.com/pouchdb-community/pouchdb-authentication/blob/master/docs/setup.md)
* [Librerias JAR](https://github.com/pouchdb-community/pouchdb-authentication/blob/master/docs/api.md)


Implementacion de Listas,ArrayList
------
```js
     public List<prodInterfaz> cargarProductos() {
    List<prodInterfaz> productos = new ArrayList<>();
    Connection conexion = null;

```

Uso de interfaces de datos
------
```js
   private int id;
    private String nombre;
    private String email;
    private String clave;
    private String direccion;
    private String telefono;

    public usdb(int id, String nombre, String email, String clave, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
        this.direccion = direccion;
        this.telefono = telefono;
    }

```


## Captura de pantalla



## Login

![App Screenshot](https://raw.githubusercontent.com/gjagomez/sisventajava/main/captura/login%20prin.png)

## Pantalla principal

![App Screenshot](https://raw.githubusercontent.com/gjagomez/sisventajava/main/captura/im1.png)


## Pantalla facturacion

![App Screenshot](https://raw.githubusercontent.com/gjagomez/sisventajava/main/captura/img2.png)


## Pantalla Usuarios

![App Screenshot](https://raw.githubusercontent.com/gjagomez/sisventajava/main/captura/usuarios.png)


## Pantalla ingreso de usuarios

![App Screenshot](https://raw.githubusercontent.com/gjagomez/sisventajava/main/captura/add%20us.png)


## Pantalla ingreso producto

![App Screenshot](https://raw.githubusercontent.com/gjagomez/sisventajava/main/captura/ingresprod.png)



## Authors

- [@Javier Gomez Riz ](https://www.github.com/octokatherine)


