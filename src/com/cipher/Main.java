package com.cipher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Cipher.fxml"));
        primaryStage.setTitle("Product Cipher");
        primaryStage.setScene(new Scene(root, 366, 253));
        primaryStage.show();
    }


    public static void main(String[] args) {

        File encryptDir = new File("/" + System.getProperty("user.dir") + "/encrypted");
        File decryptDir = new File("/" + System.getProperty("user.dir") + "/decrypted");
        if (!encryptDir.exists()) {
            encryptDir.mkdir();
        }
        if (!decryptDir.exists()) {
            decryptDir.mkdir();
        }

        launch(args);
    }
}
