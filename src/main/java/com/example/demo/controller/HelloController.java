package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.exception.SubcriberLimitException;
import com.example.demo.exception.SubscriberBlockedException;
import com.example.demo.exception.SubscriberNotFoundException;
import com.example.demo.service.MessageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtSenderId;

    @FXML
    private TextField txtRecipientId;

    @FXML
    private TextField txtMsgText;

    @FXML
    void sendMessageAction(ActionEvent event) {
        String senderPhone = txtSenderId.getText().trim();
        String recipientPhone = txtRecipientId.getText().trim();
        String msgText = txtMsgText.getText().trim();
        boolean senderResult = senderPhone.matches("(\\+*)\\d{12}");
        boolean recipientResult = recipientPhone.matches("(\\+*)\\d{12}");
        if (senderResult && recipientResult) {
            try {
                boolean isResult = MessageService.INSTANCE.sendMessage(msgText, recipientPhone, senderPhone);
                if (isResult) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Успешно отправлено");
                    alert.show();
                    return;
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка при отправке");
                    alert.show();
                    return;
                }
            } catch (SubcriberLimitException | SubscriberNotFoundException | SubscriberBlockedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.show();
                return;
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Неверный формат номера");
            alert.show();
            return;
        }
    }

    @FXML
    void showListAction(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("show-list.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            ListController controller = loader.getController();
            controller.setData(txtRecipientId.getText().trim());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
