package dz.hanouti.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class User {
    private SimpleIntegerProperty idUser;
    private SimpleStringProperty userName;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty Phone;
    private SimpleStringProperty adress;
    private SimpleStringProperty password;
    private SimpleStringProperty role;

    public User(int id, String userName, String fname, String lname, String phone, String adress, String password, String role){
         this.idUser = new SimpleIntegerProperty(id);
         this.userName = new SimpleStringProperty(userName) ;
         this.firstName = new SimpleStringProperty(fname);
         this.lastName = new SimpleStringProperty(lname);
         this.Phone = new SimpleStringProperty(phone);
         this.adress = new SimpleStringProperty(adress);
         this.password = new SimpleStringProperty(password);
         this.role = new SimpleStringProperty(role);
    }

    public int getUserId(){
        return this.idUser.get();
    };
    public String getUserName(){
        return this.userName.get();
    };
    public String getFirstName(){
        return this.firstName.get();
    };
    public String getLastName(){
        return this.lastName.get();
    };
    public String getPhone(){
        return this.Phone.get();
    };
    public String getAdress(){
        return this.adress.get();
    };
    public String getPassword(){
        return this.password.get();
    };
    public String getRole(){
        return this.role.get();
    };



}
