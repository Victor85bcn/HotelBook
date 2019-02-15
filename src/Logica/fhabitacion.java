/*Esta clase contiene todas las funciones a realizar para las habitaciones*/

package Logica;

import Datos.vhabitacion;
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
public class fhabitacion {
    
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
        
        String [] titulos = {"ID","Número","Piso","Descripción","Características","Precio","Estado","Tipo habitación"};
        
        String [] registro = new String [8];
        
        totalregistros = 0;
        
        modelo = new DefaultTableModel(null, titulos); //Inicializa la tabla añadiendo los titulos.
        
        sSQL = "select * from habitacion where piso like '%" + buscar + "%' order by idhabitacion";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL); //Ejecuta la consulta a la BD.
            
            while(rs.next()){ //Almacena los valores obtenidos del ResultSet en la posición adecuada.
                registro [0] = rs.getString("idhabitacion");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("piso");
                registro [3] = rs.getString("descripcion");
                registro [4] = rs.getString("caracteristicas");
                registro [5] = rs.getString("precio_diario");
                registro [6] = rs.getString("estado");
                registro [7] = rs.getString("tipo_habitacion");
                
                totalregistros = totalregistros+1;
                
                modelo.addRow(registro); //Añade una fila por cada resultado obtenido.          
            }          
            return modelo; //Retorna la tabla con los datos almacenados.
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;   
        }
    }
   
    
    public boolean insertar (vhabitacion dts) {
        
        //INPUT: Recibe un objeto de tipo vhabitacion.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Insertará una nueva habitación, devolviendo true si se ha realizado correctamente.
        
        sSQL = "insert into habitacion (numero,piso,descripcion,caracteristicas,precio_diario,estado,tipo_habitacion)" + 
                     " values (?,?,?,?,?,?,?)";
        
        try { //Ejecuta la query y almacena los valores en su posición correspondiente.
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            
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
    
    
    public boolean editar (vhabitacion dts) {
        
        //INPUT: Recibe un objeto de tipo vhabitacion.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Editará una habitación, devolviendo true si se ha realizado correctamente.
        
        sSQL = "update habitacion set numero=?,piso=?,descripcion=?,caracteristicas=?,precio_diario=?,estado=?,tipo_habitacion=?"+
                     " where idhabitacion=?";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNumero());
            pst.setString(2, dts.getPiso());
            pst.setString(3, dts.getDescripcion());
            pst.setString(4, dts.getCaracteristicas());
            pst.setDouble(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getTipo_habitacion());
            pst.setInt(8, dts.getIdhabitacion());

            
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
    
    
    public boolean eliminar (vhabitacion dts) {
        
        //INPUT: Recibe un objeto de tipo vhabitacion.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Eliminará una habitación, devolviendo true si se ha realizado correctamente.
        
        sSQL = "delete from habitacion where idhabitacion=?";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());

            
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
    
    
    public DefaultTableModel mostrarvista(String buscar) {
        
        //INPUT: String de búsqueda introducido por el usuario.
        //OUTPUT: Retorna una table.
        //DESC: Función que retornara los datos solicitados en formato table.
        
        DefaultTableModel modelo;
        
        String [] titulos = {"ID","Número","Piso","Descripción","Características","Precio","Estado","Tipo habitación"};
        
        String [] registro = new String [8];
        
        totalregistros = 0;
        
        modelo = new DefaultTableModel(null, titulos); //Inicializa la tabla añadiendo los titulos.
        
        sSQL = "select * from habitacion where piso like '%" + buscar + "%' and estado='Disponible' order by idhabitacion";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL); //Ejecuta la consulta a la BD.
            
            while(rs.next()){ //Almacena los valores obtenidos del ResultSet en la posición adecuada.
                registro [0] = rs.getString("idhabitacion");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("piso");
                registro [3] = rs.getString("descripcion");
                registro [4] = rs.getString("caracteristicas");
                registro [5] = rs.getString("precio_diario");
                registro [6] = rs.getString("estado");
                registro [7] = rs.getString("tipo_habitacion");
                
                totalregistros = totalregistros+1;
                
                modelo.addRow(registro); //Añade una fila por cada resultado obtenido.          
            }          
            return modelo; //Retorna la tabla con los datos almacenados.
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;   
        }
    }
    
    
    
    
}
