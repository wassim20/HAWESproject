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
 * @author Hassen Chouadah
 */
public class MyConnexion {

    private Connection cnx;
    public static MyConnexion ct;

    private MyConnexion() {
        try {
            cnx = DriverManager.getConnection(Statics.url, Statics.user, Statics.pwd);
            System.out.println("Connection etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static MyConnexion getInstance() {
        if (ct == null) {
            ct = new MyConnexion();
        }
        return ct;

    }

    public Connection getCnx() {
        return cnx;
    }
}
