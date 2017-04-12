package pl.pawel.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import pl.pawel.company.model.Customer;

public class CustomerDAO {

	private final Connection conn;
	private final Statement stat;

	public CustomerDAO() {
		DAOConector connector = new DAOConector();
		conn = connector.getConn();
		stat = connector.getStat();
	}

	public Collection<Customer> getCustomers() {
		Collection<Customer> customers = new LinkedList<Customer>();
		try {

			ResultSet result = stat.executeQuery("SELECT * FROM " + Customer.class.getSimpleName());
			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setName(result.getString("name"));
				customer.setLastName(result.getString("lastName"));
				customer.setPhone(result.getInt("phone"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return customers;
	}

	public Customer getLastCustomers() {
		try {
			ResultSet result = stat
					.executeQuery("SELECT * FROM " + Customer.class.getSimpleName() + " c ORDER BY c.id DESC");
			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setName(result.getString("name"));
				customer.setLastName(result.getString("lastName"));
				customer.setPhone(result.getInt("phone"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void addCustomer(String name, String lastname, int phone) throws SQLException {

		String sql = "INSERT INTO Customer (name, lastName, phone) VALUES ('" + name + "', '" + lastname + "'," + phone
				+ ")";
		try {
			stat.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
