module comp2013 {
    opens comp2013;
    opens comp2013.Controller;
    opens comp2013.View;
    opens comp2013.Model;
    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;
}