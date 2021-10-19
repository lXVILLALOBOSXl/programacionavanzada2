package data;

import domain.Auto;
import domain.Cliente;
import domain.Venta;
import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT = "SELECT Venta.idVenta, Auto.modelo, Auto.marca, Auto.ano, Auto.precio, Cliente.nombre FROM Venta INNER JOIN Auto on Venta.idAuto = Auto.idAuto INNER JOIN Cliente ON Venta.idCliente = Cliente.idCliente;";
    private static final String SQL_INSERT = "INSERT INTO Venta (idAuto, idCLiente) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE Venta SET idAuto = ?, idCliente = ? where idVenta = ?";
    private static final String SQL_DELETE = "DELETE FROM Venta WHERE idVenta = ?";

    public VentaDAO(){

    }

    public VentaDAO(Connection conexionTransaccional){
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Venta> seleccionar() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Venta venta = null;
        Auto auto = null;
        Cliente cliente = null;
        List<Venta> ventas = new ArrayList<>();

        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                auto = new Auto();
                cliente = new Cliente();
                auto.setAno(resultSet.getDate("ano").toLocalDate());
                auto.setMarca(resultSet.getString("marca"));
                auto.setPrecio(resultSet.getDouble("precio"));
                auto.setModelo(resultSet.getString("modelo"));
                cliente.setNombre(resultSet.getString("nombre"));
                venta = new Venta(resultSet.getInt("idVenta"),auto,cliente);
                ventas.add(venta);
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
        return ventas;
    }

    public int insertar(Venta venta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,venta.getAuto().getIdAuto().toString());
            preparedStatement.setString(2,venta.getCliente().getIdCliente().toString());
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

    public int actualizar(Venta venta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1,venta.getAuto().getIdAuto().toString());
            preparedStatement.setString(2,venta.getCliente().getIdCliente().toString());
            preparedStatement.setString(3,venta.getIdVenta().toString());
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

    public int eliminar(Venta venta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int registros = 0;
        try {
            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setString(1,venta.getIdVenta().toString());
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
