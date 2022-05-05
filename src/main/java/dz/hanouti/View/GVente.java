package dz.hanouti.View;

import dz.hanouti.Controller.GVenteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GVente {

    public GVente(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("/fxml/GVente.fxml"));
            //fxmlLoader.setController(new GVenteController());
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("/css/GVente.css").toExternalForm());
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
