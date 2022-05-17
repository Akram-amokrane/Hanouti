package dz.hanouti.Controller;

import dz.hanouti.Model.*;
import dz.hanouti.Tools.MyRegex;
import dz.hanouti.Tools.Patterns;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class GUsersController extends WindowBarController {

    private ArrayList<User> usersList = new ArrayList<User>();
    private  User selectedUser;

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
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> adresseColumn;
    @FXML
    private TableColumn<User, String> fNameColumn;

    @FXML
    private TableColumn<User, String> iconsColumn;

    @FXML
    private TableColumn<User, Integer> idColumns;

    @FXML
    private TableColumn<User, String> lNameColumn;

    @FXML
    private TableColumn<User, String> pwdColumn;
    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    private TableColumn<User, String> telColumn;
    @FXML
    private TableColumn<User, String> user_nameColumn;

    @FXML
    void initialize()  {
        setWindowTitle("Gestion des utilisateurs");
        idColumns.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        user_nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        lNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        fNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
        telColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        pwdColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        loadUsers();
        userTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User old, User user) {
                if(user!=null) {
                    selectedUser = user;
                    showUserDetails(user);
                }
            }
        });
    }
    @FXML
    void InsertNewUser(ActionEvent event)  {
        if(valideUserForm()){
            if(userRoleM.isSelected()){
                Admin.insertNewManager((Manager) getUserInfos());
            }else{
                Admin.insertNewCaissier((Cashier) getUserInfos());
            }
            initFields();
            loadUsers();
        }

    }
    @FXML
    void DeleteUser(ActionEvent event)  {
        if(!userId.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Voulez vous vraiment supprimer"+selectedUser.getUserName(),ButtonType.YES,ButtonType.NO);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.YES) {
                Admin.deleteUser(selectedUser.getUserId());
                loadUsers();
                initFields();
            }
        }
    }
    @FXML
    void EditUser(ActionEvent event)  {
        if(!userId.getText().isEmpty() || valideUserForm()){
            Admin.editUser(getUserInfos());
            loadUsers();
            initFields();
        }
    }

    @FXML
    void SearchUsers(ActionEvent event) {
        if(userId.getText().isEmpty() ){
            try {
                String query = "SELECT * FROM utilisateur WHERE (user_name=? OR first_name=? OR last_name=? OR password=? OR phone=? OR adresse=? OR Role=?)";
                PreparedStatement stmt = DbConnect.connect().prepareStatement(query);
                User user = getUserInfos();
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getPassword());
                stmt.setString(5, user.getPhone());
                stmt.setString(6, user.getAdress());
                stmt.setString(7, user.getRole());
                ResultSet rs = stmt.executeQuery();
                usersList.clear();
                while(rs.next()){
                    if(Objects.equals(rs.getString("Role"), "M")){
                        usersList.add(new Manager(
                                rs.getInt("idUser"),
                                rs.getString("user_name"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("phone"),
                                rs.getString("adresse"),
                                rs.getString("password")
                        ));
                    }else if(Objects.equals(rs.getString("Role"), "A")){
                        usersList.add(new Admin(
                                rs.getInt("idUser"),
                                rs.getString("user_name"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("phone"),
                                rs.getString("adresse"),
                                rs.getString("password")
                        ));
                    } else {
                        usersList.add(new Cashier(
                                rs.getInt("idUser"),
                                rs.getString("user_name"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("phone"),
                                rs.getString("adresse"),
                                rs.getString("password")
                        ));
                    }
                }
                showUsers(usersList);
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    @FXML
    void RefreshData(ActionEvent event) {
        initFields();
        loadUsers();
    }



    private User getUserInfos(){
        User u;
        if(userRoleM.isSelected()){
            u = new Manager(
                    userId.getText().isEmpty()?0:Integer.parseInt(userId.getText()),
                    user_name.getText().trim(),
                    userLastName.getText().trim(),
                    userName.getText().trim(),
                    userTel.getText().trim(),
                    userAdress.getText().trim(),
                    userPwd.getText().trim()
            );
        }else{
            u = new Cashier(
                    userId.getText().isEmpty()?0:Integer.parseInt(userId.getText()),
                    user_name.getText().trim(),
                    userLastName.getText().trim(),
                    userName.getText().trim(),
                    userTel.getText().trim(),
                    userAdress.getText().trim(),
                    userPwd.getText().trim()
            );
        }
        return u;
    }
    @FXML
    void ToggleClick(ActionEvent event) {
        ((ToggleButton)event.getSource()).setSelected(true);
    }

    private Boolean valideUserForm(){
        Boolean isOk = true;
        if(!MyRegex.match(user_name.getText(), Patterns.userNRegex) || user_name.getText().length()<6){
            userNError.setVisible(true);
            userNError.setText("La syntaxe n'est pas valide (exp: @prénom_nom)");
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

    private synchronized void loadUsers()  {
        try {
            String query = "SELECT * FROM utilisateur";
            Statement stmt = DbConnect.connect().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            usersList.clear();
            while(rs.next()){
                if(Objects.equals(rs.getString("Role"), "M")){
                    usersList.add(new Manager(
                            rs.getInt("idUser"),
                            rs.getString("user_name"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("adresse"),
                            rs.getString("password")
                    ));
                }else if(Objects.equals(rs.getString("Role"), "A")){
                    usersList.add(new Admin(
                            rs.getInt("idUser"),
                            rs.getString("user_name"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("adresse"),
                            rs.getString("password")
                    ));
                } else {
                    usersList.add(new Cashier(
                            rs.getInt("idUser"),
                            rs.getString("user_name"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("adresse"),
                            rs.getString("password")
                    ));
                }
            }
            showUsers(usersList);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void showUsers(ArrayList<User> users){
        ObservableList<User> usersItems = FXCollections.observableArrayList(users);
        userTable.getItems().clear();
        userTable.setItems(usersItems);
    }

    private void showUserDetails(User user){
        userId.setText(String.valueOf(user.getUserId()));
        user_name.setText(user.getUserName());
        userName.setText(user.getFirstName());
        userLastName.setText(user.getLastName());
        userAdress.setText(user.getAdress());
        userTel.setText(user.getPhone());
        userPwd.setText(user.getPassword());
        userCPwd.setText(user.getPassword());
        if(user.getRole() == "M") userRoleM.setSelected(true);
        else userRoleC.setSelected(true);
    }


    private void  initFields(){
        userId.setText("");
        user_name.setText("");
        userName.setText("");
        userLastName.setText("");
        userAdress.setText("");
        userTel.setText("");
        userPwd.setText("");
        userCPwd.setText("");
        userRoleC.setSelected(true);
    }


}
