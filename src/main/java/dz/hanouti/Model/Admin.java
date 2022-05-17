package dz.hanouti.Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends User {

    public Admin(int id, String user_name, String fname, String lname, String phone, String adress, String password) {
        super(id, user_name, fname, lname, phone, adress, password, "A");
    }

    public static void insertNewManager(Manager m) {
        try {
            String query = "INSERT into utilisateur( user_name, first_name, last_name, password, phone, adresse, Role) VALUE (?,?,?,?,?,?,'M')";
            PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
            stmt.setString(1, m.getUserName());
            stmt.setString(2, m.getFirstName());
            stmt.setString(3, m.getLastName());
            stmt.setString(4, m.getPassword());
            stmt.setString(5, m.getPhone());
            stmt.setString(6, m.getAdress());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertNewCaissier(Cashier c) {
        try {
            String query = "INSERT into utilisateur( user_name, first_name, last_name, password, phone, adresse, Role) VALUE (?,?,?,?,?,?,'C')";
            PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
            stmt.setString(1, c.getUserName());
            stmt.setString(2, c.getFirstName());
            stmt.setString(3, c.getLastName());
            stmt.setString(4, c.getPassword());
            stmt.setString(5, c.getPhone());
            stmt.setString(6, c.getAdress());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editUser(User user) {
        try {
            String query = "UPDATE utilisateur set user_name=?,first_name=?,last_name=?,password=?,phone=?,adresse=?,Role=? WHERE idUser = ?";
            PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getAdress());
            stmt.setString(7, user.getRole());
            stmt.setInt(8, user.getUserId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void deleteUser(int userId) {
        try {
            String query = "DELETE FROM utilisateur WHERE idUser=?";
            PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
