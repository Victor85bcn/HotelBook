
package Datos;

/**
 *
 * @author Victor
 */
public class vtrabajador extends vpersona{
    
    //Attributes
    
    Double sueldo;
    String acceso;
    String login;
    String password;
    String estado;
    
    //END Attributes

    
    //Constructors
    
    public vtrabajador() {
    }

    public vtrabajador(Double sueldo, String acceso, String login, String password, String estado) {
        this.sueldo = sueldo;
        this.acceso = acceso;
        this.login = login;
        this.password = password;
        this.estado = estado;
    }
    
    //END Constructors
    
    
    //Getters and Setters

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    //END Getters ans Setters
    
    
    
    
    
    
}
