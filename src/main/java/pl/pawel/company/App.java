package pl.pawel.company;



import javax.swing.JPanel;

import pl.pawel.company.dao.CustomerDAO;
import pl.pawel.company.dao.MovieDAO;
import pl.pawel.company.model.Customer;
import pl.pawel.company.model.Movie;
import pl.pawel.company.service.OrderSericeImpl;
import pl.pawel.company.service.OrderService;
import pl.pawel.company.view.ApplicationView;
/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		JPanel jPanel = new JPanel();
		ApplicationView window = new ApplicationView(jPanel);
		jPanel = window.createMoviePanel();
		window.add(jPanel);
		window.setVisible(true);
	
	
	

		
		


	}
}
