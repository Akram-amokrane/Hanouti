package dz.hanouti.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {
    public Login(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("/fxml/Login.fxml"));
            System.out.println(Login.class.getResource("/fxml/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/css/Login.css").toExternalForm());
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
