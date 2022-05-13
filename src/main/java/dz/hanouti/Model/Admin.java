package dz.hanouti.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends User {

    public Admin(int id,String user_name,String fname,String lname,String phone,String adress,String password){
        super(id,user_name,fname,lname,phone,adress,password);
    }

    public static void insertNewManager(Manager m) throws SQLException {
        String query = "INSERT into utilisateur( user_name, first_name, last_name, password, phone, adresse, Role) VALUE (?,?,?,?,?,?,'M')";
        PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
        stmt.setString (1, m.getUserName());
        stmt.setString (2, m.getFirstName());
        stmt.setString   (3, m.getLastName());
        stmt.setString(4, m.getPwd());
        stmt.setString    (5, m.getPhone());
        stmt.setString    (6, m.getAdress());

        stmt.execute();
    }
    public static void insertNewCaissier(Caissier c) throws SQLException {
        String query = "INSERT into utilisateur( user_name, first_name, last_name, password, phone, adresse, Role) VALUE (?,?,?,?,?,?,'C')";
        PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
        stmt.setString (1, c.getUserName());
        stmt.setString (2, c.getFirstName());
        stmt.setString   (3, c.getLastName());
        stmt.setString(4, c.getPwd());
        stmt.setString    (5, c.getPhone());
        stmt.setString    (6, c.getAdress());

        stmt.execute();
    }
}
