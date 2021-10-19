package data;

import domain.Auto;
import domain.Cliente;
import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAO {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT * FROM Cliente";
    private static final String SQL_INSERT = "INSERT INTO Cliente (nombre, apellidoPaterno, apellidoMaterno, telefono, correo) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Cliente SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, telefono = ?, correo = ? where idCliente = ?";
    private static final String SQL_DELETE = "DELETE FROM Cliente WHERE idCliente = ?";

    public ClienteDAO(){

    }

    public ClienteDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Cliente> seleccionar() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idCliente = resultSet.getInt("idCliente");
                String nombre = resultSet.getString("nombre");
                String apellidoPaterno = resultSet.getString("apellidoPaterno");
                String apellidoMaterno = resultSet.getString("apellidoMaterno");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                cliente = new Cliente(idCliente,nombre,apellidoPaterno,apellidoMaterno,telefono,correo);
                clientes.add(cliente);
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
        return clientes;
    }

    public int insertar(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2,cliente.getApellidoPaterno());
            preparedStatement.setString(3,cliente.getApellidoMaterno());
            preparedStatement.setString(4,cliente.getTelefono());
            preparedStatement.setString(5,cliente.getCorreo());
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

    public int actualizar(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2,cliente.getApellidoPaterno());
            preparedStatement.setString(3,cliente.getApellidoMaterno());
            preparedStatement.setString(4,cliente.getTelefono());
            preparedStatement.setString(5,cliente.getCorreo());
            preparedStatement.setString(6,cliente.getIdCliente().toString());
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

    public int eliminar(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,Integer.toString(cliente.getIdCliente()));
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
