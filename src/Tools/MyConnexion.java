/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author Hassen
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MossMoss
 */
public class MyConnexion {
    public String url="jdbc:mysql://localhost:3306/projet-pi";
    public String user="root";
    public String pwd="";
    
    private Connection cnx;
    public static MyConnexion ct;
    private MyConnexion(){
        try{
            cnx=DriverManager.getConnection(url,user,pwd);
            System.out.println("Connection etablie");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            
        }
    }
    
    public static MyConnexion getInstance(){
        if(ct == null)
            ct = new MyConnexion();
        return ct;
        
    }
    public Connection getCnx(){
        return cnx;
    }
}