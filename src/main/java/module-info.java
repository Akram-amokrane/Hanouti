module dz.akram.hanouti {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens dz.hanouti to javafx.fxml;
    exports dz.hanouti.View;
    opens dz.hanouti.View to javafx.fxml;
    exports dz.hanouti.Controller;
    opens dz.hanouti.Controller to javafx.fxml;
    opens dz.hanouti.Model to javafx.fxml;
    exports dz.hanouti.Model;
    exports dz.hanouti;
}