package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {

	private Connection connect(){

		 Connection con = null;
		 try{ 
		 
			 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 	con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
		 }
		 catch (Exception e){e.printStackTrace();
		 }
		 return con;
		 } 
		
		public String insertPayment(String paymentID,String cardNo, String nameOnCard, String expireDate, String cvc ,String totalAmount) { 
		 
			String output = ""; 
		 
			try{ 
		 
				Connection con = connect(); 
		 
				if (con == null) {
			 
					return "Error while connecting to the database for inserting."; 
		 
				} 
		 
				// create a prepared statement
		
				String query = " insert into payment (`paymentID`,`cardNo`,`nameOnCard`,`expireDate`,`cvc`,`totalAmount`)"+ " values (?, ?, ?, ?, ?, ?)"; 
		 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setInt(2, Integer.parseInt(cardNo)); 
				preparedStmt.setString(3, nameOnCard); 
				preparedStmt.setString(4, expireDate); 
				preparedStmt.setInt(5, Integer.parseInt(cvc)); 
				preparedStmt.setDouble(6, Double.parseDouble(totalAmount)); 
				
		
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
			
		
		
		public String readPayment(){


			 String output = ""; 
			 
			 try{
				 Connection con = connect();
				 if (con == null) {
		
					 return "Error while connecting to the database for reading."; }
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Payment ID</th><th>Card No</th>" +
						 "<th>Name On Card</th>" +
						 "<th>Expire Date</th>" +
						 "<th>CVC</th>" +
						 "<th>Toatal Amount</th>" +
						 "<th>Update</th><th>Remove</th></tr>";
			
				 String query = "select * from payment";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 String paymentID = Integer.toString(rs.getInt("paymentID"));
					 String cardNo = Integer.toString(rs.getInt("cardNo"));
					 String nameOnCard = rs.getString("nameOnCard");
					 String expireDate = rs.getString("expireDate");
					 String cvc = Integer.toString(rs.getInt("cvc"));
					 String totalAmount = Double.toString(rs.getDouble("totalAmount"));
					
					 
					 // Add into the html table
					 output += "<tr><td>" + paymentID + "</td>";
					 output += "<td>" + cardNo + "</td>";
					 output += "<td>" + nameOnCard + "</td>";
					 output += "<td>" + expireDate + "</td>";
					 output += "<td>" + cvc + "</td>";
					 output += "<td>" + totalAmount + "</td>";
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
							 + "<input name='project_id' type='hidden' value='" + paymentID
							 + "'>" + "</form></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
			 }
			 catch (Exception e){
				 output = "Error while reading the items.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		 } 
}
