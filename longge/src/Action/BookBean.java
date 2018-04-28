package Action;

public class BookBean {
	private static final long serialVersionUID = 1L;
	public String Title;
	public String ISBN;
	public int AuthorID;
	public String Publisher;
	public String PublishDate;
	public float Price;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}

	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}


	public int getAuthorID() {
		return AuthorID;
	}


	public void setAuthorID(int authorID) {
		this.AuthorID = authorID;
	}


	public String getPublisher() {
		return Publisher;
	}


	public void setPublisher(String publisher) {
		this.Publisher = publisher;
	}


	public String getPublishDate() {
		return PublishDate;
	}


	public void setPublishDate(String publishDate) {
		this.PublishDate = publishDate;
	}


	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		this.Price = price;
	}




}
