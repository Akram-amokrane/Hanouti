package dz.hanouti.Model;

public class Cashier extends User{
    public Cashier(int id, String user_name, String fname, String lname, String phone, String adress, String password) {
        super(id, user_name, fname, lname, phone, adress,password,"C");
    }
}
