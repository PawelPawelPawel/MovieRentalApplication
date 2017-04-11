package pl.pawel.company.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import pl.pawel.company.model.Movie;

public class MovieDAO {

	private final Connection conn;
	private final Statement stat;

	public MovieDAO() {
		DAOConector connector = new DAOConector();
		conn = connector.getConn();
		stat = connector.getStat();
	}

	public Movie getMovieById(int id) {
		try {
			ResultSet result = stat
					.executeQuery("SELECT * FROM " + Movie.class.getSimpleName() + " m WHERE m.id = " + id);
			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getInt("id"));
				movie.setTittle(result.getString("tittle"));
				movie.setRunetime(result.getInt("runetime"));
				movie.setCopies(result.getInt("copies"));
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public Collection<Movie> getMovies() {
		Collection<Movie> movies = new LinkedList<Movie>();
		try {
			ResultSet result = stat
					.executeQuery("SELECT * FROM " + Movie.class.getSimpleName() + " m WHERE m.copies > 0");
			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getInt("id"));
				movie.setTittle(result.getString("tittle"));
				movie.setRunetime(result.getInt("runetime"));
				movie.setCopies(result.getInt("copies"));
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return movies;
	}

	public Movie getLastMovie() {
		try {
			ResultSet result = stat
					.executeQuery("SELECT * FROM " + Movie.class.getSimpleName() + " m ORDER BY m.id DESC");
			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getInt("id"));
				movie.setTittle(result.getString("tittle"));
				movie.setRunetime(result.getInt("runetime"));
				movie.setCopies(result.getInt("copies"));
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void hireMovie(int customerId, int movieId) {

		String sql = "INSERT INTO PurchaseOrder (movie_id, customer_id) VALUES (" + movieId + "," + customerId + ")";

		try {
			stat.executeUpdate(sql);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateMovieState(int movieId, int count) {

		try {
			ResultSet result = stat
					.executeQuery("SELECT copies FROM " + Movie.class.getSimpleName() + " where id = " + movieId);
			int newCount = result.getInt("copies") - count;

			String sql = "update movie set copies=" + newCount + " where id =" + movieId;
			stat.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void addMovie(String tittle, int runetime, int copies) {

		String sql = "INSERT INTO Movie (tittle, runetime, copies) VALUES ('" + tittle + "', " + runetime + "," + copies
				+ ")";

		try {
			stat.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamknieciem polaczenia");
			e.printStackTrace();
		}
	}
}
