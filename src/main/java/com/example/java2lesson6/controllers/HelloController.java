package com.example.java2lesson6.controllers;

import com.example.java2lesson6.models.Network;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Date;

public class HelloController {

    @FXML
    private Button buttonSend;

    @FXML
    private Label chat;

    @FXML
    private TextArea messageHistory;

    @FXML
    private AnchorPane messageInput;

    @FXML
    private Label users;

    @FXML
    private ListView<String> usersText;

    @FXML
    private TextField message;
    private Network network;

    @FXML
    void initialize() {
        usersText.setItems(FXCollections.observableArrayList("Рустам","Равиль","Руслан","Амир"));
        buttonSend.setOnAction(event ->sendOutput());
        message.setOnAction(event ->sendOutput());



    }
    private void sendOutput() {
        String texSend = message.getText().trim();
        if(!texSend.isEmpty()){
            network.sendMessage(texSend);
            message.clear();
        }

    }

    public void outputMessage(String m) {
        m+= System.lineSeparator();
        messageHistory.setText(new StringBuilder(messageHistory.getText()).insert(0,m).toString());

    }


    public void setNetwork(Network network) {
        this.network = network;
    }
}