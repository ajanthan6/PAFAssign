package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DbConnect;
import model.PowerUnit;

public class UniteControler {
	
	public String insertunite(int unitid, int accountNo, String CusName, int  unit, int amount)
	{
	

		String output = ""; 
		try
		{ 
			Connection con = DbConnect.connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for inserting.";
				}
			
			// create a prepared statement
			String query = " insert into orders(`unitid`,`accountNo`,`CusName`,`unit`,`amount`)" + " values (?, ?, ?, ?, ?)"; 
			//PreparedStatement preparedStmt = con.prepareStatement(query);
				
				PreparedStatement ps = con.prepareStatement("query");
				ps.setInt(1, unitid);
				ps.setInt(2, accountNo);
				ps.setString(3, CusName);
				ps.setInt(4, unit);
				ps.setInt(4, amount);
			

				ps.execute(); 
				con.close(); 
				output = "Order Inserted successfully";
				} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the order details."; 
				System.err.println(e.getMessage()); 
				} 
			return output; 

			
	}

	
	public String readUnite() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con =DbConnect.connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Unit ID</th>" + "<th>Account No</th>" + "<th>Customer Name</th>" + 
			"<th>Power Unite</th>"+"<th>Amount</th>" + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from orders"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String unitid = Integer.toString(rs.getInt("unitid"));
				String accountNo = rs.getString("accountNo");
				String CusName = rs.getString("CusName"); 
				String unit = rs.getString("unit");
				String amount = rs.getString("amount");
				
				
				// Add into the html table
				output += "<tr><td>" + unitid + "</td>";
				output += "<td>" + accountNo + "</td>";
				output += "<td>" + CusName + "</td>"; 
				output += "<td>" + unit + "</td>"; 
				output += "<td>" +amount + "</td>"; 
				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" 
				+ "<td><form method='post' action='Orders.jsp'>" 
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" 
				+ "<input name='itemID' type='hidden' value='" + unitid  
				+ "'>" + "</form></td></tr>";
				
			} 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the Power Unite details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String updateunite(String unitid, String accountNo, String CusName, String  unit, String amount)
	{
		String output = ""; 
		try
		{ 
			Connection con = DbConnect.connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for updating.";
			} 
			
			// create a prepared statement
			String query = "UPDATE orders SET Order_date=?,Project_ID=?,Project_name=?,Sponsor_ID=?,Budget=? WHERE Order_ID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1,Integer.parseInt(unitid));
			preparedStmt.setInt(2,Integer.parseInt (accountNo));
			preparedStmt.setString(3, CusName);
			preparedStmt.setInt(4, Integer.parseInt( unit));
			
			preparedStmt.setInt(5, Integer.parseInt(amount));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Unite Updated successfully";
			
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while updating the Unite details."; 
			 System.err.println(e.getMessage()); 
		 } 
		
		return output;
    } 
	
		public String deleteUnite(String unitid) 
		{ 
			String output = "";
			try
			{ 
				Connection con = DbConnect.connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for deleting.";
				} 
				
				// create a prepared statement
				String query = "delete from Unite where unit_id=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(unitid));
				
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "unit Deleted successfully"; 
					
			} 
			catch (Exception e) 
			{ 
				output = "Error while deleting the unit details."; 
				System.err.println(e.getMessage()); 
			} 
			
			return output; 
		 } 

}
