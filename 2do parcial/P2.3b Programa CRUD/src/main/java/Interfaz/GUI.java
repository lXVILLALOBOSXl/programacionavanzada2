package Interfaz;

import data.AutoDAO;
import data.ClienteDAO;
import data.VentaDAO;
import domain.Auto;
import domain.Cliente;
import domain.Venta;
import error.DataError;
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
import java.time.LocalDate;
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
    //Guardan el identificador del elemento seleccionado dependiendo la tabla
    private Integer ventaSeleccionada, autoSeleccionado, clienteSeleccionado;


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
        comboBoxClientes = new JComboBox();
        comboBoxAutos = new JComboBox();

        //Crea las tablas
        setTables();

        //Acomodo de los elementos del layout
        GroupLayout groupLayout;
        groupLayout = new GroupLayout(this.getContentPane());

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        //Se agregan los evenetos a los componentes del programa
        agregarAuto.addActionListener((ActionEvent) -> {
            try {
                createAuto();
            } catch (SQLException | DataError throwables) {
                throwables.printStackTrace();
            }
        });
        modificarAuto.addActionListener((ActionEvent) -> {
            try {
                updateAuto();
            } catch (SQLException | DataError throwables) {
                throwables.printStackTrace();
            }
        });
        agregarCliente.addActionListener((ActionEvent) -> {
            try {
                createCliente();
            } catch (SQLException | DataError throwables) {
                throwables.printStackTrace();
            }
        });
        modificarCliente.addActionListener((ActionEvent) -> {
            try {
                updateCliente();
            } catch (SQLException | DataError throwables) {
                throwables.printStackTrace();
            }
        });
        agregarVenta.addActionListener((ActionEvent) -> {
            try {
                createVenta();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        eliminarVenta.addActionListener((ActionEvent) -> {
            try {
                deleteVenta();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        tablaAuto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaAuto.getSelectedRow() > -1) {
                    // Hay una fila seleccionada en la tabla
                    // Almacenamos el identificador del elemento al que se hizo click
                    autoSeleccionado = Integer.parseInt(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 0).toString());
                    // Ponemos la informacion del elemnto seleccionado en las textbox correpondientes
                    modelo.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 1).toString());
                    marca.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 2).toString());
                    ano.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 3).toString());
                    precio.setText(tablaAuto.getValueAt(tablaAuto.getSelectedRow(), 4).toString());
                }
            }
        });

        tablaCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaCliente.getSelectedRow() > -1) {
                    // Hay una fila seleccionada en la tabla
                    // Almacenamos el identificador del elemento al que se hizo click
                    clienteSeleccionado = Integer.parseInt(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0).toString());
                    // Ponemos la informacion del elemnto seleccionado en las textbox correpondientes
                    nombre.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 1).toString());
                    apellidoPaterno.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 2).toString());
                    apellidoMaterno.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 3).toString());
                    telefono.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 4).toString());
                    correo.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 5).toString());
                }
            }
        });

        tablaVenta.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tablaVenta.getSelectedRow() > -1) {
                    // Hay una fila seleccionada en la tabla
                    // Almacenamos el identificador del elemento al que se hizo click
                    ventaSeleccionada = Integer.parseInt(tablaVenta.getValueAt(tablaVenta.getSelectedRow(), 0).toString());
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

    /***
     * Configura, carga y pide la informacion para las tablas
     */
    private void setTables() {
        tablaAutoModel.addColumn("id");
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

        tablaClienteModel.addColumn("id");
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

        tablaVentaModel.addColumn("id");
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

    /***
     * Elimina la venta seleccionada de la bd
     * @throws SQLException si hubo algun error con la bd
     */
    private void deleteVenta() throws SQLException {
        if(ventaSeleccionada == null){
            return;
        }
        connection = Conexion.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        VentaDAO ventaDAO = new VentaDAO(connection);
        Venta venta = new Venta(ventaSeleccionada);
        ventaDAO.eliminar(venta);
        connection.commit();
        tablaVentaModel.setRowCount(0);
        setVentas();
    }

    /***
     * Registra en la bd una venta a partir de la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     */
    private void createVenta() throws SQLException {
        if(comboBoxAutos.getSelectedItem().equals(null) || comboBoxClientes.getSelectedItem().equals(null) ) {
            return;
        }
        connection = Conexion.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        VentaDAO ventaDAO = new VentaDAO(connection);
        Venta venta = new Venta();
        venta.setAuto(new Auto(comboBoxAutos.getSelectedIndex()+1));
        venta.setCliente(new Cliente(comboBoxClientes.getSelectedIndex()+1));
        ventaDAO.insertar(venta);
        connection.commit();
        tablaVentaModel.setRowCount(0);
        setVentas();
    }

    /***
     * Actualiza un registro en la bd con la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     * @throws DataError SI hay un error de ingreso
     */
    private void updateCliente() throws SQLException, DataError {
        if(nombre.getText().isEmpty() || apellidoPaterno.getText().isEmpty() || apellidoMaterno.getText().isEmpty() || telefono.getText().isEmpty() || correo.getText().isEmpty()) {
            return;
        }
        connection = Conexion.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteSeleccionado);
        cliente.setNombre(nombre.getText());
        cliente.setApellidoPaterno(apellidoPaterno.getText());
        cliente.setApellidoMaterno(apellidoMaterno.getText());
        cliente.setTelefono(telefono.getText());
        cliente.setCorreo(correo.getText());
        if(!isValid(telefono.getText(),2)){
            return;
        }
        clienteDAO.actualizar(cliente);
        connection.commit();
        tablaClienteModel.setRowCount(0);
        setClientes();
    }

    /***
     * Registra un cliente en la bd con la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     * @throws DataError SI hay un error de ingreso
     */
    private void createCliente() throws SQLException, DataError {
        if(nombre.getText().isEmpty() || apellidoPaterno.getText().isEmpty() || apellidoMaterno.getText().isEmpty() || telefono.getText().isEmpty() || correo.getText().isEmpty()) {
            return;
        }
        connection = Conexion.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre.getText());
        cliente.setApellidoPaterno(apellidoPaterno.getText());
        cliente.setApellidoMaterno(apellidoMaterno.getText());
        cliente.setTelefono(telefono.getText());
        cliente.setCorreo(correo.getText());
        if(!isValid(telefono.getText(),2)){
                return;
        }
        clienteDAO.insertar(cliente);
        connection.commit();
        tablaClienteModel.setRowCount(0);
        setClientes();

    }

    /***
     * Actualiza un registro en la bd con la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     * @throws DataError SI hay un error de ingreso
     */
    private void updateAuto() throws SQLException, DataError {
        if(modelo.getText().isEmpty() || marca.getText().isEmpty() || ano.getText().isEmpty() || precio.getText().isEmpty()) {
            return;
        }
        connection = Conexion.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        AutoDAO autoDAO = new AutoDAO(connection);
        Auto auto = new Auto();
        auto.setIdAuto(autoSeleccionado);
        auto.setModelo(modelo.getText());
        auto.setMarca(marca.getText());
        auto.setAno(ano.getText());
        if(!isValid(ano.getText(),1)||!isValid(precio.getText(),0)){
            return;
        }
        auto.setPrecio(Double.parseDouble(precio.getText()));
        autoDAO.actualizar(auto);
        connection.commit();
        tablaAutoModel.setRowCount(0);
        setAutos();
    }

    /***
     * Registra un auto en la bd con la informacion ingresada
     * @throws SQLException si hubo algun error con la bd
     * @throws DataError SI hay un error de ingreso
     */
    private void createAuto() throws SQLException, DataError {
        if(modelo.getText().isEmpty() || marca.getText().isEmpty() || ano.getText().isEmpty() || precio.getText().isEmpty()) {
            return;
        }
        connection = Conexion.getConnection();
        if (connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }
        AutoDAO autoDAO = new AutoDAO(connection);
        Auto auto = new Auto();
        auto.setModelo(modelo.getText());
        auto.setMarca(marca.getText());
        auto.setAno(ano.getText());
        if(!isValid(ano.getText(),1)||!isValid(precio.getText(),0)){
            return;
        }
        auto.setPrecio(Double.parseDouble(precio.getText()));
        autoDAO.insertar(auto);
        connection.commit();
        tablaAutoModel.setRowCount(0);
        setAutos();
    }

    /***
     * Trae los registros de la BD y los muestra en tabla
     * @throws SQLException si hubo algun error con la bd
     */
    private void setAutos() throws SQLException {
        connection = Conexion.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        AutoDAO autoDAO = new AutoDAO(connection);
        List<Auto> autos = autoDAO.seleccionar();
        comboBoxAutos.removeAllItems();
        for (Object o : autos) {
            comboBoxAutos.addItem(o);
        }
        String [] datosAutos = new String[5];

        for (Auto auto: autos) {
            datosAutos[0] = auto.getIdAuto().toString();
            datosAutos[1] = auto.getModelo();
            datosAutos[2] = auto.getMarca();
            datosAutos[3] = auto.getAno();
            datosAutos[4] = auto.getPrecio().toString();
            tablaAutoModel.addRow(datosAutos);
        }

    }

    /***
     * Trae los registros de la BD y los muestra en tabla
     * @throws SQLException si hubo algun error con la bd
     */
    private void setClientes() throws SQLException {
        connection = Conexion.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        List<Cliente> clientes = clienteDAO.seleccionar();
        comboBoxClientes.removeAllItems();
        for (Object o : clientes) {
            comboBoxClientes.addItem(o);
        }

        String [] datosClientes = new String[6];

        for (Cliente cliente: clientes) {
            datosClientes[0] = cliente.getIdCliente().toString();
            datosClientes[1] = cliente.getNombre();
            datosClientes[2] = cliente.getApellidoPaterno();
            datosClientes[3] = cliente.getApellidoMaterno();
            datosClientes[4] = cliente.getTelefono();
            datosClientes[5] = cliente.getCorreo();
            tablaClienteModel.addRow(datosClientes);
        }

    }

    /***
     * Trae los registros de la BD y los muestra en tabla
     * @throws SQLException si hubo algun error con la bd
     */
    private void setVentas() throws SQLException {
        connection = Conexion.getConnection();
        if(connection.getAutoCommit()){
            connection.setAutoCommit(false);
        }
        VentaDAO ventaDAO = new VentaDAO(connection);
        List<Venta> ventas = ventaDAO.seleccionar();

        String [] datosVentas = new String[6];

        for (Venta venta: ventas) {
            datosVentas[0] = venta.getIdVenta().toString();
            datosVentas[1] = venta.getAuto().getModelo();
            datosVentas[2] = venta.getAuto().getMarca();
            datosVentas[3] = venta.getAuto().getAno().toString();
            datosVentas[4] = venta.getAuto().getPrecio().toString();
            datosVentas[5] = venta.getCliente().getNombre();
            tablaVentaModel.addRow(datosVentas);
        }

    }

    /***
     * Se encarga de revisar si los datos ingresados son correctos
     * @param info Texto ingresado
     * @param typeInt Bandera para saber si es el año del que se esta hablando
     * @return True - si no hay error, False - si hay error
     * @throws DataError SI hubo un error con la informacion ingresada
     */
    private boolean isValid(String info, int typeInt) throws DataError {
        try {
            int numero = Integer.parseInt(info);
            if((numero * -1) >= 0){
                throw new DataError("Numero Negativo");
            }
        }catch (Exception e){
            return false;
        }

        if(typeInt == 1){
            if(info.length() != 4){
                throw new DataError("Año incorrecto");
            }
        }

        return true;
    }
}
