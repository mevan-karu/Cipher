package com.cipher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private TextField txtKey;

    @FXML
    private Button btnEncrypt;

    @FXML
    private Button btnDecrypt;

    @FXML
    private Button btnOpen;

    @FXML
    private Label lblStatus;

    private FileChooser fileChooser;

    private File file;

    @FXML
    void decrypt(MouseEvent event) {
        if (validate()) {

        }
    }

    @FXML
    void encrypt(MouseEvent event) {
        if (validate()) {

        }

    }

    @FXML
    void selectText(MouseEvent event) {
        Stage stage = (Stage) btnOpen.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file !=null){

        }


    }

    private boolean validate() {
        String key = txtKey.getText().trim();
        if (key.length() > 10 || key.length() < 4) {
            lblStatus.setText("Key length should be between 4 and 10");
            return false;
        } else {
            return true;
        }
    }

    private static ArrayList<Integer> createKey(String key) {
        ArrayList<Integer> keyArray = new ArrayList<>();
        int i = 0;
        while (keyArray.size() < key.length()) {
            int val = ((int) key.charAt(i));
            while (true) {
                int x = val % (key.length());
                if (keyArray.contains(x)) {
                    val++;
                } else {
                    keyArray.add(x);
                    System.out.println(x);
                    break;
                }
            }
            i++;
        }
        return keyArray;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("txt"
                , "*.txt" ));
        fileChooser.setTitle("Select text file");
    }
}