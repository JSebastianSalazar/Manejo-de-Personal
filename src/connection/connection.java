package connection;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
  
    static String user = "root";
    // se crea la clave  static 
    static String password = "01234";
    // se crea la url a la cual se va conectar 
    static String url = "jdbc:mysql://localhost/personal";
    // se crea una variable tipo connection de la libreia de mysql 
    static Connection con;
    // se crea el driver para saber a cual nos vamos a conectar para mas aldelaten conectarnos a el 
    static String driver = "com.mysql.jdbc.Driver";

    public static Connection conexion() {
        try {
            //se declara el driver de mysql
            Class.forName(driver);
            //se crea una conexion
            con =  (Connection) DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException ex) {
            // fallo al conectarse con el driver de mysql
            System.out.println("fail to start  mysql driver " + ex);
        } catch (SQLException ex) {
            //error al conectarse con la base de datos
            System.out.println("Not connection to the database" + ex);
        }
        return con;
    }
}
