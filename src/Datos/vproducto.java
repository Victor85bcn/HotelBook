/*Clase producto que contendrá los diversos consumibles para los clientes*/

package Datos;

/**
 *
 * @author Victor
 */

public class vproducto {
    
    //Attributes
    
    private int idproducto;
    private String nombre;
    private String descripcion;
    private String unidad_medida;
    private double precio_venta;
    
    //END Attributes
    
    
    //Constructors

    public vproducto(int idproducto, String nombre, String descripcion, String unidad_medida, double precio_venta) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad_medida = unidad_medida;
        this.precio_venta = precio_venta;
    }

    public vproducto() {
    }
    
    //END Constructors
    
    
    //Getters & Setters

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    //END Getters & Setters
    
    
    
    
    
}
