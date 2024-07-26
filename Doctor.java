package hospitalMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctor {
	private Connection connection;
	
	 
	public Doctor(Connection connection ) {
		this.connection=connection;
		
		
	}
	
	public void viewDoctors() {
		String query = "select * from doctors";
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+--------------+-----------------+--------------------+");
			System.out.println("| Doctor ID    | Name            |Specilization      | ");
			System.out.println("+--------------+-----------------+--------------------+");
			while(resultSet.next()) {
				int id =resultSet.getInt("id");
				String Dname=resultSet.getString("Dname");
				
				String specilization = resultSet.getString("specilization");
				System.out.printf("| %-10s | %-18s | %-16s |\n",id,Dname,specilization);
				System.out.println("+--------------+-----------------+--------------------+");
					
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean getDoctorById(int id) {
		String query ="Select * From doctors where id=?";
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}