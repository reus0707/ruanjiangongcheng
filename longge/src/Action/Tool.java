package Action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tool {

	public static String changeCode(String data) throws UnsupportedEncodingException{
		return URLDecoder.decode(data,"UTF-8");
	}
//just some notition
	public static Statement initSQL(String user,String password) throws SQLException{
		try {
	          Class.forName("com.mysql.jdbc.Driver");
	         System.out.println("Success loading Mysql Driver!");
	        }
	        catch (Exception e) {
	          System.out.print("Error loading Mysql Driver!");
	          e.printStackTrace();
	     }
		Connection connect = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/newa?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT",user,password);

		System.out.println("initSQL:Success connect Mysql server!");
	    Statement stmt = connect.createStatement();
	    return stmt;
	}
}
