package com.cipher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Cipher.fxml"));
        primaryStage.setTitle("Product Cipher");
        primaryStage.setScene(new Scene(root, 366, 253));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        String x = Encryptor.encrypt("mevan karunanayakeee", Controller.createKey("mevan"));
        System.out.println(x);
        System.out.println(Decryptor.decrypt(x, Controller.createKey("mevan")));
    }
}
