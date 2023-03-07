package com.example.terogmergifx;

import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.repository.RepoException;
import com.example.terogmergifx.service.ServiceMesaje;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChatController {

    private ServiceMesaje srvMsg;

    private String id1, id2;

    @FXML
    TextField messagetextField;

    @FXML
    TextArea displayMessagesTextField;

    private Utilizator currentUser;

    public void onSendButtonClicked(ActionEvent actionEvent) {
        String text = messagetextField.getText();
        try {
            if(id1.equals(this.currentUser.getId()))
                this.srvMsg.addMessage(id2, id1, text);
            else
                this.srvMsg.addMessage(id1, id2, text);
            loadMessages();
            this.messagetextField.clear();
        } catch (IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        }
    }

    public ChatController() {
    }

    public void setSrvMsg(ServiceMesaje srvMsg) {
        this.srvMsg = srvMsg;
        loadMessages();
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public void loadMessages() {
        try {
            String text = this.srvMsg.getMessages();
            this.displayMessagesTextField.setText(text);
        } catch (IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        }
    }

    public void setCurrentUser(Utilizator currentUser) {
        this.currentUser = currentUser;
    }
}
