package Interfaz;

import data.AutoDAO;
import data.ClienteDAO;
import data.VentaDAO;
import domain.Auto;
import domain.Cliente;
import domain.Venta;
import util.Conexion;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GUI extends JFrame {

    private JButton agregarAuto, modificarAuto, agregarCliente, modificarCliente, agregarVenta, eliminarVenta;
    private JTable tablaAuto, tablaCliente, tablaVenta;
    private JTextField modelo, marca, ano, precio;
    private JTextField nombre, apellidoPaterno, apellidoMaterno, telefono, correo;
    private Connection connection;
    private static final JLabel labelModelo = new JLabel("Modelo*:"), labelMarca = new JLabel("Marca*:"), labelAno = new JLabel("Año*:"), labelPrecio = new JLabel("Precio*:"),
    labelNombre = new JLabel("Nombre*:"), labelApellidoPaterno = new JLabel("Apellido Paterno*:"), labelApellidoMaterno = new JLabel("Apellido Materno*:"),
    labelTelefono = new JLabel("Telefono*:"), labelCorreo = new JLabel("Correo*:"), labelAuto = new JLabel("Auto*:"), labelCliente = new JLabel("Cliente*:");
    private JComboBox comboBoxAutos;
    private JComboBox comboBoxClientes;
    private DefaultTableModel tablaAutoModel = new DefaultTableModel();
    private DefaultTableModel tablaClienteModel = new DefaultTableModel();
    private DefaultTableModel tablaVentaModel = new DefaultTableModel();


    /**
     * Inicializa el layout
     */
    public GUI(){
        super();
        inicializa();
    }

    /***
     * Crea la vista de la interfaz grafica, agrego los eventos a los componentes de la misma y se configuran las
     * propiedades del layout
     */
    protected void inicializa(){
        //Establecer propiedades de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000,5000));
        this.setTitle("18100586");
        this.setLocationRelativeTo(null);

        //Definir elementos del layout
        agregarAuto = new JButton("AGREGAR");
        modificarAuto = new JButton("MODIFICAR");
        agregarCliente = new JButton("AGREGAR");
        modificarCliente = new JButton("MODIFICAR");
        agregarVenta = new JButton("AGREGAR");
        eliminarVenta = new JButton("ELIMINAR");
        tablaAuto = new JTable(){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        tablaCliente = new JTable(){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        tablaVenta = new JTable(){
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        modelo = new JTextField();
        marca = new JTextField();
        ano = new JTextField();
        precio = new JTextField();
        nombre = new JTextField();
        apellidoPaterno = new JTextField();
        apellidoMaterno = new JTextField();
        telefono = new JTextField();
        correo = new JTextField();

        setTables();

        //Acomodo de los elementos del layout
        GroupLayout groupLayout;
        groupLayout = new GroupLayout(this.getContentPane());

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        //Se agregan los evenetos a los componentes del programa
        agregarAuto.addActionListener((ActionEvent) -> {createAuto();});
        modificarAuto.addActionListener((ActionEvent) -> {updateAuto();});
        agregarCliente.addActionListener((ActionEvent) -> {createCliente();});
        modificarCliente.addActionListener((ActionEvent) -> {updateCliente();});
        agregarVenta.addActionListener((ActionEvent) -> {createVenta();});
        eliminarVenta.addActionListener((ActionEvent) -> {deleteVenta();});

        tablaAuto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaAuto.getSelectedRow() > -1) {
                    // print first column value from selected row
                    modelo.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 0).toString());
                    marca.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 1).toString());
                    ano.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 2).toString());
                    precio.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 3).toString());
                }
            }
        });

        tablaCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaCliente.getSelectedRow() > -1) {
                    // print first column value from selected row
                    nombre.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0).toString());
                    apellidoPaterno.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 1).toString());
                    apellidoMaterno.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 2).toString());
                    telefono.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 3).toString());
                    correo.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 4).toString());
                }
            }
        });

        tablaVenta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaVenta.getSelectedRow() > -1) {
                    // print first column value from selected row
                }
            }
        });

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup()
                        .addGroup(
                                groupLayout.createParallelGroup()
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(labelModelo)
                                                        .addComponent(modelo,10,80,233)
                                                        .addComponent(labelMarca)
                                                        .addComponent(marca,10,80,233)
                                                        .addComponent(labelAno)
                                                        .addComponent(ano,10,80,233)
                                                        .addComponent(labelPrecio)
                                                        .addComponent(precio,10,80,233)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(tablaAuto)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(agregarAuto,10,80,233)
                                                        .addComponent(modificarAuto,10,80,233)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(labelNombre)
                                                        .addComponent(nombre,10,80,233)
                                                        .addComponent(labelApellidoPaterno)
                                                        .addComponent(apellidoPaterno,10,80,233)
                                                        .addComponent(labelApellidoMaterno)
                                                        .addComponent(apellidoMaterno,10,80,233)
                                                        .addComponent(labelTelefono)
                                                        .addComponent(telefono,10,80,233)
                                                        .addComponent(labelCorreo)
                                                        .addComponent(correo,10,80,233)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(tablaCliente)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(agregarCliente,10,80,233)
                                                        .addComponent(modificarCliente,10,80,233)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(labelAuto)
                                                        .addComponent(comboBoxAutos,10,80,233)
                                                        .addComponent(labelCliente)
                                                        .addComponent(comboBoxClientes,10,80,233)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(tablaVenta)
                                        )
                                        .addGroup(
                                                groupLayout.createSequentialGroup()
                                                        .addComponent(agregarVenta,10,80,233)
                                                        .addComponent(eliminarVenta,10,80,233)
                                        )
                        )
        );

        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addGroup(
                                groupLayout.createSequentialGroup()
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(labelModelo)
                                                        .addComponent(modelo,25,25,25)
                                                        .addComponent(labelMarca)
                                                        .addComponent(marca,25,25,25)
                                                        .addComponent(labelPrecio)
                                                        .addComponent(precio,25,25,25)
                                                        .addComponent(labelAno)
                                                        .addComponent(ano,25,25,25)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(tablaAuto)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(agregarAuto)
                                                        .addComponent(modificarAuto)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(labelNombre)
                                                        .addComponent(nombre,25,25,25)
                                                        .addComponent(labelApellidoPaterno)
                                                        .addComponent(apellidoPaterno,25,25,25)
                                                        .addComponent(labelApellidoMaterno)
                                                        .addComponent(apellidoMaterno,25,25,25)
                                                        .addComponent(labelTelefono)
                                                        .addComponent(telefono,25,25,25)
                                                        .addComponent(labelCorreo)
                                                        .addComponent(correo,25,25,25)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(tablaCliente)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(agregarCliente)
                                                        .addComponent(modificarCliente)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(labelAuto)
                                                        .addComponent(comboBoxAutos,25,25,25)
                                                        .addComponent(labelCliente)
                                                        .addComponent(comboBoxClientes,25,25,25)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(tablaVenta)
                                        )
                                        .addGroup(
                                                groupLayout.createParallelGroup()
                                                        .addComponent(agregarVenta)
                                                        .addComponent(eliminarVenta)
                                        )
                        )
        );


        setLayout(groupLayout);
        this.pack();

    }

    private void putAuto() {
        System.out.println("auto");
    }

    private void setTables() {
        tablaAutoModel.addColumn("Modelo");
        tablaAutoModel.addColumn("Marca");
        tablaAutoModel.addColumn("Año");
        tablaAutoModel.addColumn("Precio");

        try {
            setAutos();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaAuto.setModel(tablaAutoModel);

        tablaClienteModel.addColumn("nombre");
        tablaClienteModel.addColumn("apellidoPaterno");
        tablaClienteModel.addColumn("apellidoMaterno");
        tablaClienteModel.addColumn("telefono");
        tablaClienteModel.addColumn("correo");

        try {
            setClientes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaCliente.setModel(tablaClienteModel);

        tablaVentaModel.addColumn("modelo");
        tablaVentaModel.addColumn("marca");
        tablaVentaModel.addColumn("ano");
        tablaVentaModel.addColumn("precio");
        tablaVentaModel.addColumn("nombre");

        try {
            setVentas();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tablaVenta.setModel(tablaVentaModel);


    }

    private void deleteVenta() {
    }

    private void createVenta() {
    }

    private void updateCliente() {
    }

    private void createCliente() {
    }

    private void updateAuto() {
    }

    private void createAuto() {
    }

    private void setAutos() throws SQLException {
        connection = Conexion.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        AutoDAO autoDAO = new AutoDAO(connection);
        List<Auto> autos = autoDAO.seleccionar();
        comboBoxAutos = new JComboBox(autos.toArray());
        String [] datosAutos = new String[4];

        for (Auto auto: autos) {
            datosAutos[0] = auto.getModelo();
            datosAutos[1] = auto.getMarca();
            datosAutos[2] = auto.getAno().toString();
            datosAutos[3] = auto.getPrecio().toString();
            tablaAutoModel.addRow(datosAutos);
        }

    }

    private void setClientes() throws SQLException {
        connection = Conexion.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        List<Cliente> clientes = clienteDAO.seleccionar();
        comboBoxClientes = new JComboBox(clientes.toArray());

        String [] datosClientes = new String[5];

        for (Cliente cliente: clientes) {
            datosClientes[0] = cliente.getNombre();
            datosClientes[1] = cliente.getApellidoPaterno();
            datosClientes[2] = cliente.getApellidoMaterno();
            datosClientes[3] = cliente.getTelefono();
            datosClientes[4] = cliente.getCorreo();
            tablaClienteModel.addRow(datosClientes);
        }

    }

    private void setVentas() throws SQLException {
        connection = Conexion.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        VentaDAO ventaDAO = new VentaDAO(connection);
        List<Venta> ventas = ventaDAO.seleccionar();

        String [] datosVentas = new String[5];

        for (Venta venta: ventas) {
            datosVentas[0] = venta.getAuto().getModelo();
            datosVentas[1] = venta.getAuto().getMarca();
            datosVentas[2] = venta.getAuto().getAno().toString();
            datosVentas[3] = venta.getAuto().getPrecio().toString();
            datosVentas[4] = venta.getCliente().getNombre();
            tablaVentaModel.addRow(datosVentas);
        }

    }
}
