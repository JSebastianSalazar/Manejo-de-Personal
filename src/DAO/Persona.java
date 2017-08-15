package DAO;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import modelo.minero;
import connection.connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.InactivoPersonal;
import modelo.Prestamo;

public class Persona {

    public int insertarMinero(minero min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL insertarMinero(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula());
            procedure.setString(2, min.getNombre());
            procedure.setString(3, min.getCamisa());
            procedure.setString(4, min.getPanta());
            procedure.setString(5, min.getBotas());
            procedure.setString(6, min.getSalud());
            procedure.setString(7, min.getPension());
            procedure.setString(8, min.getArl());
            procedure.setString(9, min.getCompensa());
            procedure.setString(10, min.getFecha_Na());
            procedure.setInt(11, min.getSalario());
            procedure.setString(13, min.getEmpresa());
            procedure.setString(12, min.getCargo());
            procedure.registerOutParameter(14, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(14); //obtiene lo retornado en el procedimiento
            return insertado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return insertado;
            }
        }
    }

    public int EliminarMinero(minero min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL EliminarMina(?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula());
            procedure.setString(2, min.getFecha_Na());
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(3); //obtiene lo retornado en el procedimiento

            return insertado;

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                System.out.println(insertado + "a");
                return insertado;
            }
        }
    }

    public List minerosActivos() {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<minero> lista = null;
        minero u;
        try {
            sql = "select p.cedula,p.nombre,p.salud,p.pension,p.cargo,p.arl,p.salario,p.empresa,p.talla_pantalon,p.talla_botas,p.talla_camisa,\n"
                    + " e.fecha_ingreso\n"
                    + " from personal as p, estado as e where p.idpersonal = e.personal_idpersonal\n"
                    + " AND p.tipo='activo' AND e.fecha_salida IS NULL AND e.dias IS NULL order by p.nombre ASC";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new minero();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre"));
                u.setArl(rs.getString("arl"));
                u.setSalud(rs.getString("salud"));
                u.setPension(rs.getString("pension"));
                u.setCargo(rs.getString("cargo"));
                u.setSalario(rs.getInt("salario"));
                u.setEmpresa(rs.getString("empresa"));
                u.setPanta(rs.getString("talla_pantalon"));
                u.setFecha_Na(rs.getString("fecha_ingreso"));
                u.setBotas(rs.getString("talla_botas"));
                u.setCamisa(rs.getString("talla_camisa"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (instructores) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public int insertarBene(minero min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL insertarBeneficiario (?,?,?,?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula());
            procedure.setString(2, min.getCedulaBene());
            procedure.setString(3, min.getTipo());
            procedure.setString(4, min.getParente());
            procedure.setString(5, min.getNombreBene());
            procedure.registerOutParameter(6, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(6); //obtiene lo retornado en el procedimiento
            return insertado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return insertado;
            }
        }
    }

    public int insertarPrestamo(Prestamo min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL generarPrestamo (?,?,?,?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula1());
            procedure.setDouble(2, min.getValor());
            procedure.setDouble(3, min.getSaldo());
            procedure.setInt(4, min.getCuotas());
            procedure.setString(5, min.getFecha_());
            procedure.registerOutParameter(6, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(6); //obtiene lo retornado en el procedimiento
            return insertado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return insertado;
            }
        }
    }

    public List personalInactivo() {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<InactivoPersonal> lista = null;
        InactivoPersonal u;
        try {
            sql = "select p.cedula,p.nombre,p.salario,p.empresa,p.salud,max(e.dias),"
                    + "p.cargo,p.talla_camisa,p.talla_pantalon,p.talla_botas,\n"
                    + " max(e.fecha_ingreso),max(e.fecha_salida) from estado as e, personal as p\n"
                    + " where p.idpersonal=e.personal_idpersonal"
                    + " and e.fecha_salida  IS NOT NULL and P.tipo='inactivo'\n"
                    + " group by p.idpersonal order by p.nombre ASC;";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new InactivoPersonal();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre"));
                u.setSalario(rs.getInt("salario"));
                u.setEmpresa(rs.getString("empresa"));
                u.setSalud(rs.getString("salud"));
                u.setDias(rs.getInt("max(e.dias)"));
                u.setCargo(rs.getString("cargo"));
                u.setCamisa(rs.getString("talla_camisa"));
                u.setPantalon(rs.getString("talla_pantalon"));
                u.setBotas(rs.getString("talla_botas"));
                u.setFecha_ingreso(rs.getString("max(e.fecha_ingreso)"));
                u.setFecha_salida(rs.getString("max(e.fecha_salida)"));
                lista.add(u);

            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (instructores) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public int activarTrabajador(minero min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL ActivarUsuario(?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula());
            procedure.setString(2, min.getFecha_Na());
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(3); //obtiene lo retornado en el procedimiento

            return insertado;

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                System.out.println(insertado + "a");
                return insertado;
            }
        }
    }

    public List busquedaActivoXcedula(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<minero> lista = null;
        minero u;
        try {
            sql = "select p.cedula,p.nombre,p.cargo,p.talla_camisa,p.talla_pantalon,p.talla_botas,\n"
                    + "p.salud,p.pension,p.arl,p.caja_compensacion,p.salario,p.empresa,max(e.fecha_ingreso)\n"
                    + "from personal as p, estado as e  where p.idpersonal=e.personal_idpersonal AND p.tipo='activo' and p.cedula=?";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            System.out.println(cedula);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new minero();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre"));
                u.setCargo(rs.getString("cargo"));
                u.setCamisa(rs.getString("talla_camisa"));
                u.setPanta(rs.getString("talla_pantalon"));
                u.setBotas(rs.getString("talla_botas"));
                u.setSalud(rs.getString("salud"));
                u.setPension(rs.getString("pension"));
                u.setArl(rs.getString("arl"));
                u.setCompensa(rs.getString("caja_compensacion"));
                u.setSalario(rs.getInt("salario"));
                u.setEmpresa(rs.getString("empresa"));
                u.setFecha_Na(rs.getString("max(e.fecha_ingreso)"));

                lista.add(u);
                System.out.println(u.getCedula());
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando en el metodo busqueda X activo " + ex);
            } finally {
                return lista;
            }
        }
    }

    public int busquedaCedula(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        int ce = 0;
        try {
            sql = "select cedula from personal where cedula=? AND tipo='activo'";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ce = 1;
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en el metodo  busqueda cedula " + ex);
            } finally {
                return ce;
            }
        }
    }

    public List reporteXcentro(String centro) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<minero> lista = null;
        minero u;
        try {
            sql = "select p.cedula,p.nombre,p.cargo,p.talla_camisa,p.talla_pantalon,p.talla_botas,\n"
                    + "p.salud,p.pension,p.arl,p.caja_compensacion,p.salario,p.empresa,max(e.fecha_ingreso)"
                    + "from personal as p, estado as e  where p.idpersonal=e.personal_idpersonal AND "
                    + "p.tipo='activo' and empresa=? group by p.idpersonal";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, centro);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new minero();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre"));
                u.setCargo(rs.getString("cargo"));
                u.setCamisa(rs.getString("talla_camisa"));
                u.setPanta(rs.getString("talla_pantalon"));
                u.setBotas(rs.getString("talla_botas"));
                u.setSalud(rs.getString("salud"));
                u.setPension(rs.getString("pension"));
                u.setArl(rs.getString("arl"));
                u.setCompensa(rs.getString("caja_compensacion"));
                u.setSalario(rs.getInt("salario"));
                u.setEmpresa(rs.getString("empresa"));
                u.setFecha_Na(rs.getString("max(e.fecha_ingreso)"));
                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (instructores) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public List busquedaBeneficiarioXcedulaActivo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<minero> lista = null;
        minero u;
        try {
            sql = "select b.documento,b.tipoDocumento,b.parentezco,b.nombrePersona\n"
                    + "from beneficiarios as b,personal as p "
                    + "where p.idpersonal=b.idpersona and p.cedula=? and p.tipo='activo';";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new minero();
                u.setCedulaBene(rs.getString("documento"));
                u.setParente(rs.getString("parentezco"));
                u.setTipo(rs.getString("tipoDocumento"));
                u.setNombreBene(rs.getString("nombrePersona"));
                lista.add(u);
                System.out.println(u.getCedula());
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando en el metodo busqueda X activo " + ex);
            } finally {
                return lista;
            }
        }
    }

    public List personalInactivoXcentro(String empresa) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<InactivoPersonal> lista = null;
        InactivoPersonal u;
        try {
            sql = "SELECT p.cedula,p.nombre,p.salario,p.empresa,MAX(e.dias),\n"
                    + " p.cargo, MAX(e.fecha_ingreso), MAX(e.fecha_salida)\n"
                    + "FROM estado AS e, personal AS p\n"
                    + "WHERE p.idpersonal=e.personal_idpersonal AND e.fecha_salida"
                    + " IS NOT NULL AND P.tipo='inactivo' AND p.empresa=? \n"
                    + "GROUP BY p.idpersonal;";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, empresa);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new InactivoPersonal();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre"));
                u.setSalario(rs.getInt("salario"));
                u.setEmpresa(rs.getString("empresa"));
                u.setDias(rs.getInt("max(e.dias)"));
                u.setCargo(rs.getString("cargo"));
                u.setFecha_ingreso(rs.getString("max(e.fecha_ingreso)"));
                u.setFecha_salida(rs.getString("max(e.fecha_salida)"));
                lista.add(u);
                System.out.println(u.getCedula() + u.getFecha_salida() + u.getCargo() + u.getNombre() + u.getFecha_salida());
            }
        } catch (SQLException ex) {
            System.out.println("Listando aa " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (instructores) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public List busquedaBeneficiarioXcedulaInactivo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<minero> lista = null;
        minero u;
        try {
            sql = "select b.documento,b.tipoDocumento,b.parentezco,b.nombrePersona\n"
                    + "from beneficiarios as b,personal as p "
                    + "where p.idpersonal=b.idpersona and p.cedula=? and p.tipo='inactivo'";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new minero();
                u.setCedulaBene(rs.getString("documento"));
                u.setParente(rs.getString("parentezco"));
                u.setTipo(rs.getString("tipoDocumento"));
                u.setNombreBene(rs.getString("nombrePersona"));
                lista.add(u);
                System.out.println(u.getCedula());
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando en el metodo busqueda X activo " + ex);
            } finally {
                return lista;
            }
        }
    }

    public int busquedaCedulaInactivo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        int ce = 0;
        try {
            sql = "select cedula from personal where cedula=? AND tipo='inactivo'";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ce = 1;
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en el metodo  busqueda cedula " + ex);
            } finally {
                return ce;
            }
        }
    }

    public List busquedaActivoXcedulaInactivo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<minero> lista = null;
        minero u;
        try {
            sql = "select p.cedula,p.nombre,p.cargo,p.talla_camisa,p.talla_pantalon,p.talla_botas,\n"
                    + "p.salud,p.pension,p.arl,p.caja_compensacion,p.salario,p.empresa,max(e.fecha_ingreso),max(e.fecha_salida),max(e.dias)\n"
                    + "from personal as p, estado as e  where p.idpersonal=e.personal_idpersonal AND p.tipo='inactivo' and p.cedula=?";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new minero();
                u.setCedula(rs.getString("cedula"));
                u.setNombre(rs.getString("nombre"));
                u.setCargo(rs.getString("cargo"));
                u.setCamisa(rs.getString("talla_camisa"));
                u.setPanta(rs.getString("talla_pantalon"));
                u.setBotas(rs.getString("talla_botas"));
                u.setSalud(rs.getString("salud"));
                u.setPension(rs.getString("pension"));
                u.setArl(rs.getString("arl"));
                u.setCompensa(rs.getString("caja_compensacion"));
                u.setSalario(rs.getInt("salario"));
                u.setEmpresa(rs.getString("empresa"));
                u.setFecha_Na(rs.getString("max(e.fecha_ingreso)"));
                u.setFecha_salida(rs.getString("max(e.fecha_salida)"));
                u.setDiasLaborales(rs.getInt("max(e.dias)"));

                lista.add(u);
                System.out.println(u.getCedula());
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando en el metodo busqueda X activo " + ex);
            } finally {
                return lista;
            }
        }

    }

    public int generarPrestamo(Prestamo min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL modificarPrestamos(?,?,?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(2, min.getCedula1());
            procedure.setDouble(1, min.getValor());
            procedure.setDouble(3, min.getSaldo());
            procedure.setString(4, min.getFecha_());
            procedure.registerOutParameter(5, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(5); //obtiene lo retornado en el procedimiento
            return insertado;
        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                return insertado;
            }
        }
    }

    public int busquedaCedulaXPrestamoActivo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        int ce = 0;
        try {
            sql = "select max(pr.idprestamos) from prestamos as pr,personal as p"
                    + " where p.idpersonal=pr.personal_idpersonal and p.cedula=? and\n"
                    + "p.tipo='activo' and pr.tipo='activo' group by pr.personal_idpersonal";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            while (rs.next()) {

                ce = 1;

            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en el metodo  busqueda cedula " + ex);
            } finally {
                return ce;
            }
        }
    }

    public int abonoCuenta(Prestamo min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL abonoPrestamo(?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula1());
            procedure.setInt(2, min.getCuotas());
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(3); //obtiene lo retornado en el procedimiento

            return insertado;

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                System.out.println(insertado + "a");
                return insertado;
            }
        }
    }

    public int abonoCuentaRefuerzo(Prestamo min) {
        int insertado = 0;
        CallableStatement procedure = null;
        Connection con = connection.conexion();
        try {
            String pro = " CALL abonoRefuerzo(?,?,?) ";
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure = (CallableStatement) con.prepareCall(pro);
            procedure.setString(1, min.getCedula1());
            procedure.setInt(2, min.getCuotas());
            procedure.registerOutParameter(3, Types.INTEGER);
            procedure.execute();
            insertado = procedure.getInt(3); //obtiene lo retornado en el procedimiento

        } catch (SQLException ex) {
            System.out.println("Error al insertar " + ex.getMessage());
        } finally {
            try {
                procedure.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("error cerrando conexion");
            } finally {
                System.out.println(insertado + "a");
                return insertado;
            }
        }
    }

    public int busquedaPrestamoActivo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        int ce = 0;
        try {
            sql = "select max(pr.idPrestamos) from prestamos as pr, personal as p where "
                    + "p.idpersonal=pr.personal_idPersonal and p.cedula=? and pr.tipo='activo';";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Prestamo ab = new Prestamo();
                ab.setCedula1(rs.getString("max(pr.idPrestamos)"));

                ce = Integer.parseInt(ab.getCedula1());

                System.out.println(ce);
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en el metodo  busqueda cedula " + ex);
            } finally {
                return ce;
            }
        }
    }

    public List listarPrestamos() {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<Prestamo> lista = null;
        Prestamo u;
        try {
            sql = "select p.cedula,p.nombre,pr.valor,pr.fecha_prestamo,\n"
                    + "pr.coutas,pr.saldo_cuota,pr.pago,pr.abono from prestamos as pr, personal as p where\n"
                    + "p.idpersonal=pr.personal_idpersonal and p.tipo='activo' and pr.tipo='activo'\n"
                    + " group by pr.idprestamos order by p.nombre ASC;";
            pstm = (PreparedStatement) con.prepareStatement(sql);

            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Prestamo();
                u.setCedula1(rs.getString("cedula"));
                u.setNombre1(rs.getString("nombre"));
                u.setValor(rs.getInt("valor"));
                u.setFecha_(rs.getString("fecha_prestamo"));
                u.setCuotas(rs.getInt("coutas"));
                u.setSaldo(rs.getInt("saldo_cuota"));
                u.setPago(rs.getInt("pago"));
                u.setAbono(rs.getInt("abono"));

                lista.add(u);
            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en daoUsuario (instructores) " + ex);
            } finally {
                return lista;
            }
        }
    }

    public List ReportePrestamosCedula(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<Prestamo> lista = null;
        Prestamo u;
        try {
            sql = "select pr.valor,pr.fecha_prestamo,pr.coutas,pr.saldo_cuota,pr.tipo\n"
                    + ",pr.fecha_fin_prestamo,pr.abono,pr.pago from prestamos as pr, personal as p "
                    + "where p.idpersonal=pr.personal_idpersonal and\n"
                    + "p.cedula=?";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Prestamo();
                u.setValor(rs.getInt("valor"));
                u.setFecha_(rs.getString("fecha_prestamo"));
                u.setCuotas(rs.getInt("coutas"));
                u.setSaldo(rs.getInt("saldo_cuota"));
                u.setPago(rs.getInt("pago"));
                u.setAbono(rs.getInt("abono"));
                u.setFechaFin(rs.getString("fecha_fin_prestamo"));
                u.setTipo(rs.getString("tipo"));
                lista.add(u);

            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando en el metodo busqueda X activo " + ex);
            } finally {
                return lista;
            }
        }

    }

    public List ReportePrestamosTotal() {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        List<Prestamo> lista = null;
        Prestamo u;
        try {
            sql = "select max(pr.idprestamos),p.nombre,p.cedula,pr.valor,pr.fecha_prestamo,pr.saldo_cuota,pr.tipo\n"
                    + ",pr.fecha_fin_prestamo,pr.pago from prestamos as pr, personal as p \n"
                    + "where p.idpersonal=pr.personal_idpersonal  group by p.idpersonal order by p.nombre ASC;";
            pstm = (PreparedStatement) con.prepareStatement(sql);

            rs = pstm.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                u = new Prestamo();
                u.setCedula1(rs.getString("cedula"));
                u.setNombre1(rs.getString("nombre"));
                u.setValor(rs.getInt("valor"));
                u.setFecha_(rs.getString("fecha_prestamo"));

                u.setSaldo(rs.getInt("saldo_cuota"));
                u.setPago(rs.getInt("pago"));

                u.setFechaFin(rs.getString("fecha_fin_prestamo"));
                u.setTipo(rs.getString("tipo"));
                lista.add(u);

            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando en el metodo busqueda X activo " + ex);
            } finally {
                return lista;
            }
        }

    }

    public int valorDelPrestamo(String cedula) {
        ResultSet rs = null;
        String sql;
        PreparedStatement pstm = null;
        Connection con = connection.conexion();
        int ce = 0;
        try {
            sql = "select max(pr.valor) from personal as p,prestamos as pr where "
                    + "p.idpersonal=pr.personal_idpersonal and   cedula=?  and\n"
                    + "pr.tipo='activo'";
            pstm = (PreparedStatement) con.prepareStatement(sql);
            pstm.setString(1, cedula);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ce = rs.getInt("max(pr.valor)");
                System.out.println("el valor es" + ce);

            }
        } catch (SQLException ex) {
            System.out.println("Listando " + ex);
        } finally {
            try {
                pstm.close();
                rs.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexiones en el metodo  busqueda cedula " + ex);
            } finally {
                return ce;
            }
        }
    }

}
