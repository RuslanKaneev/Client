package com.example.java2lesson6.models;

import com.example.java2lesson6.controllers.HelloController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8186;
    private final String host;
    private final int port;
    private DataInputStream in;
    private DataOutputStream out;

    private HelloController chatController;

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Network() {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public void connect() {
        try {
            Socket socketConnect = new Socket(host, port);

            in = new DataInputStream(socketConnect.getInputStream());

            out = new DataOutputStream(socketConnect.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Соединение не установлено");
        }

    }


    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
        }
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void waitMessage(HelloController chatController) {
                Thread t = new Thread(() -> {
                    try {while (true){
                        String message = in.readUTF();
                        if (message.contains("Сервер :")){
                            System.out.println(message);
                        } else {
                            chatController.outputMessage("Я : " + message);
                        }
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                t.setDaemon(true);
                t.start();
    }

    public HelloController getChatController() {
        return chatController;
    }

    public void setChatController(HelloController chatController) {
        this.chatController = chatController;
    }
}
