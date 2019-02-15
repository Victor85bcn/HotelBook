/* Clase que contiene las funciones a desarrollar por la clase producto */

package Logica;

import Datos.vhabitacion;
import Datos.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor
 */

public class fproducto {
    
    //Attributes    
    private conexion mysql = new conexion(); //Crea una instancia a la cadena conexión con la variable mysql.
    private Connection cn = mysql.conectar(); //Crea la variable cn de tipo Connection que llamará al método conectar().
    private String sSQL = ""; //Inicializa la variable sSQL que servirá para realizar las consultas e instrucciones a la BD.
    public Integer totalregistros; //Variable pública que va a almacenar el número total de registros localizados en el formulario de búsqueda.
    //END Attributes
    
    
    //Methods
    
    public DefaultTableModel mostrar(String buscar) {
        
        //INPUT: String de búsqueda introducido por el usuario.
        //OUTPUT: Retorna una table.
        //DESC: Función que retornara los datos solicitados en formato table.
        
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","Producto","Descripción","Unidad de medida","Precio de venta"}; //Crea un array con los títulos de los campos.
        
        String [] registro = new String [5]; //Crea un array que contendrálos valores de los campos.
        
        totalregistros = 0;
        
        modelo = new DefaultTableModel(null, titulos); //Inicializa la tabla añadiendo los titulos.
        
        sSQL = "select * from producto where nombre like '%" + buscar + "%' order by idproducto desc";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL); //Ejecuta la consulta a la BD.
            
            while(rs.next()){ //Almacena los valores obtenidos del ResultSet en la posición adecuada.
                registro [0] = rs.getString("idproducto");
                registro [1] = rs.getString("nombre");
                registro [2] = rs.getString("descripcion");
                registro [3] = rs.getString("unidad_medida");
                registro [4] = rs.getString("precio_venta");
                
                totalregistros = totalregistros+1;
                
                modelo.addRow(registro); //Añade una fila por cada resultado obtenido.          
            }          
            return modelo; //Retorna la tabla con los datos almacenados.
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;   
        }
    }
   
    
    public boolean insertar (vproducto dts) {
        
        //INPUT: Recibe un objeto de tipo vproducto.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Insertará un nuevo producto, devolviendo true si se ha realizado correctamente.
        
        sSQL = "insert into producto (nombre,descripcion,unidad_medida,precio_venta)" + 
                     " values (?,?,?,?)";
        
        try { //Ejecuta la query y almacena los valores en su posición correspondiente.
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            
            
            int n = pst.executeUpdate(); //Almacenará un 0 si no consigue ejecutar la query o 1 si pudo ejecutarla..
            
            if (n!=0)  {
                return true; 
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public boolean editar (vproducto dts) {
        
        //INPUT: Recibe un objeto de tipo vproducto.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Editará un producto, devolviendo true si se ha realizado correctamente.
        
        sSQL = "update producto set nombre=?,descripcion=?,unidad_medida=?,precio_venta=?"+
                     " where idproducto=?";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getUnidad_medida());
            pst.setDouble(4, dts.getPrecio_venta());
            pst.setInt(5, dts.getIdproducto());

            
            int n = pst.executeUpdate(); //Almacenará un 0 si no consigue ejecutar la query o 1 si pudo ejecutarla..
            
            if (n!=0)  {
                return true; 
            } else {
                return false;
            }   
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public boolean eliminar (vproducto dts) {
        
        //INPUT: Recibe un objeto de tipo vproducto.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Eliminará un producto, devolviendo true si se ha realizado correctamente.
        
        sSQL = "delete from producto where idproducto=?";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdproducto());

            
            int n = pst.executeUpdate(); //Almacenará un 0 si no consigue ejecutar la query o 1 si pudo ejecutarla..
            
            if (n!=0)  {
                return true; 
            } else {
                return false;
            }               
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
}
