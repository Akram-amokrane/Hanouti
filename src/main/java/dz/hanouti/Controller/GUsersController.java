package dz.hanouti.Controller;

import dz.hanouti.Model.Admin;
import dz.hanouti.Model.Caissier;
import dz.hanouti.Model.DbConnect;
import dz.hanouti.Model.Manager;
import dz.hanouti.Tools.MyRegex;
import dz.hanouti.Tools.Patterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUsersController extends WindowBarController {

    @FXML
    private ToggleGroup Role;

    @FXML
    private Button closeBtn;

    @FXML
    private Button insertUserBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button maximBtn;

    @FXML
    private Button minimBtn;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField userAdress;

    @FXML
    private Label userAdresseError;

    @FXML
    private TextField userCPwd;

    @FXML
    private Label userCPwdError;

    @FXML
    private Label userFNameError;

    @FXML
    private TextField userLastName;

    @FXML
    private TextField userId;

    @FXML
    private Label userIdError;

    @FXML
    private Label userNError;

    @FXML
    private TextField userName;

    @FXML
    private Label userNameError;

    @FXML
    private TextField userPwd;

    @FXML
    private Label userPwdError;

    @FXML
    private ToggleButton userRoleC;

    @FXML
    private ToggleButton userRoleM;

    @FXML
    private TextField userTel;

    @FXML
    private Label userTelError;

    @FXML
    private TextField user_name;
    @FXML
    private TextField userTable;


    @FXML
    void initialize()  {
        loadUsers();
    }
    @FXML
    void InsertNewUser(ActionEvent event) throws SQLException {
        if(valideUserForm()){
            if(userRoleM.isSelected()){
                Manager manager = new Manager(
                    0,
                        user_name.getText().trim(),
                        userLastName.getText().trim(),
                        userName.getText().trim(),
                        userTel.getText().trim(),
                        userAdress.getText().trim(),
                        userPwd.getText().trim()
                );
                Admin.insertNewManager(manager);
            }else{
                Caissier caissier = new Caissier(
                        0,
                        user_name.getText().trim(),
                        userLastName.getText().trim(),
                        userName.getText().trim(),
                        userTel.getText().trim(),
                        userAdress.getText().trim(),
                        userPwd.getText().trim()
                );
                Admin.insertNewCaissier(caissier);
            }
        }

    }

    @FXML
    void ToggleClick(ActionEvent event) {
        ((ToggleButton)event.getSource()).setSelected(true);
    }

    private Boolean valideUserForm(){
        Boolean isOk = true;
        if(!MyRegex.match(user_name.getText(), Patterns.userNRegex)){
            userNError.setVisible(true);
            userNError.setText("La syntaxe n'est pas valide (exp: @xxx_yyy)");
            isOk = false;
        }else{
            userNError.setVisible(false);
            user_name.setStyle("-fx-border-color: #8f8");
        }
        if(!MyRegex.match(userName.getText(), Patterns.userNameRegex)){
            userNameError.setVisible(true);
            userNameError.setText("La syntaxe n'est pas valide (Les 0-9 sont pas autoriser.");
            userName.setStyle("-fx-border-color: #f88");
            isOk = false;
        }else{
            userNameError.setVisible(false);
            userName.setStyle("-fx-border-color: #8f8");
        }
        if(!MyRegex.match(userLastName.getText(), Patterns.userNameRegex)){
            userFNameError.setVisible(true);
            userFNameError.setText("La syntaxe n'est pas valide (Les 0-9 sont pas autoriser.");
            userLastName.setStyle("-fx-border-color: #f88");
            isOk = false;
        }else{
            userFNameError.setVisible(false);
            userLastName.setStyle("-fx-border-color: #8f8");
        }
        if(!MyRegex.match(userTel.getText(), Patterns.userTelRegex) && !userTel.getText().isEmpty()){
            userTelError.setVisible(true);
            userTelError.setText("La syntaxe n'est pas valide ((0|+213|00213)xxxxxxxxx)");
            userTel.setStyle("-fx-border-color: #f88");
            isOk = false;
        }else{
            userTelError.setVisible(false);
            userTel.setStyle("-fx-border-color: #8f8");
        }
        if(userPwd.getText().trim().length()<8 ){
            userPwdError.setVisible(true);
            userPwdError.setText("Le mot de passe doit contenir +8 caractere");
            userPwd.setStyle("-fx-border-color: #f88");
            isOk = false;

        }else{
            userPwdError.setVisible(false);
            userPwd.setStyle("-fx-border-color: #8f8");
        }
        if(!userCPwd.getText().trim().equals(userPwd.getText().trim()) ){
            userCPwdError.setVisible(true);
            userCPwdError.setText("les mots de passe sont differents ");
            userCPwd.setStyle("-fx-border-color: #f88");
            isOk = false;
        }else{
            userCPwdError.setVisible(false);
            userCPwd.setStyle("-fx-border-color: #8f8");
        }
        return isOk;
    }

    private void loadUsers()  {
        try {
            String query = "SELECT * FROM utilisateur";
            Statement stmt = DbConnect.connect().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
                System.out.print(rs.getString("user_name"));
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void showUsers(ResultSet rs){

    }


}
