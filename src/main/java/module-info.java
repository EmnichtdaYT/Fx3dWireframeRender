module de.emnichtda.fx3dwireframerender {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.emnichtda.fx3dwireframerender.test to javafx.fxml;
    exports de.emnichtda.fx3dwireframerender.test;
}