/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicanegocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Productos {

    private final String url = "jdbc:mysql://localhost:3306/VENTAS";
    private final String usuarioDB = "root";
    private final String contraseñaDB = "";

    public boolean saveProd(String codbarra, String producto, String precio, String existencia, String linea) {
        try {
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
                String query = "INSERT INTO PRODUCTOS (CODBARRA, PRODUCTO, PRECIO, EXISTENCIA, LINEA)VALUES(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, codbarra);
            pst.setString(2, producto);
            pst.setString(3, precio);
            pst.setString(4, existencia);
            pst.setString(5, linea);

            int filasAfectadas = pst.executeUpdate();
            con.close();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProd(String codbarra, String producto, String precio, String existencia, String linea) {
        try {
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
            String query = "UPDATE PRODUCTOS SET PRODUCTO=?, PRECIO=?, EXISTENCIA=?, LINEA=? WHERE CODBARRA=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, producto);
            pst.setString(2, precio);
            pst.setString(3, existencia);
            pst.setString(4, linea);
            pst.setString(5, codbarra);

            int filasAfectadas = pst.executeUpdate();
            con.close();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProd(String codbarra) {
        try {
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
            String query = "DELETE FROM PRODUCTOS WHERE CODBARRA=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, codbarra);

            int filasAfectadas = pst.executeUpdate();
            con.close();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<prodInterfaz> obtenerProductos() {
        List<prodInterfaz> productos = new ArrayList<>();
        Connection conexion = null;

        try {
            // Establece la conexión con la base de datos
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);

            String sql = "SELECT ID, CODBARRA, PRODUCTO, PRECIO, EXISTENCIA, LINEA, FECHA FROM PRODUCTOS";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String codbarra = resultSet.getString("CODBARRA");
                String producto = resultSet.getString("PRODUCTO");
                String precio = resultSet.getString("PRECIO");
                String existencia = resultSet.getString("EXISTENCIA");
                String linea = resultSet.getString("LINEA");
                String fecha = resultSet.getString("FECHA");

                prodInterfaz prodInt = new prodInterfaz(id, codbarra, producto, precio, existencia, linea, fecha);

                productos.add(prodInt);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productos;
    }
    
    public List<prodInterfaz> cargarProductos() {
    List<prodInterfaz> productos = new ArrayList<>();
    Connection conexion = null;

    try {
        // Establece la conexión con la base de datos
        Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);

        String sql = "SELECT ID, CODBARRA, PRODUCTO, PRECIO, EXISTENCIA, LINEA, FECHA FROM PRODUCTOS";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String codbarra = resultSet.getString("CODBARRA");
            String producto = resultSet.getString("PRODUCTO");
            String precio = resultSet.getString("PRECIO");
            String existencia = resultSet.getString("EXISTENCIA");
            String linea = resultSet.getString("LINEA");
            String fecha = resultSet.getString("FECHA");

            prodInterfaz prodInt = new prodInterfaz(id, codbarra, producto, precio, existencia, linea, fecha);
            
            productos.add(prodInt);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return productos;
}
}
