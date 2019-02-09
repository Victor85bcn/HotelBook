/* Clase que contiene las funciones a desarrollar por la clase reserva */

package Logica;

import Datos.vreserva;
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

public class freserva {
    
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
        
        String [] titulos = {"ID reserva","ID habitación","Número habitación","ID cliente","Cliente", "ID trabajador", "Trabajador", "Tipo reserva", "Fecha reserva", "Fecha entrada", "Fecha salida", "Coste alojamiento", "Estado reserva" }; //Crea un array con los títulos de los campos.
        
        String [] registro = new String [13]; //Crea un array que contendrá los valores de los campos.
        
        totalregistros = 0;
        
        modelo = new DefaultTableModel(null, titulos); //Inicializa la tabla añadiendo los titulos.
        
        sSQL = "select r.idreserva,r.idhabitacion,h.numero,r.idcliente,(select nombre from persona where idpersona=r.idcliente) as clienten,(select apellido1 from persona where idpersona=r.idcliente)" +
                    "as clienteap,r.idtrabajador,(select nombre from persona where idpersona=r.idtrabajador) as trabajadorn,(select apellido1 from persona where idpersona=r.idtrabajador) as trabajadorap," +
                    "r.tipo_reserva,r.fecha_reserva,r.fecha_entrada,r.fecha_salida,r.coste_alojamiento,r.estado from reserva r inner join habitacion h on r.idhabitacion=h.idhabitacion where r.fecha_reserva " +
                    "like '%" + buscar + "%' order by idreserva desc";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL); //Ejecuta la consulta a la BD.
            
            while(rs.next()){ //Almacena los valores obtenidos del ResultSet en la posición adecuada.
                registro [0] = rs.getString("idreserva");
                registro [1] = rs.getString("idhabitacion");
                registro [2] = rs.getString("numero");
                registro [3] = rs.getString("idcliente");
                registro [4] = rs.getString("clienten") + " " + rs.getString("clienteap");
                registro [5] = rs.getString("idtrabajador");
                registro [6] = rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
                registro [7] = rs.getString("tipo_reserva");
                registro [8] = rs.getString("fecha_reserva");
                registro [9] = rs.getString("fecha_entrada");
                registro [10] = rs.getString("fecha_salida");
                registro [11] = rs.getString("coste_alojamiento");
                registro [12] = rs.getString("estado");

                totalregistros = totalregistros+1;
                
                modelo.addRow(registro); //Añade una fila por cada resultado obtenido.          
            }          
            return modelo; //Retorna la tabla con los datos almacenados.
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;   
        }
    }
   
    
    public boolean insertar (vreserva dts) {
        
        //INPUT: Recibe un objeto de tipo vreserva.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Insertará un nuevo producto, devolviendo true si se ha realizado correctamente.
        
        sSQL = "insert into reserva (idhabitacion,idcliente,idtrabajador,tipo_reserva,fecha_reserva,fecha_entrada,fecha_salida,coste_alojamiento,estado)" + 
                     " values (?,?,?,?,?,?,?,?,?)";
        
        try { //Ejecuta la query y almacena los valores en su posición correspondiente.
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getFecha_reserva());
            pst.setDate(6, dts.getFecha_entrada());
            pst.setDate(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCoste_alojamiento());
            pst.setString(9, dts.getEstado());

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
    
    
    public boolean editar (vreserva dts) {
        
        //INPUT: Recibe un objeto de tipo vreserva.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Editará un producto, devolviendo true si se ha realizado correctamente.
        
        sSQL = "update reserva set idhabitacion=?,idcliente=?,idtrabajador=?,tipo_reserva=?,fecha_reserva=?,fecha_entrada=?,fecha_salida=?,coste_alojamiento=?,estado=?"+
                     " where idreserva=?";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdhabitacion());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_reserva());
            pst.setDate(5, dts.getFecha_reserva());
            pst.setDate(6, dts.getFecha_entrada());
            pst.setDate(7, dts.getFecha_salida());
            pst.setDouble(8, dts.getCoste_alojamiento());
            pst.setString(9, dts.getEstado());
                      
            pst.setInt(10, dts.getIdreserva());

            
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
    
    
    public boolean eliminar (vreserva dts) {
        
        //INPUT: Recibe un objeto de tipo vreserva.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Eliminará un producto, devolviendo true si se ha realizado correctamente.
        
        sSQL = "delete from reserva where idreserva=?";
        
        try {
            
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdreserva());

            
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
