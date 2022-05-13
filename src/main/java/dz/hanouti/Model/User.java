package dz.hanouti.Model;

public abstract class User {
    private int idUser;
    private String user_name;
    private String firstName;
    private String lastName;
    private String Phone;
    private String adress;
    private String password;

    public User(int id,String user_name,String fname,String lname,String phone,String adress,String password){
         this.idUser = id;
         this.user_name = user_name ;
         this.firstName = fname;
         this.lastName = lname;
         this.Phone = phone;
         this.adress = adress;
         this.password = password;
    }

    public int getUserId(){
        return this.idUser;
    };
    public String getUserName(){
        return this.user_name;
    };
    public String getFirstName(){
        return this.firstName;
    };
    public String getLastName(){
        return this.lastName;
    };
    public String getPhone(){
        return this.Phone;
    };
    public String getAdress(){
        return this.adress;
    };
    public String getPwd(){
        return this.password;
    };


}
