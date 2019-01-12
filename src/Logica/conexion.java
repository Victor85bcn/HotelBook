/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor
 */
public class conexion {
    
    //ATRIBUTOS
    
    public String db = "bdBookinghotel"; //Declaración de la variable db con el nombre de la BBDD.
    public String url = "jdbc:mysql://127.0.0.1/" + db; //Declaración de la variable url con el valor de la ruta de acceso (ip) + nombre de la BBDD.
    public String user = "root"; //Declaración de la variable user con el valor del usuario de la BBDD.
    public String pass = ""; //Declaración de la variables pass con el valor del password de la BBDD.

    
    //CONSTRUCTORES
    
    public conexion() {
    }
    
    
    //METODOS ESPECIFICOS
    
    
    public Connection conectar() {
        
        //INPUT: Nada.
        //OUTPUT: Retorna la variable link del tipo Connection.
        //DESC: Función que conecta con la BBDD utilizando los valores introducidos en la clase conexion.java
    
        Connection link =null;
        
        try {
            Class.forName("org.gjt.mm.mysql.Driver"); //Apuntamos a la libreria mysql-connector-java previamente añadida.
            link = DriverManager.getConnection(this.url, this.user, this.pass); //Conecta con la BBDD utilizando url, user y pass.
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return link; //Retorna la conexión a la BBDD.
    }
    
}
