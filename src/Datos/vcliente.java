
package Datos;

/**
 *
 * @author Victor
 */

public class vcliente extends vpersona {
    
    //Attributes
    
    private String codigo_cliente;

    //END Attributes
    
    
    // Constructors

    public vcliente() {
    }

    public vcliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
    
    //END Constructors
    
    
    //Getters and Setters

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
    
    //END Getters and Setters
    
}
