package data;

import domain.Auto;
import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoDAO {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT * FROM Auto";
    private static final String SQL_INSERT = "INSERT INTO Auto (modelo, marca, ano) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Auto SET modelo = ?, marca = ?, ano = ? where idAuto = ?";
    private static final String SQL_DELETE = "DELETE FROM Auto WHERE idAuto = ?";

    public AutoDAO(){

    }

    public AutoDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

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
                Date ano = resultSet.getDate("ano");
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
            preparedStatement.setString(4,auto.getIdAuto().toString());
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
