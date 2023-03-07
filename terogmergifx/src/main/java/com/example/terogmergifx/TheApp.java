package com.example.terogmergifx;


import com.example.terogmergifx.domain.Prieteni;
import com.example.terogmergifx.domain.Utilizator;
import com.example.terogmergifx.repository.RepoException;
import com.example.terogmergifx.repository.RepoMesaje;
import com.example.terogmergifx.service.ServiceMesaje;
import com.example.terogmergifx.service.ServicePrieteni;
import com.example.terogmergifx.service.ServiceUtilizatori;
import com.example.terogmergifx.utils.FindFile;
import com.example.terogmergifx.validator.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TheApp {
    @FXML
    private Button acceptFirendButton;
    @FXML
    private Button cancelFirendRequestButtion;
    @FXML
    private Button afisareCereriTrimise;
    @FXML
    private Button afisarecereriPrimite;
    private Utilizator user;

    private final ObservableList<Prieteni> friendsModel = FXCollections.observableArrayList();

    private final ObservableList<Utilizator> usersModel = FXCollections.observableArrayList();

    public ServicePrieteni srvPrieteni;

    public ServiceUtilizatori srvUtilizatori;

    private String id2_for_friendship;
    private String id1_for_friendship;

    private String add_friend_id;


    @FXML
    public Label labelNumeUtilizator;

    @FXML
    private TableView<Prieteni> tabelPrieteni;
    @FXML
    private TableColumn<Prieteni, Integer> idColumn1;
    @FXML
    private TableColumn<Prieteni, Integer> idColumn2;
    @FXML
    private TableColumn<Prieteni, String> statusColumn;
    @FXML
    private TableColumn<Prieteni, String> dateColumn;

    @FXML
    private TextField cautaUtilizatorSearchBar;

    @FXML
    private TableView<Utilizator> tabelCautareUtilizator;

    @FXML
    private TableColumn<Utilizator, String> coloanaNumeUtilizator;

    @FXML
    private Button chatButton;


    public TheApp() {
    }

    public void setUser(Utilizator user) {
        this.user = user;
        this.labelNumeUtilizator.setText(user.getName());
    }

    public Utilizator checkPassword(String idUser, String parolaUser) throws RepoException, NoSuchAlgorithmException {
        return this.srvUtilizatori.checkPassword(idUser, parolaUser);
    }


    public void setSrvPrieteni(ServicePrieteni srvPrieteni) {
        this.srvPrieteni = srvPrieteni;
    }

    public void setSrvUtilizatori(ServiceUtilizatori srvUtilizatori) {
        this.srvUtilizatori = srvUtilizatori;
    }


    public void onAddButtonClicked(ActionEvent actionEvent) {
        try {
            this.srvPrieteni.adaugaPrietenie(this.user.getId(), add_friend_id);
            add_friend_id = "";
            this.initModel();
        } catch (ValidationException | RepoException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        }
    }


    public void onStergePrietenieClicked(ActionEvent actionEvent) {
        try {
            this.srvPrieteni.stergePrietenie(this.user.getId(), id2_for_friendship);
            id2_for_friendship = "";
            this.initModel();

        } catch (RepoException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        }
    }

    public void onVizualizarePrieteniClicked(ActionEvent actionEvent) {
        System.out.println("sa apasta ala de vizualiare da dc nu se intampla nimic ce");
        initModel();
        initializde();
    }

    @FXML
    public void initializde() {
        idColumn1.setCellValueFactory(new PropertyValueFactory<>("id1"));
        idColumn2.setCellValueFactory(new PropertyValueFactory<>("id2"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("friendsFrom"));
        tabelPrieteni.setItems(friendsModel);

        coloanaNumeUtilizator.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabelCautareUtilizator.setItems(usersModel);

        cautaUtilizatorSearchBar.textProperty().addListener(f -> populateSearchTable());
    }

    private void initModel() {
        Iterable<Prieteni> prieteni = this.srvPrieteni.prieteniUtilizator(this.user);
        List<Prieteni> prien = StreamSupport.stream(prieteni.spliterator(), false).collect(Collectors.toList());
        friendsModel.setAll(prien);

    }

    public void onSelectedFriendship(MouseEvent mouseEvent) {
        Prieteni prietenie = (Prieteni) tabelPrieteni.getSelectionModel().getSelectedItem();
        id2_for_friendship = prietenie.getId2();
        id1_for_friendship = prietenie.getId1();
    }

    public void populateSearchTable() {
        Iterable<Utilizator> users = this.srvUtilizatori.searchUsersWithString(cautaUtilizatorSearchBar.getText());
        List<Utilizator> usersSmthing = StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
        usersModel.setAll(usersSmthing);
    }

    public void onSelectedUser(MouseEvent mouseEvent) {
        Utilizator user = (Utilizator) tabelCautareUtilizator.getSelectionModel().getSelectedItem();
        add_friend_id = user.getId();
    }

    public void addAccount(String id, String nume, String parola, String email) throws ValidationException, RepoException, NoSuchAlgorithmException {
        this.srvUtilizatori.adaugaUtilizator(id, nume, parola, email);
    }

    public void onAcceptButtonClicked(ActionEvent actionEvent) {
        try {
            this.srvPrieteni.acceptaPrietenie(this.id1_for_friendship, this.id2_for_friendship);
            initModel();

        } catch (RepoException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText(e.getMessage());
            errorAlert.showAndWait();
            System.out.println(e);
        }

    }

    public void onVizualizareCereriTClicked(ActionEvent actionEvent) {
        Iterable<Prieteni> prieteni = this.srvPrieteni.cereriTrimise(this.user);
        List<Prieteni> prien = StreamSupport.stream(prieteni.spliterator(), false).collect(Collectors.toList());
        friendsModel.setAll(prien);
        initializde();

    }

    public void onVizualizareCereriPClicked(ActionEvent actionEvent) {
        Iterable<Prieteni> prieteni = this.srvPrieteni.cereriPrimite(this.user);
        List<Prieteni> prien = StreamSupport.stream(prieteni.spliterator(), false).collect(Collectors.toList());
        friendsModel.setAll(prien);
        initializde();
    }

    public void onChatButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader_chat = new FXMLLoader(getClass().getResource("chat-view.fxml"));
        Scene scene_chat = new Scene(loader_chat.load(), 1024, 720);
        Stage stage_chat = new Stage();
        stage_chat.setTitle("Chat");
        stage_chat.setScene(scene_chat);

        ChatController chatController = loader_chat.getController();
        FindFile finder = new FindFile();
        String fisier = finder.findFile(this.id1_for_friendship,this.id2_for_friendship);
        RepoMesaje msgRepo = new RepoMesaje(fisier);
        ServiceMesaje msgSrv = new ServiceMesaje(msgRepo);
        chatController.setSrvMsg(msgSrv);
        chatController.setId1(this.id1_for_friendship);
        chatController.setId2(this.id2_for_friendship);
        chatController.setCurrentUser(this.user);
        stage_chat.setResizable(false);
        stage_chat.show();

    }
}
