/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicanegocio;




public class usdb {
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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}