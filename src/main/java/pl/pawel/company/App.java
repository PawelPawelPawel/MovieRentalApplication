package pl.pawel.company;

import javax.swing.JPanel;

import pl.pawel.company.view.ApplicationView;

public class App {
	public static void main(String[] args) {
		JPanel jPanel = new JPanel();
		ApplicationView window = new ApplicationView(jPanel);
		jPanel = window.createMoviePanel();
		window.add(jPanel);
		window.setVisible(true);
	}
}
