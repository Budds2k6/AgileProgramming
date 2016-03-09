import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {
	
//Test file to do init database connections and such 
//Will be edited/some code moved to better places
//seven
	
	
Test(){}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	   //if(jTextField1.getText().length()==0)  // Checking for empty field
	   //   JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
	   //else if(jPasswordField1.getPassword().length==0)  // Checking for empty field
	   //  JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
	   //else{
	   //    String user = jTextField1.getText();   // Collecting the input
	   //    char[] pass = jPasswordField1.getPassword(); // Collecting the input
	   //    String pwd = String.copyValueOf(pass);  // converting from array to string
	   //    if(validate_login(user,pwd))
	   //       JOptionPane.showMessageDialog(null, "Correct Login Credentials");        
	   //    else
	   //       JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");
	   //}        
	}


public static void main(String args[])
{
	Test theTest = new Test();
	theTest.validate_login("00000000","password");
	theTest.validate_login("11111111","password");
	theTest.validate_login("00000000","wrongpass");
	theTest.validate_login("12345678","password");
	theTest.validate_login("98765432","password");
	
}


	private boolean validate_login(String username,String password) {
	   try{           
		   String path = "../../test.txt";
		   PrintWriter writer = new PrintWriter(new FileWriter(path, true));
		   String theDriver = "com.mysql.jdbc.Driver";
		   
		   Class driver_class = Class.forName(theDriver);		   
		   //Driver theDriver = new Driver();
		   Driver driver = (Driver) driver_class.newInstance();
		   
		   
		   DriverManager.registerDriver(driver);
	       
	       Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");      
	       
	       writer.println();
	       
	       /*
	        * 	S: silva.computing.dundee.ac.uk
				U: 15agileteam2
				P: 349.at2.psswd
				D: 15agileteam2db
	        */
	       
	       PreparedStatement pst = conn.prepareStatement("Select * from testlogin where username=? and password=?");
	       pst.setString(1, username); 
	       pst.setString(2, password);
	       ResultSet rs = pst.executeQuery();                        
	       if(rs.next())  {          
	             
	    	   String returnedUser = rs.getString("username");
	           String returnedPwd = rs.getString("password");
	           String theDatas = rs.getString("somedatas");
	       
	           System.out.println(returnedUser + "  |  " + returnedPwd + "  |  " + theDatas);

	           writer.println(returnedUser + "  |  " + returnedPwd + "  |  " + theDatas);
	            writer.close();
	           
	           return true; 
		   
	       }
	       else{
	    	   writer.println("FAILED | "+ username + " | " + password);
			   writer.close();
			   return false;}

	       
	   }
	   
	   
	   catch(Exception e){
	       e.printStackTrace();
	       return false;
	   }       
	   
	   
	   
	}




}
