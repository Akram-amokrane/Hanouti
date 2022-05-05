package dz.hanouti.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WindowBarController {

    @FXML
    private Button maximBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button minimBtn;

    @FXML
    private Label titleLabel;

    @FXML
    void windowActionEvent(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        if(event.getSource() == closeBtn) closeWindow(stage);
        if(event.getSource() == minimBtn) minimWindow(stage);
        if(event.getSource() == maximBtn) maximWindow(stage);
        if(event.getSource() == logoutBtn) System.out.println("Deconnected");
    }

    void closeWindow(Stage stage)  {
        stage.close();
    }

    void minimWindow(Stage stage) {
        stage.setIconified(true);
    }

    void maximWindow(Stage stage) {
        if(stage.isMaximized())  stage.setMaximized(false);
        else stage.setMaximized(true);
    }

    void setWindowTitle(String title){
        titleLabel.setText(title);
    }

}
