package com.example.java2lesson6;

import com.example.java2lesson6.controllers.HelloController;
import com.example.java2lesson6.models.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
        Network network = new Network();
        network.connect();


        HelloController chatController = fxmlLoader.getController();
        chatController.setNetwork(network);
        network.waitMessage(chatController);

    }

    public static void main(String[] args) {
        launch();
    }
}