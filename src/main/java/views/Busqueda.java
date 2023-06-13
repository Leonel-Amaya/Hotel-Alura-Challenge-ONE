package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import dao.HuespedDao;
import dao.ReservaDao;
import modelo.Huesped;
import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		llenarDatosReservas();
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		llenarDatosHuespedes();
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		final JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		final JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String consulta = txtBuscar.getText().trim();
				
					EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebahotel");
					EntityManager em = factory.createEntityManager();
					
					HuespedDao huespedDao = new HuespedDao(em);
					List<Huesped> listaHuesped = huespedDao.buscandoHuesped(consulta); 
					limpiarTabla(modelo);
					limpiarTabla(modeloHuesped);
					
					List<Reserva> listaReserva = new ArrayList<>();
					for(Huesped huesped : listaHuesped) {
						listaReserva.add(huesped.getReserva());
					}
					
					System.out.println(listaReserva);
					
					fillTableReserva(listaReserva, modelo);
					fillTableHuesped(listaHuesped, modeloHuesped);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar(panel);
				limpiarTabla(modelo);
				llenarDatosReservas();
			}
		});
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar(panel);
				limpiarTabla(modelo);
				limpiarTabla(modeloHuesped);
				llenarDatosReservas();
				llenarDatosHuespedes();
			}
		});
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	//Método que añade los datos de la DB a la tabla y los muestra
	private void llenarDatosReservas() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebahotel");
		EntityManager em = factory.createEntityManager();
		ReservaDao reservaDao = new ReservaDao(em);
		
		//
		DefaultTableModel modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.setRowCount(0); //Limpia la tabla antes de llenarla nuevamente
		
		//Obtener todas las reservas de la base de datos
		List<Reserva> reservas = reservaDao.listarDatos();
		
		//Formateando la fecha
		SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd-MM-yyyy");
		
		//Llenar el JTable con los datos de las reservas
		for(Reserva reserva : reservas) {
			Object[] fila = {
					reserva.getId(),
					fechaFormateada.format(reserva.getFecha_ingreso()),
					fechaFormateada.format(reserva.getFecha_salida()),
					reserva.getValor(),
					reserva.getFormaPago()
			};
			modelo.addRow(fila);
		}
	}
	
	private void llenarDatosHuespedes() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebahotel");
		EntityManager em = factory.createEntityManager();
		HuespedDao huespedDao = new HuespedDao(em);
		
		DefaultTableModel modelo = (DefaultTableModel) tbHuespedes.getModel();
		modelo.setRowCount(0);
		
		List<Huesped> huespedes = huespedDao.listarHuespedes();
		
		SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd-MM-yyyy");
		
		for(Huesped huesped : huespedes) {
			Object[] fila = {
					huesped.getId(),
					huesped.getNombre(),
					huesped.getApellido(),
					fechaFormateada.format(huesped.getBirthday()),
					huesped.getNacionalidad(),
					huesped.getTelefono(),
					huesped.getReserva().getId()
			};
			modelo.addRow(fila);
		}
	}
	
	private boolean tieneFilaElegida(int panel) {
		if (panel == 0) {
			return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
		} else {
			return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
		}
	}
	
	//Permite eliminar el registro
	private void eliminar(JTabbedPane panel) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebahotel");
		EntityManager em = factory.createEntityManager();
		HuespedController huespedController = new HuespedController(em);
		ReservaController reservaController = new ReservaController(em);
		
		if(tieneFilaElegida(panel.getSelectedIndex())) {
			JOptionPane.showMessageDialog(this, "Selecciona una fila");
			return;
		}
		if(panel.getSelectedIndex() == 0) {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Long id = Long.parseLong(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
				System.out.println("Fila seleccionada " + id);
				//
				em.getTransaction().begin();
				
				reservaController.eliminarReserva(id);
				
				em.getTransaction().commit();
				//
				modelo.removeRow(tbReservas.getSelectedRow());
				
				JOptionPane.showMessageDialog(this,
						String.format("El item con id %d eliminado con éxito!", id));
			}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		} else {
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				Long id = Long.parseLong(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				System.out.println("Id seleccionado de Huespedes " + id);
				//
				em.getTransaction().begin();
				
				huespedController.eliminarRegistro(id);
				
				em.getTransaction().commit();
				//
				modelo.removeRow(tbHuespedes.getSelectedRow());

				JOptionPane.showMessageDialog(this,
						String.format("El item eliminado con éxito!"));
			}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}
	
	//Permite editar los datos de un registro
	private void editar(JTabbedPane panel) {
		if(tieneFilaElegida(panel.getSelectedIndex())) {
			JOptionPane.showMessageDialog(this, "Selecciona una fila");
			return;
		}
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("pruebahotel");
		EntityManager em = factory.createEntityManager();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		if(panel.getSelectedIndex() == 0) {
			Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				
				Reserva reserva = new Reserva();
				ReservaDao reservaDao = new ReservaDao(em);
				
				try {
					Long id = Long.parseLong(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
					
					Date dateCheckIn = format.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
					
					Date dateCheckOut = format.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
					
					int valor = (int) reserva.diasHospedaje(dateCheckIn, dateCheckOut);
					
					System.out.println(id);
					System.out.println(dateCheckIn);
					System.out.println(dateCheckOut);
					System.out.println(valor);
					
					
					
					em.getTransaction().begin();
					
					reservaDao.actualizarReserva(id, valor, dateCheckIn, dateCheckOut);
					
					em.getTransaction().commit();
					
					JOptionPane.showMessageDialog(this,
							String.format("Item con %d editado con éxito!", id));
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		} else {
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
			.ifPresentOrElse(fila -> {
				
				HuespedDao huespedDao = new HuespedDao(em);
				
				try {
					
					Long id = Long.parseLong(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
					
					String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
					
					String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
					
					Date fechaNacimiento = format.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
					
					String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
					
					System.out.println(id);
					System.out.println(nombre);
					System.out.println(apellido);
					System.out.println(fechaNacimiento);
					System.out.println(telefono);
					
					//
					em.getTransaction().begin();
					
					huespedDao.actualizarHuesped(id, nombre, apellido, fechaNacimiento, telefono);
					
					em.getTransaction().commit();
					//
					
					JOptionPane.showMessageDialog(this,
						String.format("Item con %d editado con éxito!", id));
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				
			}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		}
	}
	
	private void limpiarTabla(DefaultTableModel modelo) {
		modelo.getDataVector().clear();
	}
	
	private void fillTableHuesped(List<Huesped> data, DefaultTableModel model) {
		data.forEach(h -> model.addRow(new Object[] {
				h.getId(),
				h.getNombre(),
				h.getApellido(),
				h.getBirthday(),
				h.getNacionalidad(),
				h.getTelefono(),
				h.getReserva().getId()
		}));
	}
	
	private void fillTableReserva(List<Reserva> data, DefaultTableModel model) {
		data.forEach(r -> modelo.addRow(new Object[] {
				r.getId(),
				r.getFecha_ingreso(),
				r.getFecha_salida(),
				r.getValor(),
				r.getFormaPago()
		}));
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
