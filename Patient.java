package hospitalMS;
import java.sql.*;
import java.util.*;
public class Patient {
	private Connection connection;
	private Scanner scanner;
	 
	public Patient(Connection connection , Scanner scanner) {
		this.connection=connection;
		this.scanner=scanner;
		
	}
	public void addPatient() {
		System.out.println("Enter Patient Name: ");
		String Pname=scanner.next();
		System.out.println("Enter patient age : ");
		int age= scanner.nextInt();
		System.out.print("Enter Patient Gender : ");
		String gender=scanner.next();
		
		try {
			String query ="INSERT INTO patients(Pname,age,gender) VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, Pname);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
			int affectedRows=preparedStatement.executeUpdate();
			if(affectedRows>0) {
				System.out.println("Patient Added Successfully!!");
				
			}else {
				System.out.println("failed to add ");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void viewPatients() {
		String query = "select * from patients";
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+--------------+-----------------+--------+------------+");
			System.out.println("| Patient ID   | Name            |Age     | GEnder     | ");
			System.out.println("+--------------+-----------------+--------+------------+");
			while(resultSet.next()) {
				int id =resultSet.getInt("id");
				String Pname=resultSet.getString("Pname");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				System.out.printf("| %-12s |%-18s|%-8s|%-12s|\n",id,Pname,age,gender);
				System.out.println("+--------------+-----------------+--------+------------+");
					
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean getPatientById(int id) {
		String query ="Select * From patients where id=?";
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
