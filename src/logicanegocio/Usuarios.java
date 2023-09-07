
package logicanegocio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    private final String url = "jdbc:mysql://localhost:3306/VENTAS";
    private final String usuarioDB = "root";
    private final String contraseñaDB = "";
    
    public boolean verificarCredenciales(String nombreUsuario, String contraseña) {
        try {
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
            String query = "SELECT * FROM USUARIOS WHERE EMAIL = ? AND CLAVE = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, nombreUsuario);
            pst.setString(2, contraseña);
            
            ResultSet rs = pst.executeQuery();
            
            boolean credencialesValidas = rs.next();
            
            con.close();
            
            return credencialesValidas;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean saveUser(String email,String clave,String nombre,String direccion,String tel, String rolid){
      try {
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
            String query = "INSERT INTO USUARIOS (EMAIL,CLAVE,NOMBRE,DIRECCION,TELEFONO,ROLID)VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, clave);
            pst.setString(3, nombre);
            pst.setString(4, direccion);
            pst.setString(5, tel);
            pst.setString(6, rolid);
            int filasAfectadas = pst.executeUpdate();
            con.close();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
      public boolean correoExiste(String correo) {
        try {
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
            String query = "SELECT COUNT(*) FROM usuarios WHERE EMAIL = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, correo);

            ResultSet rs = pst.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            con.close();

            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
      
      
      
    
    public List<usdb> obtenerUsuarios() {
        List<usdb> usuarios = new ArrayList<>();
        Connection conexion = null;

        try {
            // Establece la conexión con la base de datos
            Connection con = DriverManager.getConnection(url, usuarioDB, contraseñaDB);

            String sql = "SELECT ID, NOMBRE, EMAIL, CLAVE, DIRECCION, TELEFONO FROM USUARIOS";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nombre = resultSet.getString("NOMBRE");
                String email = resultSet.getString("EMAIL");
                String clave = resultSet.getString("CLAVE");
                String direccion = resultSet.getString("DIRECCION");
                String telefono = resultSet.getString("TELEFONO");

                usdb usuario = new usdb(id, nombre, email, clave, direccion, telefono);
                usuarios.add(usuario);
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

        return usuarios;
    }

}
