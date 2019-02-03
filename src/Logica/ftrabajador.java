package Logica;

import Datos.vtrabajador;
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
public class ftrabajador {

    //Attributes    
    private conexion mysql = new conexion(); //Crea una instancia a la cadena conexión con la variable mysql.
    private Connection cn = mysql.conectar(); //Crea la variable cn de tipo Connection que llamará al método conectar().
    private String sSQL = ""; //Inicializa la variable sSQL que servirá para realizar las consultas e instrucciones a la BD.
    private String sSQL2 = ""; //Inicializa la variable sSQL2 que servirá para realizar las consultas e instrucciones a la BD.
    public Integer totalregistros; //Variable pública que va a almacenar el número total de registros localizados en el formulario de búsqueda.
    //END Attributes

    //Methods
    public DefaultTableModel mostrar(String buscar) {

        //INPUT: String de búsqueda introducido por el usuario.
        //OUTPUT: Retorna una table.
        //DESC: Función que retornara los datos solicitados en formato table.
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Apellido 1", "Apellido 2", "Tipo de documento", "Número de documento", "Dirección", "Teléfono", "Email", "Sueldo", "Acceso", "Login", "Password", "Estado"}; //Crea un array con los títulos de los campos.

        String[] registro = new String[14]; //Crea un array que contendrálos valores de los campos.

        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos); //Inicializa la tabla añadiendo los titulos.

        sSQL = "select p.idpersona,p.nombre,p.apellido1,p.apellido2,p.tipo_documento,p.num_documento,p.direccion,p.telefono,p.email,t.sueldo,t.acceso,t.login,t.password,t.estado"
                + " from persona p inner join trabajador t on p.idpersona=t.idpersona where num_documento like '%" + buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL); //Ejecuta la consulta a la BD.

            while (rs.next()) { //Almacena los valores obtenidos del ResultSet en la posición adecuada.
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido1");
                registro[3] = rs.getString("apellido2");
                registro[4] = rs.getString("tipo_documento");
                registro[5] = rs.getString("num_documento");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("telefono");
                registro[8] = rs.getString("email");
                registro[9] = rs.getString("sueldo");
                registro[10] = rs.getString("acceso");
                registro[11] = rs.getString("login");
                registro[12] = rs.getString("password");
                registro[13] = rs.getString("estado");

                totalregistros = totalregistros + 1;

                modelo.addRow(registro); //Añade una fila por cada resultado obtenido.          
            }
            return modelo; //Retorna la tabla con los datos almacenados.

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vtrabajador dts) {

        //INPUT: Recibe un objeto de tipo vtrabajador.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Insertará un nuevo cliente, devolviendo true si se ha realizado correctamente.
        sSQL = "insert into persona (nombre,apellido1,apellido2,tipo_documento,num_documento,direccion,telefono,email)"
                + " values (?,?,?,?,?,?,?,?)";

        sSQL2 = "insert into trabajador (idpersona,sueldo,acceso,login,password,estado) values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?)";

        try { //Ejecuta la query y almacena los valores en su posición correspondiente.

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido1());
            pst.setString(3, dts.getApellido2());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());

            int n = pst.executeUpdate(); //Almacenará un 0 si no consigue ejecutar la query o 1 si pudo ejecutarla..

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(vtrabajador dts) {

        //INPUT: Recibe un objeto de tipo vtrabajador.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Editará un cliente, devolviendo true si se ha realizado correctamente.
        sSQL = "update persona set nombre=?,apellido1=?,apellido2=?,tipo_documento=?,num_documento=?,direccion=?,telefono=?,email=?"
                + " where idpersona=?";

        sSQL2 = "update trabajador set sueldo=?,acceso=?,login=?,password=?,estado=? where idpersona=?";
        
        try { //Ejecuta la query y almacena los valores en su posición correspondiente.

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido1());
            pst.setString(3, dts.getApellido2());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getDireccion());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getEmail());
            pst.setInt(9, dts.getIdpersona());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());

            int n = pst.executeUpdate(); //Almacenará un 0 si no consigue ejecutar la query o 1 si pudo ejecutarla..

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vtrabajador dts) {

        //INPUT: Recibe un objeto de tipo vtrabajador.
        //OUTPUT: Devuelve un boolean true o false.
        //DESC: Eliminará un cliente, devolviendo true si se ha realizado correctamente.
        sSQL = "delete from trabajador where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try { //Ejecuta la query y almacena los valores en su posición correspondiente.

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, dts.getIdpersona());

            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate(); //Almacenará un 0 si no consigue ejecutar la query o 1 si pudo ejecutarla..

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public DefaultTableModel login(String login, String password) {

        //INPUT: String de login y password
        //OUTPUT: Retorna una table.
        //DESC: Función que comprobará que existe el login y password en la BBDD.
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Apellido 1", "Apellido 2", "Acceso", "Login", "Password", "Estado"}; //Crea un array con los títulos de los campos.

        String[] registro = new String[8]; //Crea un array que contendrálos valores de los campos.

        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos); //Inicializa la tabla añadiendo los titulos.

        sSQL = "select p.idpersona,p.nombre,p.apellido1,p.apellido2,t.acceso,t.login,t.password,t.estado"
                + " from persona p inner join trabajador t on p.idpersona=t.idpersona where t.login='" + login + "' and t.password='" + password + "' and t.estado='Activado'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL); //Ejecuta la consulta a la BD.

            while (rs.next()) { //Almacena los valores obtenidos del ResultSet en la posición adecuada.
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido1");
                registro[3] = rs.getString("apellido2");
                registro[4] = rs.getString("acceso");
                registro[5] = rs.getString("login");
                registro[6] = rs.getString("password");
                registro[7] = rs.getString("estado");

                totalregistros = totalregistros + 1;

                modelo.addRow(registro); //Añade una fila por cada resultado obtenido.          
            }
            return modelo; //Retorna la tabla con los datos almacenados.

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    

}
