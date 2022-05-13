package dz.hanouti;

import dz.hanouti.View.GStat;
import dz.hanouti.View.GUsers;
import dz.hanouti.View.GVente;
import dz.hanouti.View.Login;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new GUsers(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}