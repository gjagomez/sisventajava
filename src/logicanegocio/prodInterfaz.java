/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicanegocio;

/**
 *
 * @author jagomez
 */
public class prodInterfaz {
    private int id;
    private String codbarra;
    private String producto;
    private String precio;
    private String existencia;
    private String linea;
    private String fecha;

    public prodInterfaz(int id, String codbarra, String producto, String precio, String existencia, String linea, String fecha) {
        this.id = id;
        this.codbarra = codbarra;
        this.producto = producto;
        this.precio = precio;
        this.existencia = existencia;
        this.linea = linea;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public String getCodBarra() {
        return codbarra;
    }

    public String getProducto() {
        return producto;
    }

    public String getPrecio() {
        return precio;
    }

    public String getExistencia() {
        return existencia;
    }

    public String getLinea() {
        return linea;
    }

    public String getFecha() {
        return fecha;
    }
}

