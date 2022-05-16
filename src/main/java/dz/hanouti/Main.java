package dz.hanouti;

import dz.hanouti.View.GStat;
import dz.hanouti.View.GUsers;
import dz.hanouti.View.GVente;
import dz.hanouti.View.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Assets/img/minLogo.png")));
        new GUsers(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}