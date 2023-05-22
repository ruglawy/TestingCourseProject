package com.example.testingcourseproject;

import static com.example.testingcourseproject.Main.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PayBillController implements Initializable {
    public PayBillController(){}
    Main m = new Main();
    @FXML
    private Button b_back;

    @FXML
    private Button b_pay;

    @FXML
    private ComboBox<String> cb_type;

    @FXML
    private Label l_message;

    @FXML
    private TextField tf_amount;

    @FXML
    private TextField tf_other;
    public void keyPressed(KeyEvent ke) {
        String value = tf_amount.getText();
        try {
            Double.parseDouble(ke.getText());
            tf_amount.setEditable(true);
        } catch (Exception e) {
            if (ke.getText().equals(".")) {
                for (int i = 0; i < value.length(); i++) {
                    if (value.charAt(i) == '.') {
                        tf_amount.setEditable(false);
                        break;
                    }
                }
            } else {
                tf_amount.setEditable(false);
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
                                tf_amount.setEditable(false);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            tf_amount.setEditable(true);
        }
    }
    public void goBack() throws IOException {
        m.changeScene("ClientPage.fxml");
    }
    public boolean isEmpty(){
        return tf_amount.getText().isEmpty() || cb_type.getSelectionModel().isEmpty();
    }
    public void enableOther(){
        if(!cb_type.getSelectionModel().isEmpty()) {
            if (cb_type.getSelectionModel().getSelectedItem().equals("Other")) {
                tf_other.setDisable(false);
            } else {
                tf_other.setDisable(true);
                tf_other.clear();
            }
        }
    }
    public void pay(){
        l_message.setAlignment(Pos.CENTER);
        if(isEmpty()){
            l_message.setTextFill(Color.DARKRED);
            l_message.setText("Please fill the blanks.");
            return;
        }

        double balance = clients.get(clientIndex).getBalance();
        double amount = Double.parseDouble(tf_amount.getText());

        if (balance < amount){
            l_message.setTextFill(Color.DARKRED);
            l_message.setText("Insufficient balance, your current balance is: $" + balance);
            return;
        }
        balance -= amount;
        clients.get(clientIndex).setBalance(balance);

        if (tf_other.isDisable()){
            clients.get(clientIndex).addTransaction(cb_type.getSelectionModel().getSelectedItem(), -amount);
        } else {
            clients.get(clientIndex).addTransaction(tf_other.getText(), -amount);
        }

        l_message.setTextFill(Color.GREEN);
        l_message.setText("Payment successful, your new balance is: $" + balance);
        tf_other.clear(); tf_other.setDisable(true);
        tf_amount.clear(); cb_type.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_type.getItems().removeAll(cb_type.getItems());
        cb_type.getItems().addAll("Water", "Electricity", "Gas", "Other");
    }
}
