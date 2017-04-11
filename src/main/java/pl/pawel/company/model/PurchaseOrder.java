package pl.pawel.company.model;

import java.util.Date;

public class PurchaseOrder {

	private int id;
	private Movie movie;
	private Customer customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", movie=" + movie + ", customer=" + customer + "]";
	}

}
