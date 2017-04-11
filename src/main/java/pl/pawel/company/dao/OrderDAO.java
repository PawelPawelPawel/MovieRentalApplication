package pl.pawel.company.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import pl.pawel.company.model.Customer;
import pl.pawel.company.model.Movie;
import pl.pawel.company.model.PurchaseOrder;

public class OrderDAO {

	private final Connection conn;
	private final Statement stat;

	public OrderDAO() {
		DAOConector connector = new DAOConector();
		conn = connector.getConn();
		stat = connector.getStat();
	}

	public PurchaseOrder getLastOrder() {
		String sql = "SELECT * FROM PurchaseOrder o JOIN Customer c ON c.id = o.customer_id JOIN Movie m ON m.id = o.movie_id ORDER BY o.id DESC";

		try {
			ResultSet result = stat.executeQuery(sql);
			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("customer_id"));
				customer.setLastName(result.getString("lastName"));
				customer.setName(result.getString("name"));
				customer.setPhone(result.getInt("phone"));

				Movie movie = new Movie();
				movie.setId(result.getInt("movie_id"));
				movie.setRunetime(result.getInt("runetime"));
				movie.setTittle(result.getString("tittle"));
				movie.setCopies(result.getInt("copies"));

				PurchaseOrder order = new PurchaseOrder();
				order.setCustomer(customer);
				order.setMovie(movie);
				order.setId(result.getInt("id"));
				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public Collection<PurchaseOrder> getOrders() {
		String sql = "SELECT * FROM PurchaseOrder o JOIN Customer c ON c.id = o.customer_id JOIN Movie m ON m.id = o.movie_id";
		Collection<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();
		try {
			ResultSet result = stat.executeQuery(sql);
			while (result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("customer_id"));
				customer.setLastName(result.getString("lastName"));
				customer.setName(result.getString("name"));
				customer.setPhone(result.getInt("phone"));

				Movie movie = new Movie();
				movie.setId(result.getInt("movie_id"));
				movie.setRunetime(result.getInt("runetime"));
				movie.setTittle(result.getString("tittle"));
				movie.setCopies(result.getInt("copies"));

				PurchaseOrder order = new PurchaseOrder();
				order.setCustomer(customer);
				order.setMovie(movie);
				order.setId(result.getInt("id"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return orders;

	}
}
