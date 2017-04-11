package pl.pawel.company.model;

public class Movie {

	private int id;
	private String tittle;
	private int copies;
	private int runetime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getRunetime() {
		return runetime;
	}

	public void setRunetime(int runetime) {
		this.runetime = runetime;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + tittle;
	}

}
