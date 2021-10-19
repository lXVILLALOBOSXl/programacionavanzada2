package data;

import domain.Auto;
import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * Se encarga de realizar las transacciones CRUD para los objetos auto
 */
public class AutoDAO {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT * FROM Auto";
    private static final String SQL_INSERT = "INSERT INTO Auto (modelo, marca, ano, precio) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Auto SET modelo = ?, marca = ?, ano = ?, precio = ? where idAuto = ?";
    private static final String SQL_DELETE = "DELETE FROM Auto WHERE idAuto = ?";

    public AutoDAO(){

    }

    public AutoDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    /***
     * Se encarga de traer todos los registros de Autos en la BD
     * @return Lista de objetos autos, derivada del select
     * @throws SQLException SI hubo un error al hacer la consulta
     */
    public List<Auto> seleccionar() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Auto auto = null;
        List<Auto> autos = new ArrayList<>();

        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idAuto = resultSet.getInt("idAuto");
                String modelo = resultSet.getString("modelo");
                String marca = resultSet.getString("marca");
                String ano = resultSet.getString("ano");
                Double precio = resultSet.getDouble("precio");
                auto = new Auto(idAuto,modelo,marca,ano,precio);
                autos.add(auto);
            }
        } finally {
            try {
                Conexion.close(resultSet);
                Conexion.close(preparedStatement);
                if(this.conexionTransaccional == null){
                    Conexion.close(connection);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return autos;
    }

    /***
     * Se encarga de insertar un nuevo registro auto a la bd
     * @param auto Objeto que contiene la infromacion del registro a insertar
     * @return NUmero de rows afectadas
     * @throws SQLException SI hubo un error al hacer la consulta
     */
    public int insertar(Auto auto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,auto.getModelo());
            preparedStatement.setString(2,auto.getMarca());
            preparedStatement.setString(3,auto.getAno().toString());
            preparedStatement.setString(4,auto.getPrecio().toString());
            registros = preparedStatement.executeUpdate();
        } finally {
            try {
                Conexion.close(preparedStatement);
                if(this.conexionTransaccional == null){
                    Conexion.close(connection);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return registros;
    }

    /***
     * Se encarga de actualizar un registro auto existente en la bd
     * @param auto Objeto que contiene la infromacion del registro a modificar
     * @return NUmero de rows afectadas
     * @throws SQLException SI hubo un error al hacer la consulta
     */
    public int actualizar(Auto auto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1,auto.getModelo());
            preparedStatement.setString(2,auto.getMarca());
            preparedStatement.setString(3,auto.getAno().toString());
            preparedStatement.setString(4,auto.getPrecio().toString());
            preparedStatement.setString(5,auto.getIdAuto().toString());
            registros = preparedStatement.executeUpdate();
        } finally {
            try {
                Conexion.close(preparedStatement);
                if(this.conexionTransaccional == null){
                    Conexion.close(connection);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return registros;
    }

    /***
     * Se encarga de eliminar un registro auto existente en la bd
     * @param auto Objeto que contiene la infromacion del registro a eliminar
     * @return NUmero de rows afectadas
     * @throws SQLException SI hubo un error al hacer la consulta
     */
    public int eliminar(Auto auto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,Integer.toString(auto.getIdAuto()));
            registros = preparedStatement.executeUpdate();
        } finally {
            try {
                Conexion.close(preparedStatement);
                if(this.conexionTransaccional == null){
                    Conexion.close(connection);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
