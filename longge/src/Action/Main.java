package Action;
import java.sql.*;
import java.util.ArrayList;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
public class Main extends ActionSupport{
	private static final long serialVersionUID = 1L;
	ArrayList<BookBean> Booklist = new ArrayList<BookBean>();
	private String name;
	private Author myAuthor = new Author();
	private BookBean myBook = new BookBean();
	private int authorid;
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public BookBean getMyBook() {
		return myBook;
	}
	public void setMyBook(BookBean myBook) {
		this.myBook = myBook;
	}
	public Author getMyAuthor() {
		return myAuthor;
	}
	public void setMyAuthor(Author myAuthor) {
		this.myAuthor = myAuthor;
	}
	public String execute(){
		return SUCCESS;
	}
	public ArrayList<BookBean> getBooklist() {
		return Booklist;
	}
	public void setBooklist(ArrayList<BookBean> booklist) {
		Booklist = booklist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String showall() throws UnsupportedEncodingException, SQLException{
		Booklist.clear();
		Statement stmt = Tool.initSQL("root","131413");
		ResultSet rs = stmt.executeQuery("select * from Book");
		
        while (rs.next()) {
        	BookBean mybook = new BookBean();
      		mybook.ISBN=rs.getString("ISBN");
      		mybook.Title=rs.getString("Title");
      		mybook.AuthorID=rs.getInt("AuthorID");
      		mybook.Publisher=rs.getString("Publisher");
      		mybook.PublishDate=rs.getString("PublishDate");
      		mybook.Price=rs.getFloat("Price");
	      	Booklist.add(mybook);
	    }
		return SUCCESS;
	}

	public String query()throws UnsupportedEncodingException, SQLException{
		Booklist.clear();
		myBook.Title = Tool.changeCode(myBook.Title );
		Statement stmt = Tool.initSQL("root","131413");
		ResultSet rs = stmt.executeQuery("select * from Author where Name=\""+myBook.Title+"\"");
		if(!rs.next()){
      	  return ERROR;
        }
		myAuthor = new Author();
		myAuthor.AuthorID=rs.getInt("AuthorID");
		myAuthor.Name=rs.getString("Name");
		myAuthor.Age=rs.getInt("Age");
		myAuthor.Country=rs.getString("Country");
        String authorId = rs.getString("AuthorID");
        rs = stmt.executeQuery("select * from Book");
        while (rs.next()) {
	      	if(rs.getString("AuthorID").equals(authorId)){
	      		BookBean mybook = new BookBean();
	      		mybook.ISBN=rs.getString("ISBN");
	      		mybook.Title=rs.getString("Title");
	      		mybook.AuthorID=rs.getInt("AuthorID");
	      		mybook.Publisher=rs.getString("Publisher");
	      		mybook.PublishDate=rs.getString("PublishDate");
	      		mybook.Price=rs.getFloat("Price");
		      	Booklist.add(mybook);
	      	}
        }
		return SUCCESS;
	}

	public String toauthor()throws UnsupportedEncodingException, SQLException{
		Booklist.clear();
		Statement stmt = Tool.initSQL("root","131413");
		ResultSet rs = stmt.executeQuery("select * from Author where AuthorID="+authorid);
		if(!rs.next()){
	      	  return ERROR;
	      }
		myAuthor = new Author();
		myAuthor.AuthorID=rs.getInt("AuthorID");
		myAuthor.Name=rs.getString("Name");
		myAuthor.Age=rs.getInt("Age");
		myAuthor.Country=rs.getString("Country");
        int authorId = rs.getInt("AuthorID");
        rs = stmt.executeQuery("select * from Book");
        while (rs.next()) {
	      	if(rs.getInt("AuthorID")==authorId){
	      		BookBean mybook = new BookBean();
	      		mybook.ISBN=rs.getString("ISBN");
	      		mybook.Title=rs.getString("Title");
	      		mybook.AuthorID=rs.getInt("AuthorID");
	      		mybook.Publisher=rs.getString("Publisher");
	      		mybook.PublishDate=rs.getString("PublishDate");
	      		mybook.Price=rs.getFloat("Price");
		      	Booklist.add(mybook);
	      	}
        }
		return SUCCESS;
	}
	//notation
	public String addbook() throws UnsupportedEncodingException, SQLException{
		Statement stmt = Tool.initSQL("root", "131413");
		ResultSet rs = stmt.executeQuery("select * from Book where ISBN=\""+myBook.ISBN+"\"");
		if(rs.next()){
			return "fail";
		}

		try{
			stmt.executeUpdate("INSERT INTO Book VALUES(\""+myBook.ISBN+"\", \""+myBook.Title+"\","+ myBook.AuthorID+",\""+myBook.Publisher+"\",\""+myBook.PublishDate+"\","+myBook.Price+")");

		} catch(SQLException e){
			return "exception";
		}
		rs = stmt.executeQuery("select * from Author where AuthorID="+myBook.AuthorID);
		if(rs.next())
			return SUCCESS;
		else{
			return ERROR;
		}

	}
	public String toaddbook(){
		return SUCCESS;
	}

	public String addauthor() throws UnsupportedEncodingException, SQLException{
		myAuthor.Name=Tool.changeCode(myAuthor.Name);
		myAuthor.Country=Tool.changeCode(myAuthor.Country);
		Statement stmt = Tool.initSQL("root", "131413");
		try{
			stmt.executeUpdate("INSERT INTO Author VALUES("+myAuthor.AuthorID+", \""+myAuthor.Name+"\","+ myAuthor.Age+",\""+myAuthor.Country+"\")");
		} catch(SQLException e){
			return "exception";
		}
		return SUCCESS;
	}

	public String delete() throws UnsupportedEncodingException, SQLException{
		name = Tool.changeCode(name);
		Statement stmt = Tool.initSQL("root","131413");
		ResultSet rs = stmt.executeQuery("select * from Book where ISBN=\""+name+"\"");
		if(!rs.next()){
			System.out.println("dwd");
		}
		stmt.executeUpdate("delete from Book where ISBN=\""+name+"\"");
		return SUCCESS;
	}

}
