package com.example.terogmergifx;

import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.repository.RepoException;
import com.example.terogmergifx.validator.ValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;

public class LoginScreenController {

    private TheApp app;

    @FXML
    private TextField idUtilizatorTextView;

    @FXML
    private PasswordField parolaUtilizatorTextView;

    @FXML
    private Button Loginbutton;

    @FXML
    private Button addAcountButton;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailUtilizatorTextView;

    @FXML
    private TextField numeUtilizatorTextView;

    @FXML
    private Label numeUtilizatorLabel;


    public void setApp(TheApp app) {
        this.app = app;
    }

    public void onLoginButtonClicked(ActionEvent actionEvent) {
        String idUser = this.idUtilizatorTextView.getText();
        String parolaUser = this.parolaUtilizatorTextView.getText();

        try {

           Utilizator user = this.app.checkPassword(idUser, parolaUser);
            Stage stage = (Stage) Loginbutton.getScene().getWindow();
            this.app.setUser(user);
            stage.close();

        } catch (RepoException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }

    public void onAddAcountButtonPressed(ActionEvent actionEvent) {

        String id = this.idUtilizatorTextView.getText();
        String nume = this.numeUtilizatorTextView.getText();
        String email = this.emailUtilizatorTextView.getText();
        String parola = this.parolaUtilizatorTextView.getText();

        try
        {
            this.app.addAccount(id,nume,parola,email);
        } catch (ValidationException | RepoException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        this.numeUtilizatorLabel.setOpacity(0);
        this.numeUtilizatorTextView.setOpacity(0);
        this.emailLabel.setOpacity(0);
        this.emailUtilizatorTextView.setOpacity(0);
        this.addAcountButton.setOpacity(0);
    }

    public void onCreateAccountButtonClicked(ActionEvent actionEvent) {
        this.numeUtilizatorLabel.setOpacity(1);
        this.numeUtilizatorTextView.setOpacity(1);
        this.emailLabel.setOpacity(1);
        this.emailUtilizatorTextView.setOpacity(1);
        this.addAcountButton.setOpacity(1);

    }
}
