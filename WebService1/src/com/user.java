package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class user {

	
	private Connection connect() { 
	 
		Connection con = null; 
			try{ 
				
					Class.forName("com.mysql.jdbc.Driver"); 
	 
	 
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/code", "root", "root"); 
			} 
			catch (Exception e) {
				
				e.printStackTrace();
				
			} 
			
			return con; 
	 } 
	
	public String insertUser(String first_name, String last_name, String em, String usern, String pass) { 
	 
		String output = "";
		
		try{ 
			
			Connection con = connect();
			
			if (con == null) {
				
				return "Error while connecting to the database for inserting."; 
				
			} 
			
			// create a prepared statement
			String query = " insert into user (`first_name`,`last_name`,`em`,`usern`, `pass`)"+ " values (?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, first_name); 
			preparedStmt.setString(2, last_name); 
			preparedStmt.setString(3, em); 
			preparedStmt.setString(4, usern); 
			preparedStmt.setString(5, pass); 
	 
			preparedStmt.execute(); 
			con.close();
			
			output = "Inserted successfully"; 
			
		} 
		catch (Exception e) { 
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
		} 
		
		return output; 
	 } 
	
	public String readUser() { 
		
		String output = ""; 
		
		
			try{ 
				
				Connection con = connect(); 
				
				if (con == null) {
					
					return "Error while connecting to the database for reading."; 
					
				} 
	 
				output = "<table border='1'><tr><th>ID</th><th>First Name</th>" +
						"<th>Last Name</th>" + 
						"<th>Email</th>" +
						"<th>Username</th>" +
						"<th>Password</th>" +
						"<th>Update</th><th>Remove</th></tr>"; 
				
	 
				String query = "select * from user"; 
				Statement stmt = (Statement) con.createStatement(); 
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next()) { 
					
						String ID = Integer.toString(rs.getInt("id")); 
						String first_name = rs.getString("first_name"); 
						String last_name = rs.getString("last_name"); 
						String em =rs.getString("em"); 
						String usern = rs.getString("usern"); 
						String pass = rs.getString("pass");
						
						// Add into the html table
						output += "<tr><td>" + ID + "</td>"; 
						output += "<td>" + first_name + "</td>"; 
						output += "<td>" + last_name + "</td>"; 
						output += "<td>" + em + "</td>"; 
						output += "<td>" + usern + "</td>";
						output += "<td>" + pass + "</td>";
						
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='items.jsp'>"
								+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
								+ "<input name='id' type='hidden' value='" + ID + "'>" + "</form></td></tr>"; 
	 
				} 
				
				con.close(); 
				// Complete the html table
				output += "</table>"; 
	 
			} 
			catch (Exception e) { 
				
				output = "Error while reading the user."; 
				System.err.println(e.getMessage()); 
	 
			} 
			return output; 
	
	} 
	
	public String updateUser(String id,String first_name, String last_name, String em, String usern, String pass){ 
		
		
		 String output = ""; 
		 
		 try{
			 
			 
			 Connection con = connect(); 
			 
			 if (con == null) {
				 
				 return "Error while connecting to the database for updating."; 
				 
			 } 
			 
			 
			 	// create a prepared statement
			 	String query = "UPDATE user SET first_name=?,last_name=?,em=?,usern=?, pass=? WHERE id=?"; 
			 	PreparedStatement preparedStmt = con.prepareStatement(query); 
			 	
			 	
			 		// binding values
			 		
			 		preparedStmt.setString(1, first_name); 
			 		preparedStmt.setString(2, last_name); 
			 		preparedStmt.setString(3, em); 
			 		preparedStmt.setString(4, usern); 
			 		preparedStmt.setString(5, pass);
			 		preparedStmt.setInt(6, Integer.parseInt(id));
			 		
			 		// execute the statement
			 		preparedStmt.execute(); 
			 		con.close(); 
			 		output = "Updated successfully"; 
		 
		 } 
		 
		 
		 catch (Exception e) { 
			 
		 output = "Error while updating the user."; 
		 System.err.println(e.getMessage());
		 
		 }
		 
		 return output; 
		 
	} 
		public String deleteUser(String id) { 
			
			
		 String output = ""; 
		 
		 try{ 
			 
			 
			 	Connection con = connect(); 
			 	
			 	
			 		if (con == null) {
			 			
			 			return "Error while connecting to the database for deleting.";
			 			
			 		} 
			 		
			 		
			 			// create a prepared statement
			 			String query = "delete from user where id=?"; 
			 			PreparedStatement preparedStmt = con.prepareStatement(query);
			 			
			 			
			 			// binding values
			 			preparedStmt.setInt(1, Integer.parseInt(id)); 
			 			
			 			// execute the statement
			 			preparedStmt.execute(); 
			 			con.close(); 
			 			
			 			output = "Deleted successfully"; 
		 
		 } 
		 
		 catch (Exception e) {
			 
			 output = "Error while deleting the user."; 
			 System.err.println(e.getMessage()); 
		 
		 } 
		 
		 	return output; 
		 
		} 
		
		public String login(String usern, String pass) {
			
			String output ="";
			
			try {
				
				Connection con = connect();
				
				if(con == null) {
					
					return "error while connecting to dadabase";
				}
				
				System.out.println("error");
				
				String query = "select `usern`, `pass` from user where usern =? and pass=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				System.out.println(preparedStmt);
				System.out.println(usern);
				System.out.println(pass);
				
				preparedStmt.setString(1, usern);
				preparedStmt.setString(2, pass);
				
				ResultSet rs = preparedStmt.executeQuery();
				
				
				if(rs.next()) {
					
					con.close();
					output = usern +""+ "Successfully loged in.";
				}
				else {
					
					con.close();
					if(usern.equals("")) {
						
						return "User cannot be empty";
					}
					else if(pass.equals("")) {
						
						return "password cannot be empty";
						
					}
					else if(usern.equals("admin")) {
						
						return "Admin is log in to the system";
					}
					else if(usern.equals("")) {
						
						return "User cannot be empty";
					}
					else if(pass.equals("admin123")) {
						
						return "Admin login successful";
					}
					else if(pass.equals("")) {
						
						return "password cannot be empty";
					}
					else {
						
						return "incorrect username or password";
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				output = "Error while deleting the user."; 
				 System.err.println(e.getMessage()); 
				
			}
			return output;
		}
		

}
