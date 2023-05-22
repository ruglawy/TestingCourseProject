package com.example.testingcourseproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

import static com.example.testingcourseproject.Main.*;
public class AddClientController {
    public AddClientController(){}
    Main m = new Main();
    @FXML
    private Button b_create;

    @FXML
    private Button b_goBack;

    @FXML
    private TextField tf_address;

    @FXML
    private TextField tf_balance;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_number;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_username;
    @FXML
    private Label l_errorMessage;

    public void goBack() throws IOException {
        m.changeScene("AdminPage.fxml");
    }
    public void keyPressed(KeyEvent ke) {
        String value = tf_balance.getText();
        try {
            Double.parseDouble(ke.getText());
            tf_balance.setEditable(true);
        } catch (Exception e) {
            if (ke.getText().equals(".")) {
                for (int i = 0; i < value.length(); i++) {
                    if (value.charAt(i) == '.') {
                        tf_balance.setEditable(false);
                        break;
                    }
                }
            } else {
                tf_balance.setEditable(false);
            }
        }
        if (!ke.getText().isEmpty()) {
            try {
                Double.parseDouble(ke.getText());
            } catch (Exception e) {
                if (!value.isEmpty()) {
                    if (ke.getText().equals(".")) {
                        for (int i = 0; i < value.length(); i++) {
                            if (value.charAt(i) == '.') {
                                tf_balance.setEditable(false);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            tf_balance.setEditable(true);
        }
    }
    public void keyPressedID(KeyEvent ke){
        String value = tf_id.getText();
        try{
            Integer.parseInt(ke.getText());
            tf_id.setEditable(true);
        }catch(Exception e){
            tf_id.setEditable(false);
        }
        if(!ke.getText().isEmpty()){
            try {
                Integer.parseInt(ke.getText());
            } catch (Exception e) {
                if (!value.isEmpty()) {
                    tf_id.setEditable(false);
                }
            }
        }else{
            tf_id.setEditable(true);
        }
    }
    public void keyPressedNumber(KeyEvent ke){
        String value = tf_number.getText();
        try{
            Integer.parseInt(ke.getText());
            tf_number.setEditable(true);
        }catch(Exception e){
            tf_number.setEditable(false);
        }
        if(!ke.getText().isEmpty()){
            try {
                Integer.parseInt(ke.getText());
            } catch (Exception e) {
                if (!value.isEmpty()) {
                    tf_number.setEditable(false);
                }
            }
        }else{
            tf_number.setEditable(true);
        }
    }
    public boolean isEmpty() {
        return tf_address.getText().isEmpty() || tf_balance.getText().isEmpty() ||
                tf_email.getText().isEmpty() || tf_id.getText().isEmpty() ||
                tf_name.getText().isEmpty() || tf_number.getText().isEmpty() ||
                tf_password.getText().isEmpty() || tf_username.getText().isEmpty();
    }
    public boolean doesUsernameExist(String username){
        for(Client client:clients){
            if (client.getAccount().getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public boolean doesIDExist(String id){
        for(Client client:clients){
            if (client.getId().equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean doesEmailExist(String email){
        for(Client client:clients){
            if (client.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public void createClient(){
        l_errorMessage.setTextFill(Color.DARKRED);
        if (isEmpty()){
            l_errorMessage.setText("Please fill the blanks.");
            return;
        }
        if (doesUsernameExist(tf_username.getText())){
            l_errorMessage.setText("Username already exists.");
            return;
        }
        if (doesIDExist(tf_id.getText())){
            l_errorMessage.setText("ID already exists.");
            return;
        }
        if (doesEmailExist(tf_email.getText())){
            l_errorMessage.setText("Email already exists.");
            return;
        }

        double balance = Double.parseDouble(tf_balance.getText());

        clients.add(new Client(tf_id.getText(), tf_name.getText(), tf_address.getText(),
                               tf_number.getText(), tf_email.getText(), balance,
                               tf_username.getText(), tf_password.getText()));

        l_errorMessage.setTextFill(Color.GREEN);
        l_errorMessage.setText("Client's account successfully created!");

        tf_balance.clear(); tf_username.clear(); tf_number.clear(); tf_name.clear();
        tf_id.clear(); tf_password.clear(); tf_email.clear(); tf_address.clear();
    }
}
