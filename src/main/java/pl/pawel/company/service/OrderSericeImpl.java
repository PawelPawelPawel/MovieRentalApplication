package pl.pawel.company.service;

import pl.pawel.company.dao.MovieDAO;

public class OrderSericeImpl implements OrderService {

	private MovieDAO movieDAO = new MovieDAO();

	@Override
	public void hireMovie(int customerId, int movieId, int copies) {
		movieDAO.hireMovie(customerId, movieId);
		movieDAO.updateMovieState(movieId, copies);
	}

}
