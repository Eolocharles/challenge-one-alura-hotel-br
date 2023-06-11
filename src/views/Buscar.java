package views;



import com.toedter.calendar.JDateChooser;
import controllers.GuestController;
import controllers.BookingController;
import model.Booking;
import model.Guest;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private BookingController bookingController;
	private GuestController guestController;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
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
	public Buscar() {
		this.bookingController = new BookingController();
		this.guestController = new GuestController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
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


		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

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
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scrollTable = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scrollTable, null);
		scrollTable.setVisible(true);


		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scrollTableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scrollTableHuespedes, null);
		scrollTableHuespedes.setVisible(true);

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel2);

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

		JPanel btnAtras = new JPanel();
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

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
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
				int selectedIndex = panel.getSelectedIndex();
				if (selectedIndex == 0) {
					if (txtBuscar.getText().equals("")) {
						clearReservationTable();
						fillReservationTable();
					} else {
						clearReservationTable();
						findReservationById();
					}

				} else if (selectedIndex == 1) {
					if (txtBuscar.getText().equals("")) {
						clearGuesTable();
						fillTableGuest();
					} else {
						clearGuesTable();
						findGuestByLastName();
					}
				}
			}
		});

		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
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

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = panel.getSelectedIndex();
				int selectedColumnH = tbHospedes.getSelectedColumn();
				if (selectedIndex == 0) {
					updateReservation();
					clearReservationTable();
					fillReservationTable();

				} else if (selectedIndex == 1) {
					if (selectedColumnH == 1) {
						updateGuestName();
						clearGuesTable();
						fillTableGuest();
					} else if (selectedColumnH == 2) {
						updateGuestLastName();
						clearGuesTable();
						fillTableGuest();
					} else if (selectedColumnH == 3) {
						updateBornDate();
						clearGuesTable();
						fillTableGuest();
					} else if (selectedColumnH == 4) {
						updateGuestNationality();
						clearGuesTable();
						fillTableGuest();
					} else if (selectedColumnH == 5) {
						updateGuestPhone();
						clearGuesTable();
						fillTableGuest();
					}
				}
			}
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = panel.getSelectedIndex();
				if (selectedIndex == 0) {

					deleteReservation();
					clearReservationTable();
					fillReservationTable();

				} else if (selectedIndex == 1) {

					deleteGuest();
					clearGuesTable();
					fillTableGuest();
				}
			}
		});

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}

	private void clearGuesTable() {
		if (modeloHospedes != null) {
			modeloHospedes.setRowCount(0);
		}
	}

	private void clearReservationTable() {
		if (modelo != null) {
			modelo.setRowCount(0);
		}
	}


	private List<Guest> findAllGuest() {
		return this.guestController.findAllGuests();
	}


	private List<Booking> findAllReservation() {
		return this.bookingController.reservationList();
	}

	private void findReservationById() {
		Booking booking = this.bookingController.findReservationById(Integer.parseInt(txtBuscar.getText()));
		modelo.addRow(new Object[]{booking.getId(), booking.getcheckInDate(),
				booking.getCheckOut(), booking.getValor(), booking.getFormaPagamento()});
		
	}

	private void fillTableGuest() {
		List<Guest> guests = findAllGuest();
		try {
			for (Guest guest : guests) {
				modeloHospedes.addRow(new Object[]{guest.getId(), guest.getNome(), guest.getSobrenome(), guest.getDataNascimento(),
						guest.getNacionalidade(), guest.getTelefone(), guest.getIdReserva()});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void fillReservationTable() {
		List<Booking> list = findAllReservation();
		try {
			for (Booking booking : list) {
				modelo.addRow(new Object[]{booking.getId(), booking.getcheckInDate(),
						booking.getCheckOut(), booking.getValor(), booking.getFormaPagamento()});
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void deleteGuest() {
		int row = tbHospedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
		} else {
			long id = (long) tbHospedes.getValueAt(row, 0);
			this.guestController.delete(id);
			JOptionPane.showMessageDialog(null, "Hospede excluido com sucesso");
		}
	}

	private void deleteReservation() {
		int row = tbReservas.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
		} else {
			long id = (long) tbReservas.getValueAt(row, 0);
			this.bookingController.delete(id);
			JOptionPane.showMessageDialog(null, "Reserva excluida com sucesso");
		}
	}

	private void updateGuestName() {
		int row = tbHospedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar");
		} else {
			long id = (long) tbHospedes.getValueAt(row, 0);
			String nome = JOptionPane.showInputDialog("Digite o novo nome");
			this.guestController.updateName(id, nome);
			JOptionPane.showMessageDialog(null, "Nome alterado com sucesso");
		}
	}

	private void updateGuestLastName() {
		int row = tbHospedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar");
		} else {
			long id = (long) tbHospedes.getValueAt(row, 0);
			String sobrenome = JOptionPane.showInputDialog("Digite o novo sobrenome");
			this.guestController.updateLastName(id, sobrenome);
			JOptionPane.showMessageDialog(null, "Sobrenome alterado com sucesso");
		}
	}

	private void updateGuestNationality() {
		int row = tbHospedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar");
		} else {
			long id = (long) tbHospedes.getValueAt(row, 0);
			String nacionalidade = JOptionPane.showInputDialog("Digite a nova nacionalidade");
			this.guestController.updateNationality(id, nacionalidade);
			JOptionPane.showMessageDialog(null, "Nacionalidade alterada com sucesso");
		}
	}

	private void updateBornDate() {
		int row = tbHospedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar");
		} else {
			long id = (long) tbHospedes.getValueAt(row, 0);
			String dataNascimento = JOptionPane.showInputDialog("Digite a nova data de nascimento (aaaa-mm-dd)");
			this.guestController.updateBornDate(id, dataNascimento);
			JOptionPane.showMessageDialog(null, "Data de nascimento alterada com sucesso");
		}
	}

	private void updateGuestPhone() {
		int row = tbHospedes.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar");
		} else {
			long id = (long) tbHospedes.getValueAt(row, 0);
			String telefone = JOptionPane.showInputDialog("Digite o novo telefone (xx)xxxxx-xxxx");
			this.guestController.updatePhone(id, telefone);
			JOptionPane.showMessageDialog(null, "Telefone alterado com sucesso");
		}
	}

	//método que atualiza a reserva e calcula o valor automaticamente a partir da data de checkin e checkout
	private void updateReservation() {
		int row = tbReservas.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para editar");
		} else {
			long id = (long) tbReservas.getValueAt(row, 0);
			String checkIn = JOptionPane.showInputDialog("Digite a nova data de checkin (aaaa-mm-dd)");
			String checkOut = JOptionPane.showInputDialog("Digite a nova data de checkout (aaaa-mm-dd)");
			Long valor = calculateValue(checkIn, checkOut);
			String payment = JOptionPane.showInputDialog("Digite a forma de pagamento");
			this.bookingController.updateReservation(id, checkIn, checkOut, valor, payment);
			JOptionPane.showMessageDialog(null, "Reserva alterada com sucesso");
		}
	}

	private Long calculateValue(String checkIn, String checkOut) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate checkInDate = LocalDate.parse(checkIn, formatter);
		LocalDate checkOutDate = LocalDate.parse(checkOut, formatter);
		long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		return days * 100;
	}

	private void findGuestByLastName() {
		String lastName = txtBuscar.getText();
		List<Guest> guests = this.guestController.findBySobrenome(lastName);
		DefaultTableModel model = (DefaultTableModel) tbHospedes.getModel();
		model.setRowCount(0);
		try {
			for (Guest guest : guests) {
				model.addRow(new Object[] { guest.getId(), guest.getNome(), guest.getSobrenome(), guest.getNacionalidade(),
						guest.getDataNascimento(), guest.getTelefone(), guest.getIdReserva() });
			}
		} catch (Exception e) {
			throw e;
		}
	}


	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
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
