package com.example.terogmergifx;

import com.example.terogmergifx.repository.DBRepoPrieteni;
import com.example.terogmergifx.repository.DBRepoUtilizatori;
import com.example.terogmergifx.service.ServicePrieteni;
import com.example.terogmergifx.service.ServiceUtilizatori;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        String username = "postgres";
        String password = "1337";
        String url = "jdbc:postgresql://localhost:5432/BazaDeDateProiectMare";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1024, 720);
        stage.setTitle("Wonderful app build version 2.0");
        stage.setScene(scene);

        DBRepoPrieteni dvRepoPrieteni = new DBRepoPrieteni(url, username, password);
        DBRepoUtilizatori dbRepoUtilizatori = new DBRepoUtilizatori(url, username, password);
        ServiceUtilizatori srvUsers = new ServiceUtilizatori(dbRepoUtilizatori);
        ServicePrieteni srvPrieteni = new ServicePrieteni(dvRepoPrieteni);

        TheApp app = fxmlLoader.getController();
        app.setSrvPrieteni(srvPrieteni);
        app.setSrvUtilizatori(srvUsers);

        stage.show();


        FXMLLoader loader_login = new FXMLLoader(getClass().getResource("login-screen.fxml"));
        Scene scene_login = new Scene(loader_login.load(), 1024, 720);
        Stage stage_login = new Stage();
        stage_login.setTitle("Login");
        stage_login.setScene(scene_login);

        LoginScreenController logctrl = loader_login.getController();
        logctrl.setApp(app);
        stage_login.show();
        stage_login.setOnCloseRequest(f -> closeApp(stage));

    }

    private void closeApp(Stage stage) {
        System.out.println("So inchis asta ");
        stage.close();
    }


    public static void main(String[] args) {
        launch();
    }
}