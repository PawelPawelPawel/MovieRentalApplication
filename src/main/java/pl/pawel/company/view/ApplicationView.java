package pl.pawel.company.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pl.pawel.company.dao.CustomerDAO;
import pl.pawel.company.dao.MovieDAO;
import pl.pawel.company.dao.OrderDAO;
import pl.pawel.company.model.Customer;
import pl.pawel.company.model.Movie;
import pl.pawel.company.model.PurchaseOrder;
import pl.pawel.company.service.OrderSericeImpl;
import pl.pawel.company.service.OrderService;

public class ApplicationView extends JFrame implements ActionListener {
	private static final long serialVersionUID = -5985086731899474964L;
	private JLabel JTitle;
	private JButton JAdd, JAccept;
	private JTextField textTitle, textRuntime, textCopies;
	private ButtonGroup bg;
	private JRadioButton radioButtonMovies, radioButtonCustomers, radioButtonOrders;

	private JComboBox<Customer> customers;
	private JComboBox<Movie> movies;
	private JComboBox<Integer> copies;

	private JPanel jPanel;
	private JPanel jPanel2;
	private JPanel jPanel3;

	private JTable table;
	private JTable table2;
	private JTable table3;

	private MovieDAO movieDAO = new MovieDAO();
	private CustomerDAO customerDAO = new CustomerDAO();
	private OrderDAO orderDAO = new OrderDAO();

	private OrderService orderService = new OrderSericeImpl();

	public ApplicationView(JPanel jPanel) {
		super("Movie rental");

		// this.jPanel = jPanel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(800, 600);
		setVisible(true);

		// RadioButtons
		bg = new ButtonGroup();
		radioButtonMovies = new JRadioButton("Movies", true);
		radioButtonMovies.setBounds(120, 60, 100, 20);
		bg.add(radioButtonMovies);
		add(radioButtonMovies);
		radioButtonMovies.addActionListener(this);

		radioButtonCustomers = new JRadioButton("Customers", false);
		radioButtonCustomers.setBounds(310, 60, 100, 20);
		bg.add(radioButtonCustomers);
		add(radioButtonCustomers);
		radioButtonCustomers.addActionListener(this);

		radioButtonOrders = new JRadioButton("Orders", false);
		radioButtonOrders.setBounds(510, 60, 100, 20);
		bg.add(radioButtonOrders);
		add(radioButtonOrders);
		radioButtonOrders.addActionListener(this);

		// Title Label
		JTitle = new JLabel("Business application");
		JTitle.setBounds(600, 20, 200, 20);
		JTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
		add(JTitle);

		// Buttons
		JAdd = new JButton("Add new movie");
		JAdd.setBounds(900, 100, 150, 20);
		JAdd.addActionListener(this);
		JAdd.setVisible(true);
		add(JAdd);

		JAccept = new JButton("Accept");
		JAccept.setBounds(920, 200, 100, 20);
		JAccept.addActionListener(this);
		JAccept.setVisible(false);
		add(JAccept);

		// textfield
		textTitle = new JTextField();
		textTitle.setBounds(800, 150, 100, 20);
		add(textTitle);
		textTitle.setVisible(false);

		textRuntime = new JTextField();
		textRuntime.setBounds(900, 150, 100, 20);
		add(textRuntime);
		textRuntime.setVisible(false);

		textCopies = new JTextField();
		textCopies.setBounds(1000, 150, 100, 20);
		add(textCopies);
		textCopies.setVisible(false);

	}

	public JPanel createMoviePanel() {
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[4];
		columnsName[0] = "Id";
		columnsName[1] = "tittle";
		columnsName[2] = "runetime";
		columnsName[3] = "copies";

		model.setColumnIdentifiers(columnsName);

		Collection<Movie> movies = movieDAO.getMovies();

		Object[] rowData = new Object[4];
		for (Movie movie : movies) {
			rowData[0] = movie.getId();
			rowData[1] = movie.getTittle();
			rowData[2] = movie.getRunetime();
			rowData[3] = movie.getCopies();
			model.addRow(rowData);
		}
		table.setModel(model);
		jPanel = new JPanel();
		JScrollPane pane = new JScrollPane(table);
		jPanel.add(pane);
		jPanel.setBounds(100, 100, 500, 500);
		return jPanel;
	}

