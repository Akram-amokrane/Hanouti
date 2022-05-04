package dz.hanouti;

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
        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //new Login(stage);
        new GVente(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}