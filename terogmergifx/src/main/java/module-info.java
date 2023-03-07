module com.example.terogmergifx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.terogmergifx to javafx.fxml;
    exports com.example.terogmergifx;

    exports  com.example.terogmergifx.domain;
    opens com.example.terogmergifx.domain to javafx.fxml;
}