	public JPanel createCustomerPanel() {

		table2 = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[4];
		columnsName[0] = "Id";
		columnsName[1] = "name";
		columnsName[2] = "lastName";
		columnsName[3] = "phone";

		model.setColumnIdentifiers(columnsName);

		Collection<Customer> customers = customerDAO.getCustomers();

		Object[] rowData = new Object[4];
		for (Customer customer : customers) {
			rowData[0] = customer.getId();
			rowData[1] = customer.getName();
			rowData[2] = customer.getLastName();
			rowData[3] = customer.getPhone();
			model.addRow(rowData);
		}
		table2.setModel(model);
		JScrollPane pane = new JScrollPane(table2);
		jPanel2 = new JPanel();
		jPanel2.add(pane);
		jPanel2.setBounds(100, 100, 500, 500);
		return jPanel2;
	}

	public JPanel createOrderPanel() {

		table3 = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		Object[] columnsName = new Object[6];
		columnsName[0] = "order_id";
		columnsName[1] = "customer_id";
		columnsName[2] = "name";
		columnsName[3] = "lastName";
		columnsName[4] = "movie_id";
		columnsName[5] = "tittle";

		model.setColumnIdentifiers(columnsName);

		Collection<PurchaseOrder> purchaseOrder = orderDAO.getOrders();

		Object[] rowData = new Object[6];
		for (PurchaseOrder order : purchaseOrder) {
			rowData[0] = order.getId();
			rowData[1] = order.getCustomer().getId();
			rowData[2] = order.getCustomer().getName();
			rowData[3] = order.getCustomer().getLastName();
			rowData[4] = order.getMovie().getId();
			rowData[5] = order.getMovie().getTittle();
			model.addRow(rowData);
		}
		table3.setModel(model);

		jPanel3 = new JPanel();
		JScrollPane pane = new JScrollPane(table3);
		jPanel3.add(pane);
		jPanel3.setBounds(100, 100, 500, 500);

		return jPanel3;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == radioButtonMovies) {
			if (customers != null)
				customers.setVisible(false);
			if (movies != null)
				movies.setVisible(false);
			if (copies != null)
				copies.setVisible(false);
			jPanel = createMoviePanel();
			add(jPanel);

			jPanel.setVisible(true);
			if (jPanel2 != null)
				jPanel2.setVisible(false);
			if (jPanel3 != null)
				jPanel3.setVisible(false);

			JAdd.setText("add movie");

			textTitle.setVisible(false);
			textRuntime.setVisible(false);
			textCopies.setVisible(false);

			JAccept.setVisible(false);

		}
		if (source == radioButtonCustomers) {
			if (customers != null)
				customers.setVisible(false);
			if (movies != null)
				movies.setVisible(false);
			if (copies != null)
				copies.setVisible(false);

			jPanel2 = createCustomerPanel();
			add(jPanel2);

			if (jPanel != null)
				jPanel.setVisible(false);
			jPanel2.setVisible(true);
			if (jPanel3 != null)
				jPanel3.setVisible(false);

			JAdd.setText("add customer");

			textTitle.setVisible(false);
			textRuntime.setVisible(false);
			textCopies.setVisible(false);

			JAccept.setVisible(false);

		}
		if (source == radioButtonOrders) {

			jPanel3 = createOrderPanel();
			add(jPanel3);

			if (jPanel != null)
				jPanel.setVisible(false);
			if (jPanel2 != null)
				jPanel2.setVisible(false);
			jPanel3.setVisible(true);

			JAdd.setText("add order");

			textTitle.setVisible(false);
			textRuntime.setVisible(false);
			textCopies.setVisible(false);

			JAccept.setVisible(false);

			customers = new JComboBox<Customer>();
			customers.setBounds(900, 250, 300, 20);
			add(customers);
			for (Customer customer : customerDAO.getCustomers()) {
				customers.addItem(customer);
			}

			movies = new JComboBox<Movie>();
			movies.setBounds(900, 450, 300, 20);
			add(movies);

			Collection<Movie> moviesCollection = movieDAO.getMovies();
			for (Movie movie : moviesCollection) {
				movies.addItem(movie);
			}

			copies = new JComboBox<Integer>();
			copies.setBounds(900, 550, 300, 20);
			add(copies);

			if (!moviesCollection.isEmpty()) {
				for (int i = 1; i <= moviesCollection.iterator().next().getCopies(); i++) {
					copies.addItem(i);
				}
			}
			movies.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					copies.removeAllItems();
					Movie movie = (Movie) movies.getSelectedItem();
					if (movie != null) {
						for (int i = 1; i <= movie.getCopies(); i++) {
							copies.addItem(i);
						}
					}
				}
			});

			movies.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					copies.removeAllItems();
					Movie movie = (Movie) movies.getSelectedItem();
					if (movie != null) {
						for (int i = 1; i <= movie.getCopies(); i++) {
							copies.addItem(i);
						}
					}
				}
			});

		}
		if (radioButtonMovies.isSelected() && source == JAdd) {
			textTitle.setVisible(true);
			textRuntime.setVisible(true);
			textCopies.setVisible(true);

			textTitle.setText("title");
			textRuntime.setText("runtime");
			textCopies.setText("copies");

			JAccept.setVisible(true);
		}

		if (radioButtonCustomers.isSelected() && source == JAdd) {
			textTitle.setVisible(true);
			textRuntime.setVisible(true);
			textCopies.setVisible(true);

			textTitle.setText("name");
			textRuntime.setText("lastName");
			textCopies.setText("phone");

			JAccept.setVisible(true);
		}

		if (radioButtonOrders.isSelected() && source == JAdd) {
			textTitle.setVisible(false);
			textRuntime.setVisible(false);
			textCopies.setVisible(false);

			JAccept.setVisible(true);
		}

		if (radioButtonMovies.isSelected() && source == JAccept) {
			try {
				movieDAO.addMovie(textTitle.getText(), Integer.parseInt(textRuntime.getText()),
						Integer.parseInt(textCopies.getText()));

				JOptionPane.showMessageDialog(this, " You added a movie", "Done :)", JOptionPane.INFORMATION_MESSAGE);

				Movie newMovie = movieDAO.getLastMovie();
				Object[] rowData = new Object[4];
				rowData[0] = newMovie.getId();
				rowData[1] = newMovie.getTittle();
				rowData[2] = newMovie.getRunetime();
				rowData[3] = newMovie.getCopies();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(rowData);
				table.setModel(model);
				JScrollPane pane = new JScrollPane(table);
				jPanel.add(pane);
				jPanel.revalidate();
				jPanel.repaint();

			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this, "Runtime and copies must be integer", "ERROR!!!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (radioButtonCustomers.isSelected() && source == JAccept) {
			try {
				customerDAO.addCustomer(textTitle.getText(), textRuntime.getText(),
						Integer.parseInt(textCopies.getText()));
				JOptionPane.showMessageDialog(this, " You added a customer", "Done :)",
						JOptionPane.INFORMATION_MESSAGE);

				Customer newCustomer = customerDAO.getLastCustomers();
				Object[] rowData = new Object[4];
				rowData[0] = newCustomer.getId();
				rowData[1] = newCustomer.getName();
				rowData[2] = newCustomer.getLastName();
				rowData[3] = newCustomer.getPhone();

				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				model.addRow(rowData);
				table2.setModel(model);
				JScrollPane pane = new JScrollPane(table2);
				jPanel2.add(pane);
				jPanel2.revalidate();
				jPanel2.repaint();

			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(this, "Phone must be integer", "ERROR!!!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (radioButtonOrders.isSelected() && source == JAccept) {
			try {
				Movie movie = (Movie) movies.getSelectedItem();
				Customer customer = (Customer) customers.getSelectedItem();
				Integer numberOfCopies = (Integer) copies.getSelectedItem();
				orderService.hireMovie(customer.getId(), movie.getId(), numberOfCopies);
				movies.removeAllItems();
				for (Movie refreshedMovie : movieDAO.getMovies()) {
					movies.addItem(refreshedMovie);
				}

				Object[] rowData = new Object[6];
				PurchaseOrder order = orderDAO.getLastOrder();
				rowData[0] = order.getId();
				rowData[1] = order.getCustomer().getId();
				rowData[2] = order.getCustomer().getName();
				rowData[3] = order.getCustomer().getLastName();
				rowData[4] = order.getMovie().getId();
				rowData[5] = order.getMovie().getTittle();

				DefaultTableModel model = (DefaultTableModel) table3.getModel();
				model.addRow(rowData);
				table3.setModel(model);
				JScrollPane pane = new JScrollPane(table3);
				jPanel3.add(pane);
				jPanel3.revalidate();
				jPanel3.repaint();

				JOptionPane.showMessageDialog(this, " You ordered the movie", "Done :)",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception n) {
				JOptionPane.showMessageDialog(this, "You have to contact with administrator", "ERROR!!!",
						JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}
