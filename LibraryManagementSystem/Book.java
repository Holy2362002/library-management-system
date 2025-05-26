public class Book {

	private String id;
	private String title;
	private String author;
	private boolean isAvailable;

	public Book(String id,String title,String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isAvailable = true;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setAvaliable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public boolean getAvaliable() {
		return isAvailable;
	}

	public String toString() {
        return String.format("Book ID: %-10s Title: %-30s Author: %-20s Available: %-5s",
            id, title, author, isAvailable ? "Yes" : "No");
    }
}