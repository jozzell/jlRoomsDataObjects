/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.*;
import javax.sql.*;

public class jdbcdemo{

public static void main(String args[]){
String dbtime;
String dbUrl = "jdbc:Mysql://localhost:3306/jlrooms";
//String dbUrl2 = "jdbc:Mysql://lmeans-bisc/jlrooms";
String dbClass = "com.mysql.jdbc.Driver";
String user="jozzell",pass="cru$h";

try {
System.out.println("Starting");
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection (dbUrl,user,pass);
Statement stmt = con.createStatement();




con.close();
System.out.println("done");
} //end try

catch(ClassNotFoundException e) {
e.printStackTrace();
}

catch(SQLException e) {
e.printStackTrace();
}

}  //end main

}  //end class