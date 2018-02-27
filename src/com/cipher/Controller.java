package com.cipher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private PasswordField txtKey;

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
            if (file != null) {
                String key = txtKey.getText().trim();
                String text = decryptFile(file, key);
                String path = "decrypted/decrypt_" + file.getName();
                try {
                    saveFile(text, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                lblStatus.setText("Select a text file to decrypt");
            }

        }
        txtKey.clear();
    }

    @FXML
    void encrypt(MouseEvent event) {
        if (validate()) {
            if (file != null) {
                String key = txtKey.getText().trim();
                String text = encryptFile(file, key);
                String path = "encrypted/encrypt_" + file.getName();
                try {
                    saveFile(text, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                lblStatus.setText("Select a text file to decrypt");
            }

        }
        txtKey.clear();

    }

    @FXML
    void selectText(MouseEvent event) {
        Stage stage = (Stage) btnOpen.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            lblStatus.setText(file.getName() + " selected");
        }
    }

    private boolean validate() {
        String key = txtKey.getText().trim();
        if (key.length() > 10 || key.length() < 4) {
            lblStatus.setText("Key length should be between 4 and 10");
            return false;
        } else if (key.matches("[A-Za-z0-9]+")) {
            return true;
        } else{
            lblStatus.setText("Key should contain only numbers and letters");
            return false;
        }
    }

    public static Integer[] createKey(String key) {
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
                    break;
                }
            }
            i++;
        }
        return keyArray.toArray(new Integer[keyArray.size()]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File"
                , "*.txt"));
        fileChooser.setTitle("Select text file");
    }

    private String encryptFile(File file, String key) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(Encryptor.encrypt(text, createKey(key))+System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    private String decryptFile(File file, String key) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(Decryptor.decrypt(text, createKey(key))+System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    private void saveFile(String text, String path) throws IOException {
        File saveFile = new File(System.getProperty("user.dir") + "/" + path);
        if (!saveFile.exists()) {

        }
        FileOutputStream os = new FileOutputStream(saveFile);
        OutputStreamWriter outWritter = new OutputStreamWriter(os);
        Writer bufferedWriter = new BufferedWriter(outWritter);
        bufferedWriter.write(text);
        bufferedWriter.close();
        outWritter.close();
        os.close();
    }
}